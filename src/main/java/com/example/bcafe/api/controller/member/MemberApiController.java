package com.example.bcafe.api.controller.member;

import com.example.bcafe.api.common.response.CommonResponse;
import com.example.bcafe.api.controller.member.request.MemberCreateRequest;
import com.example.bcafe.api.controller.member.request.MemberDeleteRequest;
import com.example.bcafe.api.service.member.MemberService;
import com.example.bcafe.api.service.member.response.MemberDeleteResponse;
import com.example.bcafe.api.service.member.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/member")
@RequiredArgsConstructor
@RestController
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/create")
    public CommonResponse<MemberResponse> createMember(@Valid @RequestBody MemberCreateRequest request) {
        return new CommonResponse<>(memberService.createMember(request.toServiceRequest()));
    }

    @DeleteMapping("/delete")
    public CommonResponse<MemberDeleteResponse> deleteMember(@Valid @RequestBody MemberDeleteRequest request) {
        return new CommonResponse<>(memberService.deleteMember(request.toServiceRequest()));
    }
}
