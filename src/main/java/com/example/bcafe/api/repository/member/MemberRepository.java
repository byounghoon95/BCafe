package com.example.bcafe.api.repository.member;

import com.example.bcafe.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
