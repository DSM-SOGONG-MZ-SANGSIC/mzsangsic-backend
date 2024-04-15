package com.geunoo.mzsangsicbackend.global.error.exceptions;

import com.geunoo.mzsangsicbackend.global.error.CustomException;

public class ConflictException extends CustomException {
    public ConflictException(String message) {
        super(message, 409);
    }
}
