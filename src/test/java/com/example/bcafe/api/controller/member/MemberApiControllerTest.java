package com.example.bcafe.api.controller.member;

import com.example.bcafe.CommonControllerTest;
import com.example.bcafe.api.controller.member.request.MemberCreateRequest;
import com.example.bcafe.api.controller.member.request.MemberDeleteRequest;
import com.example.bcafe.api.service.member.MemberService;
import com.example.bcafe.enums.CodeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class MemberApiControllerTest extends CommonControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    MemberService memberService;

    @DisplayName("새로운 회원 정보를 등록한다")
    @Test
    void create_member() throws Exception {
        //given
        MemberCreateRequest request = MemberCreateRequest.builder()
                .phoneNumber("010-1111-2222")
                .name("이병훈")
                .age(29)
                .build();

        //when //then
        mockMvc.perform(post("/api/member/create")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(jsonPath("$.code").value(CodeEnum.SUCCESS.getCode()))
        ;
    }

    @DisplayName("회원 정보를 삭제한다")
    @Test
    void remove_member() throws Exception {
        //given
        MemberDeleteRequest request = MemberDeleteRequest.builder()
                .phoneNumber("010-1111-2222")
                .build();

        //when //then
        mockMvc.perform(delete("/api/member/delete")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(jsonPath("$.code").value(CodeEnum.SUCCESS.getCode()))
        ;
    }

}