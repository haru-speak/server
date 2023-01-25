package com.example.be.core.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyResponse {

  @Schema(type = "Long", description = "스터디 ID, NOT NULL")
  @NotNull
  private Long studyId;

  @Schema(type = "String", description = "제목, NOT NULL")
  @NotNull
  private String title;

  @Schema(type = "content", description = "내용, NOT NULL")
  @NotNull
  private String content;

  @Schema(type = "String", description = "포스터 이미지, NOT NULL")
  @NotNull
  private String posterImage;

  @Schema(type = "Int", description = "좋아요 개수, NOT NULL")
  @NotNull
  private Integer likeCount;

  @Schema(type = "Int", description = "댓글 개수, NOT NULL")
  @NotNull
  private Integer commentCount;

  @Schema(type = "Boolean", description = "좋아요 여부, NOT NULL")
  @NotNull
  private Boolean isLiked;

  public StudyResponse(Long studyId, String title, String content, String posterImage,
      Integer likeCount, Integer commentCount, Boolean isLiked) {
    this.studyId = studyId;
    this.title = title;
    this.content = content;
    this.posterImage = posterImage;
    this.likeCount = likeCount;
    this.commentCount = commentCount;
    this.isLiked = isLiked;
  }
}
