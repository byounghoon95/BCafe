package com.example.bcafe.api.service.product.response;

import com.example.bcafe.entity.product.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel(value = "상품 응답 DTO")
@Getter
public class ProductResponse {

    @ApiModelProperty(value = "상품 코드", notes = "상품 코드", example = "P00001")
    private String productCode;

    @ApiModelProperty(value = "상품 이름", notes = "상품 이름")
    private String name;

    @ApiModelProperty(value = "상품 가격", notes = "상품 가격")
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
