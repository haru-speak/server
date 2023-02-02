package com.example.be.core.domain.member.grade;

import com.example.be.common.exception.member.grade.InvalidSpeakingGradeLanguageException;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum SpeakingGradeLanguage {

	ENG("eng"),
	KOR("kor");

	private final String language;

	SpeakingGradeLanguage(String language) {
		this.language = language;
	}

	@JsonCreator
	public static SpeakingGradeLanguage convert(String source) {
		return Arrays.stream(SpeakingGradeLanguage.values())
			.filter(e -> e.language.equals(source.toLowerCase()))
			.findAny()
			.orElseThrow(InvalidSpeakingGradeLanguageException::new);
	}
}
