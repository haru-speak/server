package com.example.be.core.application.goal_subject;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.be.core.application.member.GoalService;
import com.example.be.core.application.InitServiceTest;
import com.example.be.core.application.dto.response.GoalResponse;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("서비스 테스트 : Goal 전체 조회")
class GoalFindAllTest extends InitServiceTest {

	@Autowired
	private GoalService goalService;

	@Nested
	@DisplayName("목표를 전체 조회할 때")
	class FindAllTest {

		@Nested
		@DisplayName("정상적인 요청이라면")
		class NormalTest {

			@Test
			void find_all_goals_test(){
			    //given & when
				List<GoalResponse> goals = goalService.findAll();

				//then
				assertThat(goals).hasSize(5);
				assertThat(goals.get(0).getGoalId()).isEqualTo(1L);
				assertThat(goals.get(1).getGoalId()).isEqualTo(2L);
				assertThat(goals.get(2).getGoalId()).isEqualTo(3L);
				assertThat(goals.get(3).getGoalId()).isEqualTo(4L);
				assertThat(goals.get(4).getGoalId()).isEqualTo(5L);
				assertThat(goals.get(1).getContent()).isEqualTo("다른 사람들의 피드백!");
			}
		}
	}

}
