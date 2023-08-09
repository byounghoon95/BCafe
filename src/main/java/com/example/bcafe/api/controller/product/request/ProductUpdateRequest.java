package com.example.bcafe.api.controller.product.request;

import com.example.bcafe.api.service.product.request.ProductUpdateServiceRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductUpdateRequest {

    private String name;

    private int price = 0;

    private int quantity;

    @Builder
    private ProductUpdateRequest(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductUpdateServiceRequest toServiceRequest() {
        return ProductUpdateServiceRequest.builder()
                .name(name)
                .price(price)
                .quantity(quantity)
                .build();
    }

}