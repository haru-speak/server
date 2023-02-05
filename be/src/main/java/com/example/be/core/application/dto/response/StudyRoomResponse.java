package com.example.be.core.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class StudyRoomResponse {

    @Schema(type = "Long", description = "스터디 ID, NOT NULL")
    private final Long studyId;

    @Schema(type = "Long", description = "과제 ID, NOT NULL")
    private final Long assignmentId;

    @Schema(type = "String", description = "과제 제목, NOT NULL")
    private final String assignmentTitle;

    @Schema(type = "String", description = "음성 녹음, NOT NULL")
    private final String voiceRecord;

    @Schema(type = "String", description = "음성 텍스트, NOT NULL")
    private final String voiceText;

    @Schema(type = "date", description = "제출 기한, NOT NULL")
    private final LocalDateTime deadline;

    public StudyRoomResponse(Long studyId, Long assignmentId, String assignmentTitle,
        String voiceRecord, String voiceText, LocalDateTime deadline) {
        this.studyId = studyId;
        this.assignmentId = assignmentId;
        this.assignmentTitle = assignmentTitle;
        this.voiceRecord = voiceRecord;
        this.voiceText = voiceText;
        this.deadline = deadline;
    }
}
