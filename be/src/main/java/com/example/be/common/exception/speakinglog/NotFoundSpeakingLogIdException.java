package com.example.be.common.exception.speakinglog;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.ErrorCodeAndMessages;

public class NotFoundSpeakingLogIdException extends BaseException {

	public NotFoundSpeakingLogIdException() {
		super(ErrorCodeAndMessages.SPEAKING_LOG_ID_NOT_FOUND_ERROR);
	}
}
