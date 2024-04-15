package com.geunoo.mzsangsicbackend.global.error.exceptions;

import com.geunoo.mzsangsicbackend.global.error.CustomException;

public class BadRequestException extends CustomException {
    public BadRequestException(String message) {
        super(message, 400);
    }
}
