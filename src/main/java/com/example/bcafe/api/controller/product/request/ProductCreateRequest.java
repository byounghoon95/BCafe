package com.example.bcafe.api.controller.product.request;

import com.example.bcafe.api.service.product.request.ProductCreateServiceRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@ApiModel(value = "상품 등록 DTO")
@Getter
@NoArgsConstructor
public class ProductCreateRequest {

    @ApiModelProperty(value = "상품 이름", notes = "상품 이름", required = true, example = "아메리카노")
    @NotBlank(message = "상품 이름은 필수입니다")
    private String name;

    @ApiModelProperty(value = "상품 가격", notes = "상품 가격은 양수입니다", required = true, example = "3000")
    @Positive(message = "상품 가격은 양수여야 합니다")
    private int price;

    @ApiModelProperty(value = "상품 재고", notes = "상품 재고는 0개 이상입니다. 값이 없으면 0으로 지정합니다", example = "30")
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