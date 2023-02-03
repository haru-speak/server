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
	GENERATE_IMAGE_UPLOAD_URL_SUCCESS("S-F001", "이미지 파일 업로드를 위한 URL 생성을 성공했습니다."),
	GENERATE_VOICE_UPLOAD_URL_SUCCESS("S-F002", "음성 파일 업로드를 위한 URL 생성을 성공했습니다."),

	/**
	 OAuth
	 */
	OAUTH_LOGIN_SUCCESS("S-OA001", "로그인(혹은 회원가입)에 성공했습니다."),
	REISSUE_ACCESS_TOKEN_SUCCESS("S-OA002", "ACCESS TOKEN 재발급에 성공했습니다."),

	/**
	 * Member
	 */
	SIGN_UP_MEMBER_SUCCESS("S-M001", "멤버의 회원 등록에 성공했습니다."),
	MODIFY_MEMBER_INFO_SUCCESS("S-M002", "멤버의 회원 정보 변경에 성공했습니다."),

	/**
	 Study
	 */
	CREATE_STUDY_SUCCESS("S-S001", "스터디 생성을 성공했습니다."),
	FIND_ALL_STUDY_SUCCESS("S-S002", "스터디 로그 타입 전체 조회를 성공했습니다."),
	FIND_DETAIL_STUDY_SUCCESS("S-S003", "스터디 상세 조회를 성공했습니다."),
	MODIFY_STUDY_SUCCESS("S-S004", "스터디 수정을 성공했습니다."),
	DELETE_STUDY_SUCCESS("S-S005", "스터디 삭제를 성공했습니다."),
	
	/**
	 Assignment
	 */
	CREATE_ASSIGNMENT_SUCCESS("S-A001", "과제 생성을 성공했습니다."),
	FIND_ASSIGNMENT_SUCCESS("S-A002", "과제 전체 조회를 성공했습니다."),
	FIND_DETAIL_ASSIGNMENT_SUCCESS("S-A003", "과제 개별 조회를 성공했습니다."),
	MODIFY_ASSIGNMENT_SUCCESS("S-A004", "과제 수정을 성공했습니다"),
	DELETE_ASSIGNMENT_SUCCESS("S-A005", "과제 삭제를 성공했습니다"),
  ;

	private final String code;
	private final String message;

	ResponseCodeAndMessages(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
