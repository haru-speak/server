package com.example.be.common.exception.study;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.ErrorCodeAndMessages;

public class NotMatchStudyAndMemberException extends BaseException {

	public NotMatchStudyAndMemberException() {
		super(ErrorCodeAndMessages.STUDY_NOT_MATCH_LEADER_ERROR);
	}
}
