package com.example.bcafe.api.service.product.response;

import com.example.bcafe.entity.product.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductResponse {
    private Long id;
    private String productCode;
    private String name;
    private int price;

    @Builder
    private ProductResponse(Long id, String productCode, String name, int price) {
        this.id = id;
        this.productCode = productCode;
        this.name = name;
        this.price = price;
    }

    public static ProductResponse of(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .productCode(product.getProductCode())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
