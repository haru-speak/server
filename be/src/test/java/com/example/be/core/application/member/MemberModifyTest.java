package com.example.be.core.application.member;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.example.be.core.application.InitServiceTest;
import com.example.be.core.application.MemberService;
import com.example.be.core.application.dto.request.MemberModifyRequest;
import com.example.be.core.application.dto.request.MemberSignUpRequest;
import com.example.be.core.application.dto.response.MemberResponse;
import com.example.be.core.domain.member.MemberType;
import com.example.be.core.domain.member.SpeakingTestType;
import com.example.be.core.domain.member.goal.GoalMember;
import com.example.be.core.domain.member.grade.SpeakingGradeLanguage;
import com.example.be.core.domain.member.grade.SpeakingGradeLevel;
import com.example.be.core.repository.member.goal.GoalMemberRepository;
import com.example.be.core.repository.member.goal.GoalRepository;
import com.example.be.core.repository.member.subject.SubjectMemberRepository;
import com.example.be.core.repository.member.subject.SubjectRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("서비스 테스트 : Member 개인정보 수정 테스트")
class MemberModifyTest extends InitServiceTest {

	@Autowired
	private MemberService memberService;

	private static final Long MEMBER_ID = 1L;
	private static final List<Long> GOALS = Arrays.asList(1L, 2L);
	private static final List<Long> SUBJECTS = Arrays.asList(2L ,7L, 8L);

	@BeforeEach
	void init() {
		MemberSignUpRequest signUpRequest = new MemberSignUpRequest("university",
			"eng", "4", "kor", "2",
			GOALS, SUBJECTS, Boolean.FALSE, "toefl");
		memberService.signUp(MEMBER_ID, signUpRequest);
	}

	@Nested
	@DisplayName("정상적으로 Member 회원가입을 할 때")
	class NormalModifyTest {

		@Nested
		@DisplayName("MemberModifyRequest 정보를 받아")
		class ByMemberModifyRequest {

			@Test
			@DisplayName("개인정보 수정이 완료된다.")
			void success_member_modify(){
				//given
				MemberModifyRequest request = new MemberModifyRequest("나단", null,
					"https://s3.profile_img1.png", "영어를 잘하고 싶은 나단입니다.",
					"university", "kor", "3",
					"eng", "1", GOALS, SUBJECTS,
					Boolean.FALSE, "opic");

				//when
				MemberResponse memberResponse = memberService.modify(MEMBER_ID, request);

				//then
				assertThat(memberResponse.getMemberId()).isEqualTo(MEMBER_ID);
				assertThat(memberResponse.getNickname()).isEqualTo("나단");
				assertThat(memberResponse.getEmail()).isNull();
				assertThat(memberResponse.getProfileImage()).isEqualTo("https://s3.profile_img1.png");
				assertThat(memberResponse.getIntroduce()).isEqualTo("영어를 잘하고 싶은 나단입니다.");
				assertThat(memberResponse.getMemberType()).isEqualTo(MemberType.UNIVERSITY);
				assertThat(memberResponse.getLearnerLanguage()).isEqualTo(
					SpeakingGradeLanguage.KOR);
				assertThat(memberResponse.getLearnerLevel()).isEqualTo(
					SpeakingGradeLevel.LEVEL_THREE);
				assertThat(memberResponse.getGiverLanguage()).isEqualTo(
					SpeakingGradeLanguage.ENG);
				assertThat(memberResponse.getGiverLevel()).isEqualTo(
					SpeakingGradeLevel.LEVEL_ONE);
				assertThat(memberResponse.getGoals()).hasSize(2);
				assertThat(memberResponse.getSubjects()).hasSize(3);
				assertThat(memberResponse.getAlarmStatus()).isFalse();
				assertThat(memberResponse.getTestType()).isEqualTo(SpeakingTestType.OPIC);
			}
		}
	}

}
