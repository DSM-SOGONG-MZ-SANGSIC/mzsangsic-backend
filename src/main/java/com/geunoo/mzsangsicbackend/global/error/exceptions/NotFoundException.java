package com.geunoo.mzsangsicbackend.global.error.exceptions;

import com.geunoo.mzsangsicbackend.global.error.CustomException;

public class NotFoundException extends CustomException {
    public NotFoundException(String message) {
        super(message, 404);
    }
}
