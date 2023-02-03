package com.example.be.common.exception.member;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.ErrorCodeAndMessages;

public class InvalidSpeakingTestType extends BaseException {

	public InvalidSpeakingTestType() {
		super(ErrorCodeAndMessages.SPEAKING_TEST_TYPE_ERROR);
	}
}
