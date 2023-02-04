package com.example.be.common.exception.member.subject;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.ErrorCodeAndMessages;

public class NotFoundSubjectIdException extends BaseException {

	public NotFoundSubjectIdException() {
		super(ErrorCodeAndMessages.SUBJECT_ID_NOT_FOUND_ERROR);
	}
}
