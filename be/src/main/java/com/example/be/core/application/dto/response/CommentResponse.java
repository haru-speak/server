package com.example.be.core.application.dto.response;

import lombok.Getter;

@Getter
public class CommentResponse {

	private Long commentId;
	private Long memberId;
	private String nickname;
	private String content;

	private CommentResponse() {}

	public CommentResponse(Long commentId, Long memberId, String nickname, String content) {
		this.commentId = commentId;
		this.memberId = memberId;
		this.nickname = nickname;
		this.content = content;
	}
}
