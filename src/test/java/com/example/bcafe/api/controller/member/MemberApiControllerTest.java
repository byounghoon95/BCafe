package com.example.bcafe.api.controller.member;

import com.example.bcafe.CommonControllerTest;
import com.example.bcafe.api.controller.member.request.MemberCreateRequest;
import com.example.bcafe.api.controller.member.request.MemberDeleteRequest;
import com.example.bcafe.enums.CodeEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class MemberApiControllerTest extends CommonControllerTest {

    @DisplayName("새로운 회원 정보를 등록한다")
    @Test
    void create_member() throws Exception {
        //given
        MemberCreateRequest request = memberCreateRequest();

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
    void delete_member() throws Exception {
        //given
        MemberDeleteRequest request = memberDeleteRequest();

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