package com.example.be.core.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
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

  @Schema(type = "String", description = "목표, NOT NULL")
  @NotBlank
  private String goal;

  @Schema(type = "String", description = "자격증, NOT NULL")
  @NotBlank
  private String certificate;

  @Schema(type = "String", description = "최대 인원, NOT NULL")
  @NotBlank
  private String capacity;

  @Schema(type = "String", description = "규칙, NOT NULL")
  @NotBlank
  private String rule;

  @Schema(type = "String", description = "주 횟수, NOT NULL")
  @NotBlank
  private String timePerWeek;

  @Schema(type = "String", description = "포스터 이미지, NOT NULL")
  @NotBlank
  private String posterImg;

  private StudyRequest() {}

  public StudyRequest(String title, String content, Integer level, String language, String goal,
      String certificate, String capacity, String rule, String timePerWeek, String posterImg) {
    this.title = title;
    this.content = content;
    this.level = level;
    this.language = language;
    this.goal = goal;
    this.certificate = certificate;
    this.capacity = capacity;
    this.rule = rule;
    this.timePerWeek = timePerWeek;
    this.posterImg = posterImg;
  }
}
