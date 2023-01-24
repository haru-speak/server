package com.example.be.core.application.member;

import com.example.be.core.application.MemberService;
import com.example.be.core.application.dto.request.MemberFormRequest;
import com.example.be.core.application.dto.response.MemberResponse;
import com.example.be.core.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("local")
@DisplayName("서비스 테스트 : Member 회원가입")
public class MemberServiceTest {

  @Autowired
  private MemberService memberService;

  @Autowired
  private PasswordEncoder passwordEncoder;


  @Nested
  @DisplayName("회원가입 요청 시")
  class SaveMemberTest {

    @Nested
    @DisplayName("정상적으로 데이터가 입력되었다면")
    class NormalTest {

      @Test
      @DisplayName("회원가입에 성공한다.")
      public void NormalSaveMemberTest() {
        // given
        MemberFormRequest memberFormRequest = new MemberFormRequest("승연", "jakalroni@naver.com",
            "1111", passwordEncoder);

        // when
//        Member member = Member.of(memberFormRequest);
        MemberResponse savedMember = memberService.create(memberFormRequest);

        // then
        Assertions.assertThat(memberFormRequest.getEmail()).isEqualTo(savedMember.getEmail());
      }
    }

    @Nested
    @DisplayName("중복 회원 예외의 경우")
    class DupulicateMemberTest {

      @Test
      @DisplayName("회원가입에 실패한다.")
      public void DuplicateSaveMemberTest() {
        // given
        MemberFormRequest memberFormRequest1 = new MemberFormRequest("승연", "jakalroni@naver.com", "1111", passwordEncoder);
        MemberFormRequest memberFormRequest2 = new MemberFormRequest("재욱", "jakalroni@naver.com", "1234", passwordEncoder);


        // when
//        Member member1 = Member.of(memberFormRequest1);
//        Member member2 = Member.of(memberFormRequest2);

        MemberResponse savedMember1 = memberService.create(memberFormRequest1);

        // then
        Assertions.assertThatThrownBy(() -> memberService.create(memberFormRequest2))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("이미 가입된 회원입니다.");

      }
    }
  }
}