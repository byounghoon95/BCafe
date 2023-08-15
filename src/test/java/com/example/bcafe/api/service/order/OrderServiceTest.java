package com.example.bcafe.api.service.order;

import com.example.bcafe.CommonServiceTest;
import com.example.bcafe.api.service.order.request.OrderCreateServiceRequest;
import com.example.bcafe.api.service.order.response.OrderResponse;
import com.example.bcafe.entity.stock.Stock;
import com.example.bcafe.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.*;


class OrderServiceTest extends CommonServiceTest {

    @DisplayName("회원인 경우 주문코드 리스트를 받아 주문을 생성한다")
    @Test
    void create_order_member() {
        // given
        setUp();
        LocalDateTime registeredDateTime = LocalDateTime.now();
        OrderCreateServiceRequest request = orderCreateServiceMemberRequest();

        // when
        OrderResponse orderResponse = orderService.createOrder(request, registeredDateTime);

        // then
        assertThat(orderResponse.getId()).isNotNull();
        assertThat(orderResponse.getPhoneNumber()).isEqualTo(request.getPhoneNumber());
        assertThat(orderResponse)
                .extracting("registeredDateTime", "totalPrice")
                .contains(registeredDateTime, 10000);
        assertThat(orderResponse.getProducts()).hasSize(2)
                .extracting("productCode", "price")
                .containsExactlyInAnyOrder(
                        tuple("P00001", 4000),
                        tuple("P00002", 6000)
                );
    }

    @DisplayName("회원이 아닌경우 주문코드 리스트를 받아 주문을 생성한다")
    @Test
    void create_order_not_member() {
        // given
        setUp();
        LocalDateTime registeredDateTime = LocalDateTime.now();
        String notMemberPhoneNumber = "000-0000-0000";
        OrderCreateServiceRequest request = orderCreateServiceNotMemberRequest();

        // when
        OrderResponse orderResponse = orderService.createOrder(request, registeredDateTime);

        // then
        assertThat(orderResponse.getId()).isNotNull();
        assertThat(orderResponse.getPhoneNumber()).isEqualTo(notMemberPhoneNumber);
        assertThat(orderResponse)
                .extracting("registeredDateTime", "totalPrice")
                .contains(registeredDateTime, 10000);
        assertThat(orderResponse.getProducts()).hasSize(2)
                .extracting("productCode", "price")
                .containsExactlyInAnyOrder(
                        tuple("P00001", 4000),
                        tuple("P00002", 6000)
                );
    }

    @DisplayName("중복되는 상품코드 리스트로 주문을 생성할 수 있다")
    @Test
    void create_order_with_duplicate_product_codes() {
        // given
        setUp();
        LocalDateTime registeredDateTime = LocalDateTime.now();
        OrderCreateServiceRequest request = OrderCreateServiceRequest.builder()
                .productCodes(List.of("P00001", "P00001"))
                .phoneNumber("010-1111-2222")
                .build();

        // when
        OrderResponse orderResponse = orderService.createOrder(request, registeredDateTime);

        // then
        assertThat(orderResponse.getId()).isNotNull();
        assertThat(orderResponse)
                .extracting("registeredDateTime", "totalPrice")
                .contains(registeredDateTime, 8000);
        assertThat(orderResponse.getProducts()).hasSize(2)
                .extracting("productCode", "price")
                .containsExactlyInAnyOrder(
                        tuple("P00001", 4000),
                        tuple("P00001", 4000)
                );
    }

    @DisplayName("주문코드 리스트를 받아 주문을 생성하고 상품 재고를 검증한다")
    @Test
    void create_order_with_product_stock() {
        // given
        setUp();
        LocalDateTime registeredDateTime = LocalDateTime.now();
        OrderCreateServiceRequest request = OrderCreateServiceRequest.builder()
                .productCodes(List.of("P00001", "P00002", "P00003"))
                .phoneNumber("010-1111-2222")
                .build();

        // when
        OrderResponse orderResponse = orderService.createOrder(request, registeredDateTime);

        // then
        assertThat(orderResponse.getId()).isNotNull();
        assertThat(orderResponse)
                .extracting("registeredDateTime", "totalPrice")
                .contains(registeredDateTime, 17000);
        assertThat(orderResponse.getProducts()).hasSize(3)
                .extracting("productCode", "price")
                .containsExactlyInAnyOrder(
                        tuple("P00001", 4000),
                        tuple("P00002", 6000),
                        tuple("P00003", 7000)
                );

        List<Stock> stocks = stockRepository.findAllByProductCodeIn(List.of("P00001","P00002","P00003"));
        assertThat(stocks)
                .extracting("productCode", "quantity")
                .containsExactlyInAnyOrder(
                        tuple("P00001", 4),
                        tuple("P00002", 4),
                        tuple("P00003", 4)
                );
    }

    @DisplayName("재고가 부족한 상품으로 주문을 생성하려는 경우 예외가 발생한다")
    @Test
    void create_order_with_no_stock() {
        // given
        LocalDateTime registeredDateTime = LocalDateTime.now();
        setUp();
        OrderCreateServiceRequest request = OrderCreateServiceRequest.builder()
                .productCodes(List.of("P00001", "P00001", "P00001", "P00001", "P00001", "P00001"))
                .phoneNumber("010-1111-2222")
                .build();

        // when // then
        assertThatThrownBy(() -> orderService.createOrder(request, registeredDateTime))
                .isInstanceOf(CustomException.class)
                .hasMessage("LESS STOCK QUANTITY THAN REQUIRED STOCK");
    }


}