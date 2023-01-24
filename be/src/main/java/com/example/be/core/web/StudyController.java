package com.example.be.core.web;

import static com.example.be.common.response.ResponseCodeAndMessages.CREATE_STUDY_SUCCESS;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.StudyService;
import com.example.be.core.application.dto.request.StudyRequest;
import com.example.be.core.application.dto.response.StudyDetailResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
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
  public BaseResponse<StudyDetailResponse> create(
      @RequestBody final StudyRequest studyRequest) {
      StudyDetailResponse response = studyService.create(studyRequest, 1L);
      return new BaseResponse<>(CREATE_STUDY_SUCCESS, response);
  }
}
