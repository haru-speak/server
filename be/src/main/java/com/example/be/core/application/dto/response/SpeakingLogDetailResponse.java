package com.example.be.core.application.dto.response;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema
@Getter
public class SpeakingLogDetailResponse {

	@Schema(type = "Long", description = "멤버 ID, NOT NULL")
	@NotNull
	@Positive
	private final Long memberId;

	@Schema(type = "String", description = "제목, NOT NULL")
	@NotBlank
	private final String title;

	@Schema(type = "String", description = "음성 녹음 URL, NOT NULL")
	@NotBlank
	private final String voiceRecord;

	@Schema(type = "String", description = "음성 텍스트 URL, NOT NULL")
	@NotBlank
	private final String voiceText;

	@Schema(type = "Int", description = "좋아요 개수, NOT NULL")
	@NotNull
	private final Integer likeCount;

	@NotNull
	private final Integer commentCount;

	@Schema(type = "Boolean", description = "좋아요 여부, NOT NULL")
	@NotNull
	private final Boolean isLiked;
	@Schema(type = "List<Comment>", description = "댓글 리스트, NULLABLE")
	private final List<CommentResponse> comments;

	public SpeakingLogDetailResponse(Long memberId, String title, String voiceRecord, String voiceText,
		Integer likeCount, Integer commentCount, Boolean isLiked, List<CommentResponse> comments) {
		this.memberId = memberId;
		this.title = title;
		this.voiceRecord = voiceRecord;
		this.voiceText = voiceText;
		this.likeCount = likeCount;
		this.commentCount = commentCount;
		this.isLiked = isLiked;
		this.comments = comments;
	}
}
