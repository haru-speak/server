package com.example.be.core.application.member;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.be.core.application.InitServiceTest;
import com.example.be.core.application.dto.request.MemberSignUpRequest;
import com.example.be.core.application.dto.response.MemberResponse;
import com.example.be.core.domain.member.MemberType;
import com.example.be.core.domain.member.grade.SpeakingGradeLanguage;
import com.example.be.core.domain.member.grade.SpeakingGradeLevel;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("서비스 테스트 : Member 개인정보 조회 테스트")
class MemberMyPageTest extends InitServiceTest {

	private static final Long MEMBER_ID = 1L;
	private static final List<Long> GOALS = Arrays.asList(1L, 2L);
	private static final List<Long> SUBJECTS = Arrays.asList(2L ,7L, 8L);

	@Autowired
	private MemberService memberService;


	@BeforeEach
	void init() {
		MemberSignUpRequest signUpRequest = new MemberSignUpRequest("university",
			"kor", "3", "eng", "1",
			GOALS, SUBJECTS, Boolean.FALSE, "toefl");
		memberService.signUp(MEMBER_ID, signUpRequest);
	}

	@Nested
	@DisplayName("정상적으로 Member 개인정보를 조회 할 때")
	class NormalModifyTest {

		@Nested
		@DisplayName("멤버의 아이디를 통해")
		class ByMemberModifyRequest {

			@Test
			@DisplayName("해당 멤버의 개인정보가 조회된다.")
			void success_member_modify() {
				//given&when
				MemberResponse memberResponse = memberService.findById(MEMBER_ID);

				//then
				assertThat(memberResponse.getMemberId()).isEqualTo(MEMBER_ID);
				assertThat(memberResponse.getNickname()).isEqualTo("member1");
				assertThat(memberResponse.getEmail()).isEqualTo("member-email1@google.com");
				assertThat(memberResponse.getProfileImage()).isEqualTo("https://s3.profile_img1.png");
				assertThat(memberResponse.getIntroduce()).isNull();
				assertThat(memberResponse.getMemberType()).isEqualTo(MemberType.UNIVERSITY);
				assertThat(memberResponse.getLearnerLanguage()).isEqualTo(SpeakingGradeLanguage.KOR);
				assertThat(memberResponse.getLearnerLevel()).isEqualTo(SpeakingGradeLevel.LEVEL_THREE);
				assertThat(memberResponse.getGiverLanguage()).isEqualTo(SpeakingGradeLanguage.ENG);
				assertThat(memberResponse.getGiverLevel()).isEqualTo(SpeakingGradeLevel.LEVEL_ONE);
				assertThat(memberResponse.getGoals()).hasSize(2);
				assertThat(memberResponse.getSubjects()).hasSize(3);
				assertThat(memberResponse.getAlarmStatus()).isFalse();
				assertThat(memberResponse.getTestType()).isNull();
				assertThat(memberResponse.getFollowerCount()).isZero();
				assertThat(memberResponse.getFollowingCount()).isZero();
			}
		}
	}
}
