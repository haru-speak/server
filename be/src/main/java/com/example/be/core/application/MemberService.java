package com.example.be.core.application;

import com.example.be.core.application.dto.request.MemberFormRequest;
import com.example.be.core.application.dto.response.MemberResponse;
import com.example.be.core.domain.member.Member;
import com.example.be.core.repository.member.MemberRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class MemberService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
    this.memberRepository = memberRepository;
    this.passwordEncoder = passwordEncoder;
  }

  private void validateDuplicateMember(Member member) {
    Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());

    findMember.ifPresent(m -> {
      throw new IllegalStateException("이미 가입된 회원입니다.");
    });
  }

  private void passwordEncode(MemberFormRequest memberFormRequest) {
    passwordEncoder.encode(memberFormRequest.getPassword());
    }

  @Transactional
  public MemberResponse create(MemberFormRequest memberFormRequest) {
    passwordEncode(memberFormRequest);
    log.debug("memberFormRequest = {}", memberFormRequest);

    Member member = Member.of(memberFormRequest);
    validateDuplicateMember(member);
    memberRepository.save(member);
    log.debug("member = {}", member);
    return new MemberResponse(member.getId(), member.getNickname(), member.getEmail(), member.getPassword(),
        member.getProfileImage());
  }
}