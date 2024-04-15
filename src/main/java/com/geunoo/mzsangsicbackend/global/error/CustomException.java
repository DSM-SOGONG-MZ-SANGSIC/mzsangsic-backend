package com.geunoo.mzsangsicbackend.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private final String message;
    private final Integer status;
}
