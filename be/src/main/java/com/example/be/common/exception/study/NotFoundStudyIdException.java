package com.example.be.common.exception.study;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.ErrorCodeAndMessages;

public class NotFoundStudyIdException extends BaseException {

    public NotFoundStudyIdException() {
        super(ErrorCodeAndMessages.STUDY_ID_NOT_FOUND_ERROR);
    }
}
