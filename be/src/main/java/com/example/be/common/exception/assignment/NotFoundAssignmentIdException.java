package com.example.be.common.exception.assignment;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.ErrorCodeAndMessages;

public class NotFoundAssignmentIdException extends BaseException {

    public NotFoundAssignmentIdException(){
        super(ErrorCodeAndMessages.ASSIGNMENT_ID_NOT_FOUND_ERROR);
    }
}
