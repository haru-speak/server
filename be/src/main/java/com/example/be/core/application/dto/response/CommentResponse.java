package com.example.be.core.application.dto.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class CommentResponse {

	@NotNull
	@Positive
	private final Long commentId;

	@NotNull
	@Positive
	private final Long memberId;

	@NotBlank
	private final String nickname;

	@NotBlank
	private final String content;

	public CommentResponse(Long commentId, Long memberId, String nickname, String content) {
		this.commentId = commentId;
		this.memberId = memberId;
		this.nickname = nickname;
		this.content = content;
	}
}
