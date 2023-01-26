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
	MODIFY_SPEAKING_LOG_SUCCESS("S-SL004", "스피킹 로그 수정을 성공했습니다"),
	CREATE_SPEAKING_LOG_SUCCESS("S-SL005", "스피킹 로그 생성을 성공했습니다."),

	/**
	Question
	 */
	FIND_QUESTION_SUCCESS("S-Q001", "오늘의 문장 조회를 성공했습니다."),

	/**
	 Study
	 */
	CREATE_STUDY_SUCCESS("S-S001", "스터디 생성을 성공했습니다."),
	FIND_STUDY_SUCCESS("S-S002", "스터디 타입 전체 조회를 성공했습니다."),
	FIND_DETAIL_STUDY_SUCCESS("S-S003", "스터디 상세 조회를 성공했습니다."),
	MODIFY_STUDY_SUCCESS("S-S004", "스터디 수정을 성공했습니다."),
	DELETE_STUDY_SUCCESS("S-S005", "스터디 삭제를 성공했습니다."),
	;

	private final String code;
	private final String message;

	ResponseCodeAndMessages(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
