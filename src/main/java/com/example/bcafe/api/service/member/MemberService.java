package com.example.bcafe.api.service.member;

import com.example.bcafe.api.repository.member.MemberRepository;
import com.example.bcafe.api.repository.points.PointsRepository;
import com.example.bcafe.api.service.member.request.MemberCreateServiceRequest;
import com.example.bcafe.api.service.member.request.MemberDeleteServiceRequest;
import com.example.bcafe.api.service.member.response.MemberDeleteResponse;
import com.example.bcafe.api.service.member.response.MemberResponse;
import com.example.bcafe.entity.member.Member;
import com.example.bcafe.entity.points.Points;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PointsRepository pointsRepository;

    @Transactional
    public MemberResponse createMember(MemberCreateServiceRequest request) {

        Member member = request.toMemberEntity();
        Member savedMember = memberRepository.save(member);

        Points points = request.toPointsEntity();
        pointsRepository.save(points);

        return MemberResponse.of(savedMember);
    }

    @Transactional
    public MemberDeleteResponse deleteMember(MemberDeleteServiceRequest request) {

        Member member = request.toMemberEntity();
        memberRepository.deleteByPhoneNumber(request.getPhoneNumber());

        pointsRepository.deleteByPhoneNumber(request.getPhoneNumber());


        return MemberDeleteResponse.of(member);
    }
}
