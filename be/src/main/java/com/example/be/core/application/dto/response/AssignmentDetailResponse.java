package com.example.be.core.application.dto.response;

import com.example.be.core.domain.assignment.Assignment;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class AssignmentDetailResponse {

  @Schema(type = "Long", description = "과제 ID, NOT NULL")
  private final Long assignmentId;

  @Schema(type = "String", description = "과제 제목, NOT NULL")
  private final String title;

  @Schema(type = "String", description = "과제 내용, NOT NULL")
  private final String content;

  @Schema(type = "String", description = "음성 기록 URL, NOT NULL")
  private final String voiceRecord;

  @Schema(type = "date", description = "과제 생성 날짜 , NOT NULL")
  private final LocalDateTime createdAt;

  @Schema(type = "date", description = "과제 제출 기한, NOT NULL")
  private final LocalDateTime deadline;

  @Schema(type = "boolean", description = "과제 제출 여부, NOT NULL")
  private final Boolean isSubmitted;

  public AssignmentDetailResponse(Long assignmentId, String title, String content,
      String voiceRecord, LocalDateTime createdAt, LocalDateTime deadline,
      Boolean isSubmitted) {
    this.assignmentId = assignmentId;
    this.title = title;
    this.content = content;
    this.voiceRecord = voiceRecord;
    this.createdAt = createdAt;
    this.deadline = deadline;
    this.isSubmitted = isSubmitted;
  }

  public static AssignmentDetailResponse create(Assignment assignment) {
    return new AssignmentDetailResponse(
        assignment.getId(),
        assignment.getTitle(),
        assignment.getContent(),
        assignment.getVoiceRecord(),
        assignment.getCreatedAt(),
        assignment.getDeadLine(),
        Boolean.FALSE
    );
  }
}
