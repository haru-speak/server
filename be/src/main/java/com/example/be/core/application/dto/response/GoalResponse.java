package com.example.be.core.application.dto.response;

import com.example.be.core.domain.member.goal.Goal;
import lombok.Getter;

@Getter
public class GoalResponse {

	private final Long goalId;
	private final String content;

	private GoalResponse(Long goalId, String content) {
		this.goalId = goalId;
		this.content = content;
	}

	public static GoalResponse of(Goal goal) {
		return new GoalResponse(goal.getId(), goal.getContent());
	}
}
