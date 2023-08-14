package com.example.bcafe.api.service.order;

import com.example.bcafe.CommonServiceTest;
import com.example.bcafe.api.service.order.request.OrderCreateServiceRequest;
import com.example.bcafe.api.service.order.response.OrderResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;


class OrderServiceTest extends CommonServiceTest {

    @DisplayName("회원인 경우 주문코드 리스트를 받아 주문을 생성한다")
    @Test
    void create_order_member() {
        // given
        LocalDateTime registeredDateTime = LocalDateTime.now();
        setUp();
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
        LocalDateTime registeredDateTime = LocalDateTime.now();
        setUp();
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
}