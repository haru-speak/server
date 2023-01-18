package com.example.be.core.domain;

import com.example.be.common.exception.speakinglog.InvalidSpeakingLogTypeException;
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

}
