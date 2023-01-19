package com.example.be.common.exception;

import com.example.be.common.CodeAndMessages;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public enum ErrorCodeAndMessages implements CodeAndMessages {

	/*
	400 Bad Request
	 */
	SPEAKING_LOG_TYPE_ERROR("E-BR001", "올바르지 않은 타입 형식입니다. ALL, MY, MATE 중 하나여야 합니다."),
	SPEAKING_LOG_DATE_FORMAT_ERROR("E-BR002", "올바르지 않은 날짜 형식입니다. yyyyMMdd 형식이어야 합니다.")
	;

	private final String code;
	private final String message;

	ErrorCodeAndMessages(String code, String message) {
		this.code = code;
		this.message = message;
	}

}
