package com.example.bcafe.api.service.product.response;

import com.example.bcafe.entity.product.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel(value = "상품 수정 응답 DTO")
@Getter
public class ProductUpdateResponse {

    @ApiModelProperty(value = "상품 코드", notes = "상품 코드", example = "P00001")
    private String productCode;

    @ApiModelProperty(value = "상품 이름", notes = "상품 이름")
    private String name;

    @ApiModelProperty(value = "상품 가격", notes = "상품 가격")
    private int price;

    @ApiModelProperty(value = "상품 재고", notes = "상품 재고")
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
