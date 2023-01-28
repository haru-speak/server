package com.example.be.core.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class StudyDetailResponse {

  @Schema(type = "Long", description = "스터디 ID, NOT NULL")
  @Positive
  private final Long studyId;

  @Schema(type = "String", description = "제목, NOT NULL")
  private final String title;

  @Schema(type = "String", description = "내용, NOT NULL")
  private final String content;

  @Schema(type = "int", description = "레벨, NOT NULL")
  private final Integer level;

  @Schema(type = "String", description = "언어, NOT NULL")
  private final String language;

  @Schema(type = "String", description = "목표, NOT NULL")
  private final String goal;

  @Schema(type = "String", description = "자격증, NOT NULL")
  private final String certificate;

  @Schema(type = "int", description = "최대 인원, NOT NULL")
  private final Integer capacity;

  @Schema(type = "String", description = "규칙, NOT NULL")
  private final String rule;

  @Schema(type = "Integer", description = "주 횟수, NOT NULL")
  private final Integer timePerWeek;

  @Schema(type = "String", description = "포스터 이미지, NOT NULL")
  private final String posterImage;

  @Schema(type = "Int", description = "좋아요 개수, NOT NULL")
  private final Integer likeCount;

  @Schema(type = "Boolean", description = "좋아요 여부, NOT NULL")
  private final Boolean isLiked;

  @Schema(type = "List<StudyCommentResponse>", description = "댓글 리스트, NULLABLE")
  private final List<StudyCommentResponse> comments;

  public StudyDetailResponse(Long studyId, String title, String content, Integer level, String language, String goal, String certificate, Integer capacity, String rule, Integer timePerWeek, String posterImage, Integer likeCount, Boolean isLiked, List<StudyCommentResponse> comments) {
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
    this.posterImage = posterImage;
    this.likeCount = likeCount;
    this.isLiked = isLiked;
    this.comments = comments;
  }
}
