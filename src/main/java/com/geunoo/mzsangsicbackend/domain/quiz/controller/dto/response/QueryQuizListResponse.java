package com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryQuizListResponse {

    private final List<QuizResponse> quiz;

    @Getter
    @AllArgsConstructor
    public static class QuizResponse {
        private final Long id;
        private final String content;
    }
}
