package com.example.be.core.domain.member;

import com.example.be.common.exception.member.InvalidSpeakingTestType;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import java.util.Set;

public enum SpeakingTestType {

	OPIC("OPIC"),
	TOEFL("TOEFL"),
	TOEIC_SPEAKING("TOEIC_SPEAKING");

	private final String name;

	SpeakingTestType(String name) {
		this.name = name;
	}

	@JsonCreator
	public static SpeakingTestType convert(String source) {
		if (source == null) {
			return null;
		}

		return Arrays.stream(values())
			.filter(e -> e.name.equals(source.toUpperCase()))
			.findAny()
			.orElseThrow(InvalidSpeakingTestType::new);
	}
}
