package com.example.be.core.domain.member.grade;

import lombok.Getter;

@Getter
public enum SpeakingGradeType {

	LEARNER("LEARNER"), GIVER("GIVER");

	private final String type;

	SpeakingGradeType(String type) {
		this.type = type;
	}
}
