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
	BAD_REQUEST_ERROR("E-BR000", "잘못된 요청입니다."),
	SPEAKING_LOG_TYPE_ERROR("E-BR001", "올바르지 않은 SpeakingLogType 형식입니다. ALL, MY, MATE 중 하나여야 합니다."),
  	SPEAKING_LOG_DATE_FORMAT_ERROR("E-BR002", "올바르지 않은 날짜 형식입니다. YYYYMMDD 형식이어야 합니다."),
	STUDY_TYPE_ERROR("E-BR003", "올바르지 않은 StudyType 형식입니다. ALL, MY, MATE 중 하나여야 합니다."),
	INVALID_JWT_TOKEN_ERROR("E-BR004", "유효하지 않은 JWT Token 입니다."),
	NOT_LOGGED_IN_ERROR("E-BR005", "로그인하지 않은 유저입니다."),
	MEMBER_TYPE_ERROR("E-BR006", "올바르지 않은 MemberType 형식입니다. "
			+ "elementary_school, middle_school, high_school, university, office_worker, job_seeker, free 중 하나여야 합니다."),
	SPEAKING_GRADE_LANGUAGE_ERROR("E-BR007", "올바르지 않은 SpeakingGradeLanguage 형식입니다."
		+ "eng, kor 중 하나여야 합니다."),
	SPEAKING_GRADE_LEVEL_ERROR("E-BR008", "올바르지 않은 SpeakingGradeLevel 형식입니다."
		+ "1, 2, 3, 4, 5 중 하나여야 합니다."),
	SPEAKING_TEST_TYPE_ERROR("E-BR009", "올바르지 않은 SpeakingTestType 형식입니다."
		+ "OPIC, TOEFL, TOEIC_SPEAKING 중 하나여야 합니다."),
	SPEAKING_LOG_NOT_MATCH_MEMBER_ERROR("E-BR010", "SpeakingLog의 작성자가 해당 멤버가 아닙니다."),


	/**
	 * 404 Not Found
	 */
	NOT_FOUND_ERROR("E-NF000", "존재하지 않습니다."),
	SPEAKING_LOG_ID_NOT_FOUND_ERROR("E-NF001", "스피킹 로그 아이디를 찾을 수 없습니다."),
	MEMBER_NOT_FOUND_ERROR("E-NF002", "해당 멤버를 찾을 수 없습니다."),
	STUDY_ID_NOT_FOUND_ERROR("E-NF003", "스터디 아이디를 찾을 수 없습니다."),
	ASSIGNMENT_ID_NOT_FOUND_ERROR("E-NF004", "과제 아이디를 찾을 수 없습니다."),
	GOAL_ID_NOT_FOUND_ERROR("E-NF005", "목표 아이디를 찾을 수 없습니다."),
	SUBJECT_ID_NOT_FOUND_ERROR("E-NF006", "주제 아이디를 찾을 수 없습니다."),
	FOLLOW_RELATIONSHIP_NOT_FOUND_ERROR("E-NF007", "팔로우 관계에 대해 찾을 수 없습니다."),
  
	/**
	 * 500 Server Error
	 */
	SPEAKING_GRADE_COUNT_ERROR("E-SE001", "스피킹 등급의 개수가 2개가 아닙니다."),
	;

	private final String code;
	private final String message;

	ErrorCodeAndMessages(String code, String message) {
		this.code = code;
		this.message = message;
	}

}
