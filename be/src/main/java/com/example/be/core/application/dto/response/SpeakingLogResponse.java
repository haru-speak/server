package com.example.be.core.application.dto.response;

import lombok.Getter;

@Getter
public class SpeakingLogResponse {

	private String title;
	private String voiceRecord;
	private Integer likeCount;
	private Integer commentCount;
	private Boolean isLiked;
	private String profileImage;
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
