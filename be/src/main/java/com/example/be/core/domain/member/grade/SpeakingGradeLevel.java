package com.example.be.core.domain.member.grade;

import com.example.be.common.exception.member.grade.InvalidSpeakingGradeLevelException;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
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
		Set<String> stringValues = Arrays.stream(SpeakingGradeLevel.values())
			.map(Enum::toString)
			.collect(Collectors.toSet());

		if (stringValues.contains(source)) {
			return SpeakingGradeLevel.valueOf(source);
		}

		return Arrays.stream(values())
			.filter(e -> e.level.equals(source))
			.findAny()
			.orElseThrow(InvalidSpeakingGradeLevelException::new);
	}
}
