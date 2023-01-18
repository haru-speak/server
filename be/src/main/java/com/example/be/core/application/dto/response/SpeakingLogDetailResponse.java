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
	private Long memberId;

	@NotBlank
	private String title;

	@NotBlank
	private String voiceRecord;

	@NotBlank
	private String voiceText;

	@NotNull
	private Integer likeCount;

	@NotNull
	private Integer commentCount;

	@NotNull
	private Boolean isLiked;
	private List<CommentResponse> comments;

	private SpeakingLogDetailResponse() {}

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
