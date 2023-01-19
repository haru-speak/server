package com.example.be.core.domain;

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
