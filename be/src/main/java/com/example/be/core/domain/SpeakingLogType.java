package com.example.be.core.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum SpeakingLogType {

	ALL("all"),
	MY("my"),
	MATE("mate"),
	;

	private final String type;

	SpeakingLogType(String type) {
		this.type = type;
	}

	@JsonCreator
	public static SpeakingLogType fromString(String type) {
		return Arrays.stream(SpeakingLogType.values())
			.filter(t -> t.name().equalsIgnoreCase(type))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("해당 SPEAKING LOG TYPE을 찾을 수 없습니다."));
	}
}
