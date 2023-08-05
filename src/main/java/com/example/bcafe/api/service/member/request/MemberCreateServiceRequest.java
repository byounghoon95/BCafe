package com.example.bcafe.api.service.member.request;

import com.example.bcafe.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberCreateServiceRequest {
    private String phoneNumber;
    private String name;
    private int age;

    @Builder
    public MemberCreateServiceRequest(String phoneNumber, String name, int age) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.age = age;
    }

    public Member toMemberEntity() {
        return Member.builder()
                .phoneNumber(phoneNumber)
                .name(name)
                .age(age)
                .build();
    }
}
