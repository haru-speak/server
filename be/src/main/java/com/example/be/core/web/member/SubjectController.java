package com.example.be.core.web.member;

import static com.example.be.common.response.ResponseCodeAndMessages.FIND_ALL_SUBJECTS_SUCCESS;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.dto.response.SubjectResponse;
import com.example.be.core.application.member.SubjectService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subject")
public class SubjectController {

	private final SubjectService subjectService;

	public SubjectController(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	@GetMapping
	@ApiOperation(value = "주제 전체 조회입니다.")
	public BaseResponse<List<SubjectResponse>> findAll() {
		List<SubjectResponse> response = subjectService.findAll();
		return new BaseResponse<>(FIND_ALL_SUBJECTS_SUCCESS, response);
	}
}
