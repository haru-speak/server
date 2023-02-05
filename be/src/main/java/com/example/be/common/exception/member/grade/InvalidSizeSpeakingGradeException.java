package com.example.be.common.exception.member.grade;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.ErrorCodeAndMessages;

public class InvalidSizeSpeakingGradeException extends BaseException {

	public InvalidSizeSpeakingGradeException() {
		super(ErrorCodeAndMessages.SPEAKING_GRADE_COUNT_ERROR);
	}
}
