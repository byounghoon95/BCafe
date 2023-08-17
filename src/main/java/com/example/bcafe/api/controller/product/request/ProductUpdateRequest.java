package com.example.bcafe.api.controller.product.request;

import com.example.bcafe.api.service.product.request.ProductUpdateServiceRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "상품 수정 DTO")
@Getter
@NoArgsConstructor
public class ProductUpdateRequest {

    @ApiModelProperty(value = "상품 이름", notes = "상품 이름", example = "아메리카노")
    private String name;

    @ApiModelProperty(value = "상품 가격", notes = "상품 가격", example = "3000")
    private int price = 0;

    @ApiModelProperty(value = "상품 재고", notes = "상품 재고", example = "30")
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