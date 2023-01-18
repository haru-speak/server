package com.example.be.core.application.dto.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class CommentResponse {

	@NotNull
	@Positive
	private Long commentId;

	@NotNull
	@Positive
	private Long memberId;

	@NotBlank
	private String nickname;

	@NotBlank
	private String content;

	private CommentResponse() {}

	public CommentResponse(Long commentId, Long memberId, String nickname, String content) {
		this.commentId = commentId;
		this.memberId = memberId;
		this.nickname = nickname;
		this.content = content;
	}
}
