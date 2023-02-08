package com.example.be.core.application.dto.response;

import com.example.be.core.domain.member.subject.Subject;
import lombok.Getter;

@Getter
public class SubjectResponse {

	private final Long subjectId;
	private final String content;
	private final String image;

	private SubjectResponse(Long subjectId, String content, String image) {
		this.subjectId = subjectId;
		this.content = content;
		this.image = image;
	}

	public static SubjectResponse of(Subject subject) {
		return new SubjectResponse(
			subject.getId(),
			subject.getContent(),
			subject.getImage()
		);
	}
}
