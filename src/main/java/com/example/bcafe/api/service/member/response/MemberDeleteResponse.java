package com.example.bcafe.api.service.member.response;

import com.example.bcafe.entity.member.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberDeleteResponse {

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
