package com.example.be.core.domain.speakinglog;

import com.example.be.common.exception.speakinglog.InvalidSpeakingLogTypeException;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum SpeakingLogType {

	ALL("ALL"),
	MY("MY"),
	MATE("MATE"),
	;

	private final String type;

	SpeakingLogType(String type) {
		this.type = type;
	}

	public static SpeakingLogType convert(String source) {

		return Arrays.stream(SpeakingLogType.values())
			.filter(e -> e.type.equals(source))
			.findAny()
			.orElseThrow(InvalidSpeakingLogTypeException::new);
	}

}
