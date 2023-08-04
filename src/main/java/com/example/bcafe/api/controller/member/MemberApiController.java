package com.example.bcafe.api.controller.member;

import com.example.bcafe.api.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/member")
@RequiredArgsConstructor
@RestController
public class MemberApiController {

    private MemberService memberService;

}
