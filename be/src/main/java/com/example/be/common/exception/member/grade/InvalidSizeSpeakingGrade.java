package com.example.be.common.exception.member.grade;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.ErrorCodeAndMessages;

public class InvalidSizeSpeakingGrade extends BaseException {

	public InvalidSizeSpeakingGrade() {
		super(ErrorCodeAndMessages.SPEAKING_GRADE_COUNT_ERROR);
	}
}
