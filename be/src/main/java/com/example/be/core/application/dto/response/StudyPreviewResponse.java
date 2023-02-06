package com.example.be.core.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class StudyPreviewResponse {

    @Schema(type = "Long", description = "스터디 ID, NOT NULL")
    private final Long studyId;

    @Schema(type = "String", description = "제목, NOT NULL")
    private final String title;

    @Schema(type = "content", description = "내용, NOT NULL")
    private final String content;

    @Schema(type = "String", description = "포스터 이미지, NOT NULL")
    private final String posterImage;

    @Schema(type = "LocalDateTime", description = "생성 시간, NOT NULL")
    private final LocalDateTime createdAt;

    @Schema(type = "MemberProfilesResponse", description = "스터디에 참여중인 멤버들의 프로필 이미지, NOT NULL")
    private final MemberProfilesResponse memberProfiles;

    public StudyPreviewResponse(Long studyId, String title, String content, String posterImage,
        LocalDateTime createdAt, MemberProfilesResponse memberProfiles) {
        this.studyId = studyId;
        this.title = title;
        this.content = content;
        this.posterImage = posterImage;
        this.createdAt = createdAt;
        this.memberProfiles = memberProfiles;
    }
}
