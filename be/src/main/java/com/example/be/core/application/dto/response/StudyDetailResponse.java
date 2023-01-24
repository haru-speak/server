package com.example.be.core.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class StudyDetailResponse {

  @Schema(type = "Long", description = "스터디 ID, NOT NULL")
  @NotNull
  @Positive
  private final Long studyId;

  @Schema(type = "String", description = "제목, NOT NULL")
  @NotBlank
  private final String title;

  @Schema(type = "String", description = "내용, NOT NULL")
  @NotBlank
  private final String content;

  @Schema(type = "int", description = "레벨, NOT NULL")
  @NotBlank
  private final Integer level;

  @Schema(type = "String", description = "언어, NOT NULL")
  @NotBlank
  private final String language;

  @Schema(type = "String", description = "목표, NOT NULL")
  @NotBlank
  private final String goal;

  @Schema(type = "String", description = "자격증, NOT NULL")
  @NotBlank
  private final String certificate;

  @Schema(type = "String", description = "최대 인원, NOT NULL")
  @NotBlank
  private final String capacity;

  @Schema(type = "String", description = "규칙, NOT NULL")
  @NotBlank
  private final String rule;

  @Schema(type = "String", description = "주 횟수, NOT NULL")
  @NotBlank
  private final String timePerWeek;

  @Schema(type = "String", description = "포스터 이미지, NOT NULL")
  @NotBlank
  private final String posterImg;

  @Schema(type = "Int", description = "좋아요 개수, NOT NULL")
  @NotNull
  private final Integer likeCount;

  @Schema(type = "Boolean", description = "좋아요 여부, NOT NULL")
  @NotNull
  private final Boolean isLiked;

  @Schema(type = "List<Comment>", description = "댓글 리스트, NULLABLE")
  private final List<CommentResponse> comments;

  public StudyDetailResponse(Long studyId, String title, String content, Integer level, String language, String goal, String certificate, String capacity, String rule, String timePerWeek, String posterImg, Integer likeCount, Boolean isLiked, List<CommentResponse> comments) {
    this.studyId = studyId;
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
    this.likeCount = likeCount;
    this.isLiked = isLiked;
    this.comments = comments;
  }
}
