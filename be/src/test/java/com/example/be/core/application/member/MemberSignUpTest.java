package com.example.be.core.application.member;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.be.core.application.InitServiceTest;
import com.example.be.core.application.MemberService;
import com.example.be.core.application.dto.request.MemberSignUpRequest;
import com.example.be.core.application.dto.response.MemberSignUpResponse;
import com.example.be.core.domain.member.MemberType;
import com.example.be.core.domain.member.grade.SpeakingGradeLanguage;
import com.example.be.core.domain.member.grade.SpeakingGradeLevel;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("서비스 테스트 : Member 회원가입 테스트")
class MemberSignUpTest extends InitServiceTest {

	@Autowired
	private MemberService memberService;

	@Nested
	@DisplayName("정상적으로 Member 회원가입을 할 때")
	class NormalSignUpTest {

		@Nested
		@DisplayName("MemberSignUpRequest 정보를 받아")
		class ByMemberSignUpRequest {

			@Test
			@DisplayName("회원가입이 완료된다.")
			void success_member_sign_up(){
			    //given
				Long memberId = 1L;
				List<Long> goals = Arrays.asList(1L, 2L);
				List<Long> subjects = Arrays.asList(2L ,7L, 8L);

				MemberSignUpRequest signUpRequest = new MemberSignUpRequest("university",
					"eng", "4", "kor", "2",
					goals, subjects, Boolean.FALSE, "toefl");

				//when
				MemberSignUpResponse memberSignUpResponse = memberService.signUp(memberId, signUpRequest);

				//then
				assertThat(memberSignUpResponse.getMemberId()).isEqualTo(memberId);
				assertThat(memberSignUpResponse.getMemberType()).isEqualTo(MemberType.UNIVERSITY);
				assertThat(memberSignUpResponse.getLearnerLanguage()).isEqualTo(
					SpeakingGradeLanguage.ENG);
				assertThat(memberSignUpResponse.getLearnerLevel()).isEqualTo(
					SpeakingGradeLevel.LEVEL_FOUR);
				assertThat(memberSignUpResponse.getGiverLanguage()).isEqualTo(
					SpeakingGradeLanguage.KOR);
				assertThat(memberSignUpResponse.getGiverLevel()).isEqualTo(
					SpeakingGradeLevel.LEVEL_TWO);
				assertThat(memberSignUpResponse.getGoals()).hasSize(2);
				assertThat(memberSignUpResponse.getSubjects()).hasSize(3);
				assertThat(memberSignUpResponse.getAlarmStatus()).isFalse();
				assertThat(memberSignUpResponse.getTestType()).isNull();
			}
		}
	}
}
