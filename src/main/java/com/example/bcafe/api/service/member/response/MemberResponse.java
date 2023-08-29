package com.example.bcafe.api.service.member.response;

import com.example.bcafe.entity.member.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel(value = "회원 응답 DTO")
@Getter
public class MemberResponse {

    @ApiModelProperty(value = "회원 아이디", notes = "회원 아이디")
    private Long id;

    @ApiModelProperty(value = "회원 핸드폰 번호", notes = "회원 핸드폰 번호")
    private String phoneNumber;

    @ApiModelProperty(value = "회원 이름", notes = "회원 이름")
    private String name;

    @ApiModelProperty(value = "회원 나이", notes = "회원 나이")
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
