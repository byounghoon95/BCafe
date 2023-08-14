package com.example.bcafe.api.controller.order.request;

import com.example.bcafe.api.service.order.request.OrderCreateServiceRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderCreateRequest {

    @NotEmpty(message = "상품 번호 리스트는 필수입니다")
    private List<String> productCodes;
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
