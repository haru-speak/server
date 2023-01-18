package com.example.be.core.application.dto.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class SpeakingLogResponse {

	@NotNull
	@Positive
	private final Long speakingLogId;
	@NotBlank
	private final String title;

	@NotBlank
	private final String voiceRecord;

	@NotNull
	private final Integer likeCount;

	@NotNull
	private final Integer commentCount;

	@NotNull
	private final Boolean isLiked;

	@NotNull
	private final String profileImage;

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
