package com.example.be.core.web.member;

import static com.example.be.common.response.ResponseCodeAndMessages.SIGN_UP_MEMBER_SUCCESS;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.MemberService;
import com.example.be.core.application.dto.request.MemberSignUpRequest;
import com.example.be.core.application.dto.response.GoalResponse;
import com.example.be.core.application.dto.response.MemberSignUpResponse;
import com.example.be.core.application.dto.response.SubjectResponse;
import com.example.be.core.domain.member.MemberType;
import com.example.be.core.domain.member.SpeakingTestType;
import com.example.be.core.domain.member.goal.Goal;
import com.example.be.core.domain.member.grade.SpeakingGradeLanguage;
import com.example.be.core.domain.member.grade.SpeakingGradeLevel;
import com.example.be.core.domain.member.subject.Subject;
import com.example.be.core.web.InitControllerTest;
import com.example.be.core.web.MemberController;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;


@WebMvcTest(MemberController.class)
@DisplayName("컨트롤러 테스트 : Member 회원가입")
class MemberControllerSignUpTest extends InitControllerTest {

	@MockBean
	private MemberService memberService;

	@Nested
	@DisplayName("Member 회원가입을 할 때")
	class SignUpTest {

		@Nested
		@DisplayName("정상적인 요청이라면")
		class NormalTest {
			@Test
			@DisplayName("멤버 회원가입시 해당 멤버의 정보가 업데이트 된다.")
			void sign_up_member() throws Exception {
			    //given
				Long memberId = 1L;
				List<Long> goals = Arrays.asList(1L, 2L);
				List<Long> subjects = Arrays.asList(2L ,7L, 8L);

				MemberSignUpRequest request = new MemberSignUpRequest("university",
					"eng", "1", "kor", "2",
					goals, subjects, Boolean.FALSE, "opic");

				List<GoalResponse> goalResponses = Arrays.asList(
					GoalResponse.of(new Goal(1L, "일상 속 유용한 표현 배우기!")),
					GoalResponse.of(new Goal(2L, "다른 사람들의 피드백!"))
				);
				List<SubjectResponse> subjectResponses = Arrays.asList(
					SubjectResponse.of(new Subject(2L, "영화&음악")),
					SubjectResponse.of(new Subject(7L, "운동&건강")),
					SubjectResponse.of(new Subject(8L, "동네"))
				);

				MemberSignUpResponse response = new MemberSignUpResponse(
					memberId,
					MemberType.UNIVERSITY,
					SpeakingGradeLanguage.ENG,
					SpeakingGradeLevel.LEVEL_FOUR,
					SpeakingGradeLanguage.KOR,
					SpeakingGradeLevel.LEVEL_TWO,
					goalResponses,
					subjectResponses,
					Boolean.FALSE,
					null
				);

				BaseResponse<MemberSignUpResponse> baseResponse = new BaseResponse<>(
					SIGN_UP_MEMBER_SUCCESS, response);
				when(memberService.signUp(refEq(memberId), refEq(request)))
					.thenReturn(response);

			    //when
				ResultActions resultActions = mockMvc.perform(post("/member")
					.header("Authorization", "Bearer "+jwtProvider.generateAccessToken(memberId))
					.content(objectMapper.writeValueAsString(request))
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.contentType(MediaType.APPLICATION_JSON_VALUE));

				//then
				resultActions.andExpect(status().isOk())
					.andExpect(content().string(objectMapper.writeValueAsString(baseResponse)))
					.andDo(print());
			}
		}
	}
}
