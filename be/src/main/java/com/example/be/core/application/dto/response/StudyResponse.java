package com.example.be.core.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class StudyResponse {

  @Schema(type = "Long", description = "스터디 ID, NOT NULL")
  private final Long studyId;

  @Schema(type = "String", description = "제목, NOT NULL")
  private final String title;

  @Schema(type = "content", description = "내용, NOT NULL")
  private final String content;

  @Schema(type = "int", description = "최대 인원, NOT NULL")
  private final Integer maxCapacity;

  @Schema(type = "int", description = "최소 인원, NOT NULL")
  private final Integer minCapacity;

  @Schema(type = "String", description = "포스터 이미지, NOT NULL")
  private final String posterImage;

  @Schema(type = "Int", description = "스터디 멤버 수, NOT NULL")
  private final Integer memberCount;

  @Schema(type = "Boolean", description = "관심 여부, NOT NULL")
  private final Boolean isInterested;

  @Schema(type = "LocalDateTime", description = "생성 시간, NOT NULL")
  private final LocalDateTime createdAt;

  public StudyResponse(Long studyId, String title, String content, Integer maxCapacity,
      Integer minCapacity, String posterImage, Integer memberCount, Boolean isInterested,
      LocalDateTime createdAt) {
    this.studyId = studyId;
    this.title = title;
    this.content = content;
    this.maxCapacity = maxCapacity;
    this.minCapacity = minCapacity;
    this.posterImage = posterImage;
    this.memberCount = memberCount;
    this.isInterested = isInterested;
    this.createdAt = createdAt;
  }
}
