package com.example.bcafe.api.controller.member;

import com.example.bcafe.api.common.response.CommonResponse;
import com.example.bcafe.api.controller.member.request.MemberCreateRequest;
import com.example.bcafe.api.controller.member.request.MemberDeleteRequest;
import com.example.bcafe.api.service.member.MemberService;
import com.example.bcafe.api.service.member.response.MemberDeleteResponse;
import com.example.bcafe.api.service.member.response.MemberResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "회원 API")
@RequestMapping("/api/member")
@RequiredArgsConstructor
@RestController
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/create")
    @ApiOperation(value = "회원 등록", notes = "핸드폰 번호, 이름, 나이를 갖고 회원을 등록합니다")
    public CommonResponse<MemberResponse> createMember(@Valid @RequestBody MemberCreateRequest request) {
        return new CommonResponse<>(memberService.createMember(request.toServiceRequest()));
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "회원 삭제", notes = "핸드폰 번호를 갖고 회원을 삭제합니다")
    public CommonResponse<MemberDeleteResponse> deleteMember(@Valid @RequestBody MemberDeleteRequest request) {
        return new CommonResponse<>(memberService.deleteMember(request.toServiceRequest()));
    }
}
