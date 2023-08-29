package com.example.bcafe.api.controller.member.request;

import com.example.bcafe.api.service.member.request.MemberCreateServiceRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel(value = "회원 등록 요청 DTO")
@Getter
@NoArgsConstructor
public class MemberCreateRequest {

    @ApiModelProperty(value = "회원 핸드폰 번호", notes = "회원 핸드폰 번호. 형식에 맞게 입력한다", required = true, example = "000-0000-0000")
    @NotBlank
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "핸드폰번호 형식에 맞게 입력해주세요")
    private String phoneNumber;

    @ApiModelProperty(value = "회원 이름", notes = "회원 이름", required = true, example = "이병훈")
    @NotBlank(message = "이름은 필수입니다")
    private String name;

    @ApiModelProperty(value = "회원 나이", notes = "회원 나이. 나이는 0 이상이다", required = false, example = "29")
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
