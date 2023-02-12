package com.example.be.core.web.member;

import static com.example.be.common.response.ResponseCodeAndMessages.FIND_ALL_GOALS_SUCCESS;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.member.GoalService;
import com.example.be.core.application.dto.response.GoalResponse;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goal")
public class GoalController {

	private final GoalService goalService;

	public GoalController(GoalService goalService) {
		this.goalService = goalService;
	}

	@GetMapping
	@ApiOperation(value = "목표 전체 조회입니다.")
	public BaseResponse<List<GoalResponse>> findAll() {
		List<GoalResponse> response = goalService.findAll();
		return new BaseResponse<>(FIND_ALL_GOALS_SUCCESS, response);
	}
}
