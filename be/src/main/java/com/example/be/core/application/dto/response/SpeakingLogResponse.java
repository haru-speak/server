package com.example.be.core.application.dto.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class SpeakingLogResponse {

	@Schema(type = "Long", description = "스피킹 로그 ID, NOT NULL")
	@NotNull
	@Positive
	private final Long speakingLogId;

	@Schema(type = "String", description = "제목, NOT NULL")
	@NotBlank
	private final String title;

	@Schema(type = "String", description = "음성 녹음 URL, NOT NULL")
	@NotBlank
	private final String voiceRecord;

	@Schema(type = "Int", description = "좋아요 개수, NOT NULL")
	@NotNull
	private final Integer likeCount;

	@Schema(type = "Int", description = "댓글 개수, NOT NULL")
	@NotNull
	private final Integer commentCount;

	@Schema(type = "Boolean", description = "좋아요 여부, NOT NULL")
	@NotNull
	private final Boolean isLiked;

	@Schema(type = "String", description = "프로필 이미지, NOT NULL")
	@NotNull
	private final String profileImage;

	@Schema(type = "Long", description = "멤버 ID, NOT NULL")
	@NotNull
	@Positive
	private final Long memberId;

	public SpeakingLogResponse(Long speakingLogId, String title, String voiceRecord,
		Integer likeCount,
		Integer commentCount, Boolean isLiked, String profileImage, Long memberId) {
		this.speakingLogId = speakingLogId;
		this.title = title;
		this.voiceRecord = voiceRecord;
		this.likeCount = likeCount;
		this.commentCount = commentCount;
		this.isLiked = isLiked;
		this.profileImage = profileImage;
		this.memberId = memberId;
	}
}
