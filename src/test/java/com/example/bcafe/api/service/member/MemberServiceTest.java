package com.example.bcafe.api.service.member;

import com.example.bcafe.api.service.member.request.MemberCreateServiceRequest;
import com.example.bcafe.api.service.member.response.MemberResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @DisplayName("새로운 회원 정보를 등록한다")
    @Test
    void create_member() {
        //given
        MemberCreateServiceRequest request = MemberCreateServiceRequest.builder()
                .phoneNumber("010-1111-2222")
                .name("이병훈")
                .age(29)
                .build();

        //when
        MemberResponse response = memberService.createMember(request);

        //then
        assertNotNull(response);
        assertEquals("이병훈", response.getName());
        assertEquals(29, response.getAge());
    }
}