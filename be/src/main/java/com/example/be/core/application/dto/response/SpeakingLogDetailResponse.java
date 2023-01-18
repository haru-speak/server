package com.example.be.core.application.dto.response;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class SpeakingLogDetailResponse {

	@NotNull
	@Positive
	private final Long memberId;

	@NotBlank
	private final String title;

	@NotBlank
	private final String voiceRecord;

	@NotBlank
	private final String voiceText;

	@NotNull
	private final Integer likeCount;

	@NotNull
	private final Integer commentCount;

	@NotNull
	private final Boolean isLiked;
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
