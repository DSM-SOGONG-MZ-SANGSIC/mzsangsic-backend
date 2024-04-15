package com.geunoo.mzsangsicbackend.global.error.exceptions;

import com.geunoo.mzsangsicbackend.global.error.CustomException;

public class UnauthorizedException extends CustomException {
    public UnauthorizedException(String message) {
        super(message, 401);
    }
}
