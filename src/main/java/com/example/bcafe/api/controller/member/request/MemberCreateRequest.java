package com.example.bcafe.api.controller.member.request;

import com.example.bcafe.api.service.member.request.MemberCreateServiceRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class MemberCreateRequest {

    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "핸드폰번호 형식에 맞게 입력해주세요")
    private String phoneNumber;

    @NotBlank(message = "이름은 필수입니다")
    private String name;

    @Min(value = 0, message = "나이는 0살 이상입니다")
    private int age;

    @Builder
    public MemberCreateRequest(String phoneNumber, String name, int age) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.age = age;
    }

    public MemberCreateServiceRequest toServiceRequest() {
        return MemberCreateServiceRequest.builder()
                .phoneNumber(phoneNumber)
                .name(name)
                .age(age)
                .build();
    }

}
