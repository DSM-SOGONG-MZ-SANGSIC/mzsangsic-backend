package com.geunoo.mzsangsicbackend.global.error.exceptions;

import com.geunoo.mzsangsicbackend.global.error.CustomException;

public class InternalServerErrorException extends CustomException {
    public InternalServerErrorException() {
        super("Internal Server Error", 500);
    }
}
