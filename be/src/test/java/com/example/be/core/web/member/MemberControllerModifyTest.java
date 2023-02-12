package com.example.be.core.web.member;

import static com.example.be.common.response.ResponseCodeAndMessages.MODIFY_MEMBER_INFO_SUCCESS;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.member.MemberService;
import com.example.be.core.application.dto.request.MemberModifyRequest;
import com.example.be.core.application.dto.response.GoalResponse;
import com.example.be.core.application.dto.response.MemberResponse;
import com.example.be.core.application.dto.response.SubjectResponse;
import com.example.be.core.domain.member.MemberType;
import com.example.be.core.domain.member.SpeakingTestType;
import com.example.be.core.domain.member.goal.Goal;
import com.example.be.core.domain.member.grade.SpeakingGradeLanguage;
import com.example.be.core.domain.member.grade.SpeakingGradeLevel;
import com.example.be.core.domain.member.subject.Subject;
import com.example.be.core.web.InitControllerTest;
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
@DisplayName("컨트롤러 테스트 : Member 개인정보 수정")
class MemberControllerModifyTest extends InitControllerTest {

	@MockBean
	private MemberService memberService;

	@Nested
	@DisplayName("Member 개인정보 수정 할 때")
	class ModifyTest {

		@Nested
		@DisplayName("정상적인 요청이라면")
		class NormalTest {

			@Test
			@DisplayName("멤버 개인정보 수정시 해당 멤버의 정보가 업데이트 된다.")
			void modify_member() throws Exception {
				//given
				Long memberId = 1L;
				List<Long> goals = Arrays.asList(1L, 2L);
				List<Long> subjects = Arrays.asList(2L ,7L, 8L);

				MemberModifyRequest request = new MemberModifyRequest("나단", null,
					"https://s3.profile_img1.png", "영어를 잘하고 싶은 나단입니다.",
					"university", "eng", "3",
					"kor", "1", goals, subjects,
					Boolean.FALSE, "opic");

				List<GoalResponse> goalResponses = Arrays.asList(
					GoalResponse.of(new Goal(1L, "일상 속 유용한 표현 배우기!")),
					GoalResponse.of(new Goal(2L, "다른 사람들의 피드백!"))
				);
				List<SubjectResponse> subjectResponses = Arrays.asList(
					SubjectResponse.of(new Subject(2L, "영화&음악",
						"https://haru-speak-s3.s3.ap-northeast-2.amazonaws.com/image/static/movie_music.png")),
					SubjectResponse.of(new Subject(7L, "운동&건강",
						"https://haru-speak-s3.s3.ap-northeast-2.amazonaws.com/image/static/workout.png")),
					SubjectResponse.of(new Subject(8L, "동네",
						"https://haru-speak-s3.s3.ap-northeast-2.amazonaws.com/image/static/town.png"))
				);

				MemberResponse response = new MemberResponse(
					memberId,
					"나단",
					null,
					"https://s3.profile_img1.png",
					"영어를 잘하고 싶은 나단입니다.",
					MemberType.UNIVERSITY,
					SpeakingGradeLanguage.ENG,
					SpeakingGradeLevel.LEVEL_FOUR,
					SpeakingGradeLanguage.KOR,
					SpeakingGradeLevel.LEVEL_TWO,
					goalResponses,
					subjectResponses,
					Boolean.FALSE,
					SpeakingTestType.OPIC,
					0,
					0
				);

				BaseResponse<MemberResponse> baseResponse = new BaseResponse<>(MODIFY_MEMBER_INFO_SUCCESS, response);
				when(memberService.modify(refEq(memberId), refEq(request)))
					.thenReturn(response);

				//when
				ResultActions resultActions = mockMvc.perform(put("/member")
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
