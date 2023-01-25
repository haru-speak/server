package com.example.be.core.web;

import static com.example.be.common.response.ResponseCodeAndMessages.CREATE_STUDY_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.DELETE_STUDY_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.FIND_DETAIL_STUDY_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.FIND_STUDY_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.MODIFY_STUDY_SUCCESS;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.StudyService;
import com.example.be.core.application.dto.request.StudyRequest;
import com.example.be.core.application.dto.response.StudiesResponse;
import com.example.be.core.application.dto.response.StudyDetailResponse;
import com.example.be.core.domain.study.StudyType;
import io.swagger.annotations.ApiOperation;
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
  public BaseResponse<StudyDetailResponse> create(@RequestBody final StudyRequest studyRequest) {
    StudyDetailResponse response = studyService.create(studyRequest, 1L);
    return new BaseResponse<>(CREATE_STUDY_SUCCESS, response);
  }

  @GetMapping
  @ApiOperation(value = "스터디 타입에 따른 전체 조회입니다")
  public BaseResponse<StudiesResponse> find(StudyType type) {
    StudiesResponse response = studyService.find(type);
    return new BaseResponse<>(FIND_STUDY_SUCCESS, response);
  }

  @GetMapping("/{studyId}")
  @ApiOperation(value = "스터디 상세 조회입니다.")
  public BaseResponse<StudyDetailResponse> findById(@PathVariable final Long studyId) {
    StudyDetailResponse response = studyService.findById(studyId);
    return new BaseResponse<>(FIND_DETAIL_STUDY_SUCCESS, response);
  }

  @PutMapping("/{studyId}")
  @ApiOperation(value = "스터디 수정입니다.")
  public BaseResponse<StudyDetailResponse> modify(
      @PathVariable final Long studyId,
      @RequestBody final StudyRequest studyRequest) {
    StudyDetailResponse response = studyService.modify(studyId, studyRequest);
    return new BaseResponse<>(MODIFY_STUDY_SUCCESS, response);
  }

  @DeleteMapping("/{studyId}")
  @ApiOperation(value = "스터디 삭제입니다.")
  public BaseResponse<Void> delete(@PathVariable Long studyId) {
    studyService.delete(studyId);
    return new BaseResponse<>(DELETE_STUDY_SUCCESS, null);
  }
}
