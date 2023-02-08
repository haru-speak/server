package com.example.be.core.web.goal_subject;

import static com.example.be.common.response.ResponseCodeAndMessages.FIND_ALL_GOALS_SUCCESS;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.dto.response.GoalResponse;
import com.example.be.core.application.member.GoalService;
import com.example.be.core.domain.member.goal.Goal;
import com.example.be.core.web.InitControllerTest;
import com.example.be.core.web.member.GoalController;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(GoalController.class)
@DisplayName("컨트롤러 테스트 : Goal 전체 조회")
class GoalControllerFindAllTest extends InitControllerTest {

	@MockBean
	private GoalService goalService;

	private static final List<GoalResponse> goals = Arrays.asList(
		GoalResponse.of(new Goal(1L, "일상 속 유용한 표현 배우기!")),
		GoalResponse.of(new Goal(2L, "다른 사람들의 피드백!")),
		GoalResponse.of(new Goal(3L, "듣기 능력 키우기!")),
		GoalResponse.of(new Goal(4L, "함께 공부할 스터디 찾기!")),
		GoalResponse.of(new Goal(5L, "어학 자격증 따기!"))
	);

	@Nested
	@DisplayName("Goal 전체 조회를 할 때")
	class RetrieveAllTest {

		@Nested
		@DisplayName("정상적인 요청이라면")
		class NormalTest {

			@Test
			@DisplayName("모든 Goal들이 조회된다.")
			void find_all_goals() throws Exception {
			    //given
				Long memberId = 1L;
				BaseResponse<List<GoalResponse>> baseResponse = new BaseResponse<>(FIND_ALL_GOALS_SUCCESS, goals);
				when(goalService.findAll()).thenReturn(goals);

				//when
				ResultActions resultActions = mockMvc.perform(get("/goal")
					.header("Authorization", "Bearer " + jwtProvider.generateAccessToken(memberId))
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andDo(print());

				//then
				resultActions.andExpect(status().isOk())
					.andExpect(content().string(objectMapper.writeValueAsString(baseResponse)));
			}
		}
	}

}
