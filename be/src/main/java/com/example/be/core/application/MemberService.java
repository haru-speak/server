package com.example.be.core.application;

import com.example.be.core.application.dto.request.MemberFormRequest;
import com.example.be.core.application.dto.response.MemberResponse;
import com.example.be.core.domain.member.Member;
import com.example.be.core.repository.member.MemberRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 컨벤션에 따라 어노테이션 길이 오름차순으로 정렬
@Slf4j
@Service
@Transactional(readOnly = true)
public class MemberService {

  private final MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

//  public Member join(Member member) {
//    validateDuplicateMember(member);
//
//    return memberRepository.save(member);
//  }

  private void validateDuplicateMember(Member member) {
    // findMember의 반환타입이 Optional이므로 예외처리 코드를 람다식으로 작성
    Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());

    findMember.ifPresent(m -> {
      throw new IllegalStateException("이미 가입된 회원입니다.");
    });

//    if (findMember != null) {
//      throw new IllegalStateException("이미 가입된 회원입니다.");
//    }
  }

  @Transactional
  public MemberResponse create(MemberFormRequest memberFormRequest) {
    // join의 역할을 create에 합침, 반환 타입이 MemberResponse이므로 그에 맞게 new로 생성해서 멤버 정보 담고 반환
    Member member = Member.of(memberFormRequest);
    validateDuplicateMember(member);
    memberRepository.save(member);
    log.debug("member = {}", member);
    return new MemberResponse(member.getId(), member.getNickname(), member.getEmail(), member.getPassword());
  }
}