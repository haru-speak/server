package com.example.be.common.response;

import com.example.be.common.CodeAndMessages;
import lombok.Getter;

@Getter
public enum ResponseCodeAndMessages implements CodeAndMessages {

	FIND_SPEAKING_LOG_SUCCESS("S-SL001", "스피킹 로그가 성공적으로 조회되었습니다."),
	;
	private final String code;
	private final String message;

	ResponseCodeAndMessages(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
