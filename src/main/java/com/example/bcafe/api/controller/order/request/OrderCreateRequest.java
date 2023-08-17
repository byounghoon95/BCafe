package com.example.bcafe.api.controller.order.request;

import com.example.bcafe.api.service.order.request.OrderCreateServiceRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@ApiModel(value = "주문 생성 DTO")
@Getter
@NoArgsConstructor
public class OrderCreateRequest {

    @ApiModelProperty(value = "주문한 상품 번호 리스트", notes = "상품 번호 리스트", required = true, example = "['P00001','P00002']")
    @NotEmpty(message = "상품 번호 리스트는 필수입니다")
    private List<String> productCodes;

    @ApiModelProperty(value = "회원 핸드폰 번호", notes = "회원 핸드폰 번호. 비회원인 경우 비회원의 default 번호인 000-0000-0000를 갖는다", example = "010-0000-0000")
    @NotEmpty(message = "핸드폰 번호는 필수입니다")
    private String phoneNumber = "000-0000-0000";

    @Builder
    private OrderCreateRequest(List<String> productCodes, String phoneNumber) {
        this.productCodes = productCodes;
        this.phoneNumber = phoneNumber;
    }

    public OrderCreateServiceRequest toServiceRequest() {
        return OrderCreateServiceRequest.builder()
                .productCodes(productCodes)
                .phoneNumber(phoneNumber)
                .build();
    }

}
