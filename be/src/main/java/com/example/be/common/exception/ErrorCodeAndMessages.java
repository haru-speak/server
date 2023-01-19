package com.example.be.common.exception;

import com.example.be.common.CodeAndMessages;
import java.lang.annotation.Annotation;
import lombok.Getter;

@Getter
public enum ErrorCodeAndMessages implements CodeAndMessages {

	/*
	400 Bad Request
	 */
	SPEAKING_LOG_TYPE_ERROR("E-BR001", "올바르지 않은 SpeakingLogType입니다. ALL, MY, MATE 중 하나여야 합니다."),
	;

	private final String code;
	private final String message;

	ErrorCodeAndMessages(String code, String message) {
		this.code = code;
		this.message = message;
	}

}
