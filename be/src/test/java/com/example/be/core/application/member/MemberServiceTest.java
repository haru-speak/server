package com.example.be.core.application.member;

import com.example.be.core.application.MemberService;
import com.example.be.core.application.dto.request.MemberFormRequest;
import com.example.be.core.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
@DisplayName("서비스 테스트 : 회원가입")
public class MemberServiceTest {

  @Autowired
  private MemberService memberService;

  @Autowired
  private PasswordEncoder passwordEncoder;


  @Test
  @DisplayName("회원가입 테스트")
  public void saveMemberTest() {
    // given
    MemberFormRequest memberFormRequest = new MemberFormRequest("승연", "jakalroni@naver.com", "1111");

    // when
    Member member = Member.of(memberFormRequest, passwordEncoder);
    Member savedMember = memberService.saveMember(member);

    // then
    Assertions.assertThat(member.getEmail()).isEqualTo(savedMember.getEmail());
  }
}