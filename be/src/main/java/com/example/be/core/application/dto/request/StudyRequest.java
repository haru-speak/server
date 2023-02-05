package com.example.be.core.application.dto.request;

import com.example.be.core.domain.study.StudyDay;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.EnumSet;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudyRequest {

  @Schema(type = "String", description = "제목, NOT NULL")
  @NotBlank
  private String title;

  @Schema(type = "String", description = "내용, NOT NULL")
  @NotBlank
  private String content;

  @Schema(type = "int", description = "레벨, NOT NULL")
  @NotBlank
  private Integer level;

  @Schema(type = "String", description = "언어, NOT NULL")
  @NotBlank
  private String language;

  @Schema(type = "String", description = "스피킹 시험, NULL")
  private String speakingTest;

  @Schema(type = "String", description = "스피킹 시험에서 받는 등급, NULL")
  private String grade;

  @Schema(type = "int", description = "최대 인원, NOT NULL")
  @NotBlank
  private Integer maxCapacity;

  @Schema(type = "int", description = "최소 인원, NOT NULL")
  @NotBlank
  private Integer minCapacity;

  @Schema(type = "String", description = "대면, 비대면 규칙, NOT NULL")
  @NotBlank
  private String rule;

  @Schema(type = "String", description = "지역, NULL")
  private String region;

  @Schema(type = "String", description = "스터디 하는 요일, NOT NULL")
  @NotBlank
  private String studyDay;

  @Schema(type = "String", description = "포스터 이미지, NOT NULL")
  @NotBlank
  private String posterImage;

  public StudyRequest(String title, String content, Integer level, String language,
      String speakingTest, String grade, Integer maxCapacity, Integer minCapacity, String rule,
      String region, String studyDay, String posterImage) {
    this.title = title;
    this.content = content;
    this.level = level;
    this.language = language;
    this.speakingTest = speakingTest;
    this.grade = grade;
    this.maxCapacity = maxCapacity;
    this.minCapacity = minCapacity;
    this.rule = rule;
    this.region = region;
    this.studyDay = studyDay;
    this.posterImage = posterImage;
  }
}
