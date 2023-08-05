package com.example.bcafe.api.controller.member;

import com.example.bcafe.api.common.response.CommonResponse;
import com.example.bcafe.api.controller.member.request.MemberCreateRequest;
import com.example.bcafe.api.service.member.MemberService;
import com.example.bcafe.api.service.member.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/api/member")
@RequiredArgsConstructor
@RestController
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/new")
    public CommonResponse<MemberResponse> createMember(@Valid @RequestBody MemberCreateRequest request) {
        return new CommonResponse<>(memberService.createMember(request.toServiceRequest()));
    }
}
