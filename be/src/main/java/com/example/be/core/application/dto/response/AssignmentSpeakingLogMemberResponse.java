package com.example.be.core.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

public class AssignmentSpeakingLogMemberResponse {

  @Schema(type = "Long", description = "과제 스피킹 로그 ID, NOT NULL")
  private Long AssignmentSpeakingLogId;

  @Schema(type = "Long", description = "멤버 ID, NOT NULL")
  private Long memberId;

  @Schema(type = "String", description = "멤버 프로필 URL, NOT NULL")
  private String memberProfile;

  @Schema(type = "String", description = "과제 스피킹 로그 제목, NOT NULL")
  private String title;

  @Schema(type = "String", description = "음성 녹음 URL, NOT NULL")
  private String voiceRecord;

  @Schema(type = "String", description = "음성 텍스트 URL, NOT NULL")
  private String voiceText;

  @Schema(type = "boolean", description = "좋아요 여부, NOT NULL")
  private Boolean isFavorite;

  @Schema(type = "int", description = "좋아요 수, NOT NULL")
  private Integer favoriteCount;

  @Schema(type = "int", description = "댓글 수, NOT NULL")
  private Integer commentCount;

  @Schema(type = "int", description = "생성 날짜, NOT NULL")
  private LocalDateTime createdAt;

  public AssignmentSpeakingLogMemberResponse(Long assignmentSpeakingLogId, Long memberId,
      String memberProfile, String title, String voiceRecord, String voiceText, Boolean isFavorite,
      Integer favoriteCount, Integer commentCount, LocalDateTime createdAt) {
    AssignmentSpeakingLogId = assignmentSpeakingLogId;
    this.memberId = memberId;
    this.memberProfile = memberProfile;
    this.title = title;
    this.voiceRecord = voiceRecord;
    this.voiceText = voiceText;
    this.isFavorite = isFavorite;
    this.favoriteCount = favoriteCount;
    this.commentCount = commentCount;
    this.createdAt = createdAt;
  }
}
