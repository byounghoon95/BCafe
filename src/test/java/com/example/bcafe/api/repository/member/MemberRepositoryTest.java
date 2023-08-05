package com.example.bcafe.api.repository.member;

import com.example.bcafe.entity.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.BOOLEAN;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    private Member createMember(String phoneNumber, String name, int age) {
        return Member.builder()
                .phoneNumber(phoneNumber)
                .name(name)
                .age(age)
                .build();
    }

    private void setUp() {
        Member member = createMember("010-1111-2222", "이병훈", 29);
        memberRepository.save(member);
    }

    @Transactional
    @DisplayName("전화번호로 회원의 정보를 논리적 삭제한다")
    @Test
    void delete_by_phone_number() {
        //given
        setUp();
        String phoneNumber = "010-1111-2222";

        //when
        Member findMember = memberRepository.findByPhoneNumber(phoneNumber).get();
        memberRepository.deleteByPhoneNumber(phoneNumber);
        Optional<Member> deletedMember = memberRepository.findByPhoneNumber(phoneNumber);

        //then
        assertThat(findMember.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(deletedMember).isEmpty();
    }
}