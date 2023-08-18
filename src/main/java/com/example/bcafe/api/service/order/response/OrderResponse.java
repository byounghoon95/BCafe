package com.example.bcafe.api.service.order.response;

import com.example.bcafe.api.service.product.response.ProductResponse;
import com.example.bcafe.entity.member.Member;
import com.example.bcafe.entity.order.Order;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel(value = "주문 응답 DTO")
@Getter
public class OrderResponse {

    @ApiModelProperty(value = "주문 아이디", notes = "주문 아이디")
    private Long id;

    @ApiModelProperty(value = "주문한 총 가격", notes = "주문한 총 가격")
    private int totalPrice;

    @ApiModelProperty(value = "주문 생성 시간", notes = "주문 생성 시간", example = "2023-08-18T22:03:31.8704234")
    private LocalDateTime registeredDateTime;

    @ApiModelProperty(value = "주문 상품 리스트", notes = "주문 상품 리스트")
    private List<ProductResponse> products;

    @ApiModelProperty(value = "주문한 회원 번호", notes = "주문한 회원 번호")
    private String phoneNumber;

    @Builder
    private OrderResponse(Long id, int totalPrice, LocalDateTime registeredDateTime, List<ProductResponse> products, String phoneNumber) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.registeredDateTime = registeredDateTime;
        this.products = products;
        this.phoneNumber = phoneNumber;
    }

    public static OrderResponse of(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .totalPrice(order.getTotalPrice())
                .registeredDateTime(order.getRegisteredDateTime())
                .products(order.getOrderProducts().stream()
                        .map(orderProduct -> ProductResponse.of(orderProduct.getProduct()))
                        .collect(Collectors.toList())
                )
                .phoneNumber(order.getMember().getPhoneNumber())
                .build();
    }

}
