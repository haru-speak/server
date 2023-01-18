package com.example.be.core.application.dto.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class SpeakingLogResponse {

	@NotNull
	@Positive
	private Long speakingLogId;
	@NotBlank
	private String title;

	@NotBlank
	private String voiceRecord;

	@NotNull
	private Integer likeCount;

	@NotNull
	private Integer commentCount;

	@NotNull
	private Boolean isLiked;

	@NotNull
	private String profileImage;

	@NotNull
	@Positive
	private Long memberId;

	private SpeakingLogResponse() {}

	public SpeakingLogResponse(String title, String voiceRecord, Integer likeCount,
		Integer commentCount, Boolean isLiked, String profileImage, Long memberId) {
		this.title = title;
		this.voiceRecord = voiceRecord;
		this.likeCount = likeCount;
		this.commentCount = commentCount;
		this.isLiked = isLiked;
		this.profileImage = profileImage;
		this.memberId = memberId;
	}
}
