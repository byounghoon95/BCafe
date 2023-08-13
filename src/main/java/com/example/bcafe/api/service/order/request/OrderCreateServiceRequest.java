package com.example.bcafe.api.service.order.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderCreateServiceRequest {

    private List<String> productCodes;
    private String phoneNumber;

    @Builder
    private OrderCreateServiceRequest(List<String> productCodes, String phoneNumber) {
        this.productCodes = productCodes;
        this.phoneNumber = phoneNumber;
    }

}
