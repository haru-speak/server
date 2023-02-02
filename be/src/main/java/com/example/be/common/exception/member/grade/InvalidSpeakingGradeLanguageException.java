package com.example.be.common.exception.member.grade;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.ErrorCodeAndMessages;

public class InvalidSpeakingGradeLanguageException extends BaseException {

	public InvalidSpeakingGradeLanguageException() {
		super(ErrorCodeAndMessages.SPEAKING_GRADE_LANGUAGE_ERROR);
	}
}
