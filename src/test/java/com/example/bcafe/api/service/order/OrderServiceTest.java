package com.example.bcafe.api.service.order;

import com.example.bcafe.CommonServiceTest;
import com.example.bcafe.api.service.order.request.OrderCreateServiceRequest;
import com.example.bcafe.api.service.order.response.OrderResponse;
import com.example.bcafe.entity.member.Member;
import com.example.bcafe.entity.product.Product;
import com.example.bcafe.entity.stock.Stock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;


class OrderServiceTest extends CommonServiceTest {

    @DisplayName("주문코드 리스트를 받아 주문을 생성한다")
    @Test
    void create_order() {
        // given
        LocalDateTime registeredDateTime = LocalDateTime.now();
        setUp();
        OrderCreateServiceRequest request = orderCreateServiceRequest();

        // when
        OrderResponse orderResponse = orderService.createOrder(request, registeredDateTime);

        // then
        assertThat(orderResponse.getId()).isNotNull();
        assertThat(orderResponse.getMember().getPhoneNumber()).isEqualTo(request.getPhoneNumber());
        assertThat(orderResponse)
                .extracting("registeredDateTime", "totalPrice")
                .contains(registeredDateTime, 4000);
        assertThat(orderResponse.getProducts()).hasSize(2)
                .extracting("productCode", "price")
                .containsExactlyInAnyOrder(
                        tuple("P00001", 1000),
                        tuple("P00002", 3000)
                );
    }
}