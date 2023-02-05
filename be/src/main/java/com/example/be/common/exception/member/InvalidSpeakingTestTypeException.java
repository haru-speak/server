package com.example.be.common.exception.member;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.ErrorCodeAndMessages;

public class InvalidSpeakingTestTypeException extends BaseException {

	public InvalidSpeakingTestTypeException() {
		super(ErrorCodeAndMessages.SPEAKING_TEST_TYPE_ERROR);
	}
}
