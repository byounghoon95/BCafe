package com.example.bcafe.api.controller.product.request;

import com.example.bcafe.api.service.product.request.ProductCreateServiceRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
public class ProductCreateRequest {

    @NotBlank(message = "상품 이름은 필수입니다")
    private String name;

    @Positive(message = "상품 가격은 양수여야 합니다")
    private int price;

    @Min(value = 0, message = "상품 재고는 0개 이상입니다")
    private int quantity;

    @Builder
    private ProductCreateRequest(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductCreateServiceRequest toServiceRequest() {
        return ProductCreateServiceRequest.builder()
                .name(name)
                .price(price)
                .quantity(quantity)
                .build();
    }

}