package com.example.be.common.exception.speakinglog;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.ErrorCodeAndMessages;

public class InvalidSpeakingLogTypeException extends BaseException {

	public InvalidSpeakingLogTypeException() {
		super(ErrorCodeAndMessages.SPEAKING_LOG_TYPE_ERROR);
	}
}
