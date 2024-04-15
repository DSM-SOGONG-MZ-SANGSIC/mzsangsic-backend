package com.geunoo.mzsangsicbackend.global.error.exceptions;

import com.geunoo.mzsangsicbackend.global.error.CustomException;

public class ForbiddenException extends CustomException {
    public ForbiddenException(String message) {
        super(message, 403);
    }
}
