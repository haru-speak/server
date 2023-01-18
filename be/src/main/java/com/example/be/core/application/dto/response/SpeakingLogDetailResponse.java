package com.example.be.core.application.dto.response;

import java.util.List;
import lombok.Getter;

@Getter
public class SpeakingLogDetailResponse {

	private Long memberId;
	private String title;
	private String voiceRecord;
	private String voiceText;
	private Integer likeCount;
	private Integer commentCount;
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
