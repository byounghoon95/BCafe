package com.example.bcafe.api.service.member;

import com.example.bcafe.CommonServiceTest;
import com.example.bcafe.api.service.member.request.MemberCreateServiceRequest;
import com.example.bcafe.api.service.member.request.MemberDeleteServiceRequest;
import com.example.bcafe.api.service.member.response.MemberDeleteResponse;
import com.example.bcafe.api.service.member.response.MemberResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MemberServiceTest extends CommonServiceTest {

    @DisplayName("새로운 회원 정보를 등록한다")
    @Test
    void create_member() {
        //given
        MemberCreateServiceRequest request = memberCreateServiceRequest();

        //when
        MemberResponse response = memberService.createMember(request);

        //then
        assertNotNull(response);
        assertEquals("이병훈", response.getName());
        assertEquals(29, response.getAge());
    }

    @DisplayName("회원 정보를 삭제한다")
    @Test
    void delete_member() {
        //given
        setUpMember();
        MemberDeleteServiceRequest request = memberDeleteServiceRequest();

        //when
        MemberDeleteResponse response = memberService.deleteMember(request);

        //then
        assertNotNull(response);
        assertEquals("010-1111-2222", response.getPhoneNumber());
    }
}