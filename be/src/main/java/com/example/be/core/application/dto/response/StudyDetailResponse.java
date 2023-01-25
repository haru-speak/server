package com.example.be.core.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyDetailResponse {

  @Schema(type = "Long", description = "스터디 ID, NOT NULL")
  @NotNull
  @Positive
  private Long studyId;

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

  @Schema(type = "int", description = "최대 인원, NOT NULL")
  @NotBlank
  private Integer capacity;

  @Schema(type = "String", description = "규칙, NOT NULL")
  @NotBlank
  private String rule;

  @Schema(type = "date", description = "주 횟수, NOT NULL")
  @NotBlank
  private LocalDate timePerWeek;

  @Schema(type = "String", description = "포스터 이미지, NOT NULL")
  @NotBlank
  private String posterImage;

  @Schema(type = "Int", description = "좋아요 개수, NOT NULL")
  @NotNull
  private Integer likeCount;

  @Schema(type = "Boolean", description = "좋아요 여부, NOT NULL")
  @NotNull
  private Boolean isLiked;

  @Schema(type = "List<Comment>", description = "댓글 리스트, NULLABLE")
  private List<CommentResponse> comments;

  public StudyDetailResponse(Long studyId, String title, String content, Integer level, String language, String goal, String certificate, Integer capacity, String rule, LocalDate timePerWeek, String posterImage, Integer likeCount, Boolean isLiked, List<CommentResponse> comments) {
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
