package com.example.bcafe.api.controller.member.request;

import com.example.bcafe.api.service.member.request.MemberDeleteServiceRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class MemberDeleteRequest {

    @NotBlank(message = "휴대폰 번호는 필수입니다")
    private String phoneNumber;


    @Builder
    public MemberDeleteRequest(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public MemberDeleteServiceRequest toServiceRequest() {
        return MemberDeleteServiceRequest.builder()
                .phoneNumber(phoneNumber)
                .build();
    }

}
