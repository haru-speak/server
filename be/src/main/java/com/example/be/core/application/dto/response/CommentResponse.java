package com.example.be.core.application.dto.response;

import com.example.be.core.domain.speakinglog.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public class CommentResponse {

	@Schema(type = "Long", description = "댓글 ID, NOT NULL")
	@NotNull
	@Positive
	private final Long commentId;

	@Schema(type = "Long", description = "멤버 ID, NOT NULL")
	@NotNull
	@Positive
	private final Long memberId;

	@Schema(type = "String", description = "멤버 닉네임, NOT NULL")
	@NotBlank
	private final String nickname;

	@Schema(type = "String", description = "댓글 내용, NOT NULL")
	@NotBlank
	private final String content;

	private CommentResponse(Long commentId, Long memberId, String nickname, String content) {
		this.commentId = commentId;
		this.memberId = memberId;
		this.nickname = nickname;
		this.content = content;
	}

	public static CommentResponse from(Comment comment) {
		return new CommentResponse(
			comment.getId(),
			comment.getMember().getId(),
			comment.getMember().getNickname(),
			comment.getContent()
		);
	}
}
