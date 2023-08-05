package com.example.bcafe.api.service.member.response;

import com.example.bcafe.entity.member.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponse {

    private Long id;
    private String phoneNumber;
    private String name;
    private int age;

    @Builder
    public MemberResponse(Long id, String phoneNumber, String name, int age) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.age = age;
    }

    public static MemberResponse of(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .phoneNumber(member.getPhoneNumber())
                .name(member.getName())
                .age(member.getAge())
                .build();
    }
}
