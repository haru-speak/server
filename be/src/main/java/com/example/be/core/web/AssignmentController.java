package com.example.be.core.web;

import static com.example.be.common.response.ResponseCodeAndMessages.CREATE_ASSIGNMENT_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.DELETE_ASSIGNMENT_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.FIND_ASSIGNMENT_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.FIND_DETAIL_ASSIGNMENT_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.MODIFY_ASSIGNMENT_SUCCESS;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.AssignmentService;
import com.example.be.core.application.dto.request.AssignmentModifyRequest;
import com.example.be.core.application.dto.request.AssignmentRequest;
import com.example.be.core.application.dto.response.AssignmentDetailResponse;
import com.example.be.core.application.dto.response.AssignmentResponse;
import com.example.be.core.application.dto.response.AssignmentsResponse;
import com.example.be.oauth.Login;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {

  private final AssignmentService assignmentService;

  public AssignmentController(AssignmentService assignmentService) {
    this.assignmentService = assignmentService;
  }

  @PostMapping
  @ApiOperation(value = "과제 생성입니다.")
  public BaseResponse<AssignmentDetailResponse> create(@Login @Positive final Long memberId,
      @RequestBody @Valid final AssignmentRequest assignmentRequest) {
    AssignmentDetailResponse response = assignmentService.create(assignmentRequest, memberId);
    return new BaseResponse<>(CREATE_ASSIGNMENT_SUCCESS, response);
  }

  @GetMapping("/{assignmentId}")
  @ApiOperation(value = "과제 상세 조회입니다.")
  public BaseResponse<AssignmentResponse> findById(@Login @Positive final Long memberId,
      @PathVariable @Positive final Long assignmentId) {
    AssignmentResponse response = assignmentService.findById(memberId, assignmentId);
    return new BaseResponse<>(FIND_DETAIL_ASSIGNMENT_SUCCESS, response);
  }

  @PutMapping("/{assignmentId}")
  @ApiOperation(value = "과제 수정입니다.")
  public BaseResponse<AssignmentDetailResponse> modify(@Login @Positive final Long memberId,
      @PathVariable @Positive final Long assignmentId,
    @RequestBody @Valid final AssignmentModifyRequest assignmentModifyRequest) {
    AssignmentDetailResponse response = assignmentService.modify(memberId, assignmentId, assignmentModifyRequest);
    return new BaseResponse<>(MODIFY_ASSIGNMENT_SUCCESS, response);
  }

  @DeleteMapping("/{assignmentId}")
  @ApiOperation(value = "과제 삭제입니다.")
  public BaseResponse<Void> delete(@Login @Positive final Long memberId,
      @PathVariable final Long assignmentId) {
    assignmentService.delete(memberId, assignmentId);
    return new BaseResponse<>(DELETE_ASSIGNMENT_SUCCESS, null);
  }
}
