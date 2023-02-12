package com.example.be.core.application.member;

import com.example.be.core.application.dto.response.GoalResponse;
import com.example.be.core.domain.member.goal.Goal;
import com.example.be.core.repository.member.goal.GoalRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GoalService {

	private final GoalRepository goalRepository;

	public GoalService(GoalRepository goalRepository) {
		this.goalRepository = goalRepository;
	}


	public List<GoalResponse> findAll() {

		List<Goal> goals = goalRepository.findAll();
		return goals.stream()
			.map(GoalResponse::of)
			.collect(Collectors.toList());
	}
}
