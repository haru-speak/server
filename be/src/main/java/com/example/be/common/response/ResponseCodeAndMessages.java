package com.example.be.common.response;

import com.example.be.common.CodeAndMessages;
import lombok.Getter;

@Getter
public enum ResponseCodeAndMessages implements CodeAndMessages {

	/**
	SpeakingLog
	 */
	FIND_SPEAKING_LOG_SUCCESS("S-SL001", "스피킹 로그 타입 전체 조회를 성공했습니다."),
	FIND_DETAIL_SPEAKING_LOG_SUCCESS("S-SL002", "스피킹 로그 개별 조회를 성공했습니다."),
	DELETE_SPEAKING_LOG_SUCCESS("S-SL003", "스피킹 로그 삭제를 성공했습니다"),

	/**
	Question
	 */
	FIND_QUESTION_SUCCESS("S-Q001", "오늘의 문장 조회를 성공했습니다."),
	;

	private final String code;
	private final String message;

	ResponseCodeAndMessages(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
