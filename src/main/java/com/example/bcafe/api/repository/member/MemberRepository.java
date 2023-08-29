package com.example.bcafe.api.repository.member;

import com.example.bcafe.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query(value = "SELECT m FROM Member m WHERE m.phoneNumber = :phoneNumber AND m.isDeleted = false")
    Optional<Member> findByPhoneNumber(String phoneNumber);

}
