package com.example.bcafe.api.service.product.request;

import com.example.bcafe.entity.product.Product;
import com.example.bcafe.entity.stock.Stock;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductCreateServiceRequest {
    private String name;
    private int price;
    private int quantity;

    @Builder
    private ProductCreateServiceRequest(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product toProductEntity(String nextProductId) {
        return Product.builder()
                .productCode(nextProductId)
                .name(name)
                .price(price)
                .build();
    }

    public Stock toStockEntity(String nextProductId) {
        return Stock.builder()
                .productCode(nextProductId)
                .quantity(quantity)
                .build();
    }

}