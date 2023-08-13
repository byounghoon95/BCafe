package com.example.bcafe.api.service.product.response;

import com.example.bcafe.entity.product.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductUpdateResponse {
    private String productCode;
    private String name;
    private int price;
    private int quantity;

    @Builder
    private ProductUpdateResponse(String productCode, String name, int price, int quantity) {
        this.productCode = productCode;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public static ProductUpdateResponse of(Product product, int quantity) {
        return ProductUpdateResponse.builder()
                .productCode(product.getProductCode())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(quantity)
                .build();
    }
}
