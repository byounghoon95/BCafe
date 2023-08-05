package com.example.bcafe.api.repository.member;

import com.example.bcafe.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByPhoneNumber(String phoneNumber);

    @Modifying
    @Query(value = "UPDATE Member m SET m.isDeleted = true WHERE m.phoneNumber = :phoneNumber")
    void deleteByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
