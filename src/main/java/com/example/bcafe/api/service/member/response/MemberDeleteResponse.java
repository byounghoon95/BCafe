package com.example.bcafe.api.service.member.response;

import com.example.bcafe.entity.member.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel(value = "회원 삭제 응답 DTO")
@Getter
public class MemberDeleteResponse {

    @ApiModelProperty(value = "회원 핸드폰 번호", notes = "회원 핸드폰 번호")
    private String phoneNumber;

    @Builder
    public MemberDeleteResponse(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static MemberDeleteResponse of(Member member) {
        return MemberDeleteResponse.builder()
                .phoneNumber(member.getPhoneNumber())
                .build();
    }
}
