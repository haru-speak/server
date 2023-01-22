package com.example.be.core.application;

import com.example.be.core.application.dto.request.MemberFormRequest;
import com.example.be.core.application.dto.request.SpeakingLogRequest;
import com.example.be.core.application.dto.response.MemberResponse;
import com.example.be.core.application.dto.response.SpeakingLogDetailResponse;
import com.example.be.core.domain.member.Member;
import com.example.be.core.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
@Service
public class MemberService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
    this.memberRepository = memberRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public Member join(Member member) {
    validateDuplicateMember(member);

    return memberRepository.save(member);
  }

  private void validateDuplicateMember(Member member) {
    Member findMember = memberRepository.findByEmail(member.getEmail());

    if (findMember != null) {
      throw new IllegalStateException("이미 가입된 회원입니다.");
    }
  }

  @Transactional
  public MemberResponse create(MemberFormRequest memberFormRequest) {
    Member member = Member.of(memberFormRequest, passwordEncoder);
    join(member);
    log.debug("member = {}", member);
    return null;
  }
}