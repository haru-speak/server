package com.example.be.core.domain.member.grade;

import com.example.be.common.exception.member.grade.InvalidSpeakingGradeLevelException;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum SpeakingGradeLevel {

	LEVEL_ONE("1"),
	LEVEL_TWO("2"),
	LEVEL_THREE("3"),
	LEVEL_FOUR("4"),
	LEVEL_FIVE("5");

	private final String level;

	SpeakingGradeLevel(String level) {
		this.level = level;
	}

	@JsonCreator
	public static SpeakingGradeLevel convert(String source) {
		return Arrays.stream(SpeakingGradeLevel.values())
			.filter(e -> e.level.equals(source))
			.findAny()
			.orElseThrow(InvalidSpeakingGradeLevelException::new);
	}
}
