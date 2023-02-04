package com.example.be.common.exception.member.grade;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.ErrorCodeAndMessages;

public class InvalidSpeakingGradeLevelException extends BaseException {

	public InvalidSpeakingGradeLevelException() {
		super(ErrorCodeAndMessages.SPEAKING_GRADE_LEVEL_ERROR);
	}
}
