package com.example.be.core.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class SpeakingLogResponse {

	@Schema(type = "Long", description = "스피킹 로그 ID, NOT NULL")
	private final Long speakingLogId;

	@Schema(type = "String", description = "제목, NOT NULL")
	private final String title;

	@Schema(type = "String", description = "음성 녹음 URL, NOT NULL")
	private final String voiceRecord;

	@Schema(type = "String", description = "음성 텍스트, NOT NULL")
	private final String voiceText;

	@Schema(type = "Int", description = "좋아요 개수, NOT NULL")
	private final Integer likeCount;

	@Schema(type = "Int", description = "댓글 개수, NOT NULL")
	private final Integer commentCount;

	@Schema(type = "Boolean", description = "좋아요 여부, NOT NULL")
	private final Boolean isLiked;

	@Schema(type = "String", description = "프로필 이미지, NOT NULL")
	private final String profileImage;

	@Schema(type = "Long", description = "멤버 ID, NOT NULL")
	private final Long memberId;

	public SpeakingLogResponse(Long speakingLogId, String title, String voiceRecord,
		String voiceText,
		Integer likeCount, Integer commentCount, Boolean isLiked, String profileImage,
		Long memberId) {
		this.speakingLogId = speakingLogId;
		this.title = title;
		this.voiceRecord = voiceRecord;
		this.voiceText = voiceText;
		this.likeCount = likeCount;
		this.commentCount = commentCount;
		this.isLiked = isLiked;
		this.profileImage = profileImage;
		this.memberId = memberId;
	}
}
