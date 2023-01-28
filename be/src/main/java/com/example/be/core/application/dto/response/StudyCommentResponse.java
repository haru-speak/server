package com.example.be.core.application.dto.response;

import com.example.be.core.domain.speakinglog.Comment;
import com.example.be.core.domain.study.StudyComment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class StudyCommentResponse {

    @Schema(type = "Long", description = "댓글 ID, NOT NULL")
    private final Long studyCommentId;

    @Schema(type = "Long", description = "멤버 ID, NOT NULL")
    private final Long memberId;

    @Schema(type = "String", description = "멤버 닉네임, NOT NULL")
    private final String nickname;

    @Schema(type = "String", description = "댓글 내용, NOT NULL")
    private final String content;

    private StudyCommentResponse(Long studyCommentId, Long memberId, String nickname,
        String content) {
        this.studyCommentId = studyCommentId;
        this.memberId = memberId;
        this.nickname = nickname;
        this.content = content;
    }

    public static StudyCommentResponse from(StudyComment studyComment) {
        return new StudyCommentResponse(
            studyComment.getId(),
            studyComment.getMember().getId(),
            studyComment.getMember().getNickname(),
            studyComment.getContent()
        );
    }
}
