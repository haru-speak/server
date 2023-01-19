package com.example.be.common.exception.speakinglog;

import static com.example.be.common.exception.ErrorCodeAndMessages.SPEAKING_LOG_DATE_FORMAT_ERROR;

import com.example.be.common.exception.BaseException;

public class InvalidSpeakingLogDateException extends BaseException {

	public InvalidSpeakingLogDateException() {
		super(SPEAKING_LOG_DATE_FORMAT_ERROR);
	}
}
