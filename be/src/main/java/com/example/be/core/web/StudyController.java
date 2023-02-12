package com.example.be.core.web;

import static com.example.be.common.response.ResponseCodeAndMessages.CREATE_STUDY_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.DELETE_STUDY_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.FIND_DETAIL_STUDY_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.FIND_ALL_STUDY_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.FIND_STUDY_PREVIEW_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.INTEREST_STUDY_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.MODIFY_STUDY_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.NOT_INTEREST_STUDY_SUCCESS;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.StudyService;
import com.example.be.core.application.dto.request.StudyConditionRequest;
import com.example.be.core.application.dto.request.StudyPreviewConditionRequest;
import com.example.be.core.application.dto.request.StudyRequest;
import com.example.be.core.application.dto.response.StudiesResponse;
import com.example.be.core.application.dto.response.StudyDetailResponse;
import com.example.be.core.application.dto.response.StudyPreviewResponse;
import com.example.be.core.application.dto.response.StudyPreviewsResponse;
import com.example.be.oauth.Login;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/study")
public class StudyController {

  private final StudyService studyService;

  public StudyController(StudyService studyService) {
    this.studyService = studyService;
  }

  @PostMapping
  @ApiOperation(value = "스터디 생성입니다.")
  public BaseResponse<StudyDetailResponse> create(@Login @Positive final Long memberId, @RequestBody final StudyRequest studyRequest) {
    StudyDetailResponse response = studyService.create(studyRequest, memberId);
    return new BaseResponse<>(CREATE_STUDY_SUCCESS, response);
  }

  @GetMapping
  @ApiOperation(value = "스터디 타입에 따른 전체 조회입니다")
  public BaseResponse<StudiesResponse> find(@Login @Positive final Long memberId, final StudyConditionRequest studyConditionRequest) {
    StudiesResponse response = studyService.find(memberId,studyConditionRequest);
    return new BaseResponse<>(FIND_ALL_STUDY_SUCCESS, response);
  }

  @GetMapping("/{studyId}")
  @ApiOperation(value = "스터디 상세 조회입니다.")
  public BaseResponse<StudyDetailResponse> findById(@Login @Positive final Long memberId, @PathVariable final Long studyId) {
    StudyDetailResponse response = studyService.findById(memberId,studyId);
    return new BaseResponse<>(FIND_DETAIL_STUDY_SUCCESS, response);
  }

  @GetMapping("/preview")
  @ApiOperation(value = "스터디 미리보기 조회입니다.")
  public BaseResponse<StudyPreviewsResponse> findPreview(@Login @Positive final Long memberId, final StudyPreviewConditionRequest studyPreviewConditionRequest) {
    StudyPreviewsResponse response = studyService.findPreview(memberId, studyPreviewConditionRequest);
    return new BaseResponse<>(FIND_STUDY_PREVIEW_SUCCESS, response);
  }

  @PutMapping("/{studyId}")
  @ApiOperation(value = "스터디 수정입니다.")
  public BaseResponse<StudyDetailResponse> modify(
      @Login @Positive final Long memberId,
      @PathVariable final Long studyId,
      @RequestBody final StudyRequest studyRequest) {
    StudyDetailResponse response = studyService.modify(memberId, studyId, studyRequest);
    return new BaseResponse<>(MODIFY_STUDY_SUCCESS, response);
  }

  @DeleteMapping("/{studyId}")
  @ApiOperation(value = "스터디 삭제입니다.")
  public BaseResponse<Void> delete(
      @Login @Positive final Long memberId,
      @PathVariable final Long studyId) {
    studyService.delete(memberId, studyId);
    return new BaseResponse<>(DELETE_STUDY_SUCCESS, null);
  }

  @PostMapping("/interest/{studyId}")
  public BaseResponse<Void> interest(
      @Login @Positive final Long memberId,
      @PathVariable final Long studyId) {
    studyService.interest(memberId, studyId);
    return new BaseResponse<>(INTEREST_STUDY_SUCCESS, null);
  }

  @DeleteMapping("/interest/{studyId}")
  public BaseResponse<Void> notInterest(
      @Login @Positive final Long memberId,
      @PathVariable final Long studyId) {
    studyService.notInterest(memberId, studyId);
    return new BaseResponse<>(NOT_INTEREST_STUDY_SUCCESS, null);
  }
}
