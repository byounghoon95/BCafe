package com.example.bcafe.api.controller.member.request;

import com.example.bcafe.api.service.member.request.MemberDeleteServiceRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@ApiModel(value = "회원 삭제 요청 DTO")
@Getter
@NoArgsConstructor
public class MemberDeleteRequest {

    @ApiModelProperty(value = "회원 핸드폰 번호", notes = "회원 핸드폰 번호", required = true, example = "000-0000-0000")
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
