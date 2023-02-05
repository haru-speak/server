package com.example.be.common.exception.speakinglog;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.ErrorCodeAndMessages;

public class NotMatchSpeakingLogAndMemberException extends BaseException {

	public NotMatchSpeakingLogAndMemberException() {
		super(ErrorCodeAndMessages.SPEAKING_LOG_NOT_MATCH_MEMBER_ERROR);
	}
}
