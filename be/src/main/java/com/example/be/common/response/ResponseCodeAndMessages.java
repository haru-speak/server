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
	FileUpload
	 */
	GENERATE_IMAGE_UPLOAD_RUL_SUCCESS("S-F001", "이미지 파일 업로드를 위한 URL 생성을 성공했습니다."),
	GENERATE_VOICE_UPLOAD_URL_SUCCESS("S-F002", "음성 파일 업로드를 위한 URL 생성을 성공했습니다."),
	;

	private final String code;
	private final String message;

	ResponseCodeAndMessages(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
