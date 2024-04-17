package com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuizResponse {

    private boolean answer;
    private String commentation;
}
