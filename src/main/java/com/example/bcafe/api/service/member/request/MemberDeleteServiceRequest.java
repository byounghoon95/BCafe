package com.example.bcafe.api.service.member.request;

import com.example.bcafe.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDeleteServiceRequest {

    private String phoneNumber;

    @Builder
    public MemberDeleteServiceRequest(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Member toMemberEntity() {
        return Member.builder()
                .phoneNumber(phoneNumber)
                .build();
    }
}
