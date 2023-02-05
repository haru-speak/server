package com.example.be.core.web;

import static com.example.be.common.response.ResponseCodeAndMessages.CREATE_SPEAKING_LOG_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.DELETE_SPEAKING_LOG_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.FIND_DETAIL_SPEAKING_LOG_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.FIND_SPEAKING_LOG_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.MODIFY_SPEAKING_LOG_SUCCESS;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.SpeakingLogService;
import com.example.be.core.application.dto.request.SpeakingLogConditionRequest;
import com.example.be.core.application.dto.request.SpeakingLogModifyRequest;
import com.example.be.core.application.dto.request.SpeakingLogRequest;
import com.example.be.core.application.dto.response.SpeakingLogDetailResponse;
import com.example.be.core.application.dto.response.SpeakingLogsResponse;
import com.example.be.oauth.Login;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/speaking-log")
public class SpeakingLogController {

	private final SpeakingLogService speakingLogService;

	public SpeakingLogController(SpeakingLogService speakingLogService) {
		this.speakingLogService = speakingLogService;
	}

	@PostMapping
	@ApiOperation(value = "스피킹 로그 생성입니다. [RequestBody : SpeakingLogRequest]")
	public BaseResponse<SpeakingLogDetailResponse> create(@Login @Positive final Long memberId,
		@RequestBody @Valid final SpeakingLogRequest speakingLogRequest) {
		SpeakingLogDetailResponse response = speakingLogService.create(memberId, speakingLogRequest);
		return new BaseResponse<>(CREATE_SPEAKING_LOG_SUCCESS, response);
	}

	@GetMapping
	@ApiOperation(value = "스피킹 로그 타입에 따른 전체 조회입니다.")
	public BaseResponse<SpeakingLogsResponse> find(@Login @Positive final Long memberId,
		@Valid final SpeakingLogConditionRequest speakingLogConditionRequest) {
		SpeakingLogsResponse response = speakingLogService.find(memberId, speakingLogConditionRequest);
		return new BaseResponse<>(FIND_SPEAKING_LOG_SUCCESS, response);
	}

	@GetMapping("/{speakingLogId}")
	@ApiOperation(value = "스피킹 로그 상세 조회입니다.")
	public BaseResponse<SpeakingLogDetailResponse> findById(@Login @Positive final Long memberId,
		@PathVariable @Positive final Long speakingLogId) {
		SpeakingLogDetailResponse response = speakingLogService.findById(memberId, speakingLogId);
		return new BaseResponse<>(FIND_DETAIL_SPEAKING_LOG_SUCCESS, response);
	}

	@PutMapping("/{speakingLogId}")
	@ApiOperation(value = "스피킹 로그 수정입니다. [RequestBody : SpeakingLogModifyRequest]")
	public BaseResponse<SpeakingLogDetailResponse> modify(@Login @Positive final Long memberId,
		@PathVariable @Positive final Long speakingLogId,
		@RequestBody @Valid final SpeakingLogModifyRequest speakingLogModifyRequest) {
		SpeakingLogDetailResponse response = speakingLogService.modify(memberId, speakingLogId, speakingLogModifyRequest);
		return new BaseResponse<>(MODIFY_SPEAKING_LOG_SUCCESS, response);
	}

	@DeleteMapping("/{speakingLogId}")
	@ApiOperation(value = "스피킹 로그 삭제입니다.")
	public BaseResponse<Void> delete(@Login @Positive final Long memberId,
		@PathVariable @Positive final Long speakingLogId) {
		speakingLogService.delete(memberId, speakingLogId);
		return new BaseResponse<>(DELETE_SPEAKING_LOG_SUCCESS, null);
	}
}
