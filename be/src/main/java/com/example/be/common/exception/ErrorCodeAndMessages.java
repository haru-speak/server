package com.example.be.common.exception;

import com.example.be.common.CodeAndMessages;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public enum ErrorCodeAndMessages implements CodeAndMessages {

	/**
	 * 400 Bad Request
	 */
	SPEAKING_LOG_TYPE_ERROR("E-BR001", "올바르지 않은 타입 형식입니다. ALL, MY, MATE 중 하나여야 합니다."),
	SPEAKING_LOG_DATE_FORMAT_ERROR("E-BR002", "올바르지 않은 날짜 형식입니다. yyyyMMdd 형식이어야 합니다."),

	MEMBERFORM_INVALID("E-MF001", "유효하지 않은 입력값입니다. 각 입력 조건을 확인해주세요."),

	PASSWORD_MISMATCH("E-PM001", "저장된 회원의 비밀번호와 일치하지 않습니다."),

	/**
	 * 404 Not Found
	 */
	SPEAKING_LOG_ID_NOT_FOUND_ERROR("E-NF001", "스피킹 로그 아이디를 찾을 수 없습니다."),
	LOGIN_MEMBER_NOT_FOUND_ERROR("E-NF002", "로그인 된 멤버를 찾을 수 없습니다."),
	;

	private final String code;
	private final String message;

	ErrorCodeAndMessages(String code, String message) {
		this.code = code;
		this.message = message;
	}

}
