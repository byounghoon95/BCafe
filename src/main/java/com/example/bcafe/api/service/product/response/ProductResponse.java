package com.example.bcafe.api.service.product.response;

import com.example.bcafe.entity.product.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductResponse {
    private String productCode;
    private String name;
    private int price;

    @Builder
    private ProductResponse(String productCode, String name, int price) {
        this.productCode = productCode;
        this.name = name;
        this.price = price;
    }

    public static ProductResponse of(Product product) {
        return ProductResponse.builder()
                .productCode(product.getProductCode())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
