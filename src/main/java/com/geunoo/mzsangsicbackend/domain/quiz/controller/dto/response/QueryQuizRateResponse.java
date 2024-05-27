package com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.response;

import com.geunoo.mzsangsicbackend.domain.quiz.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryQuizRateResponse {

    private final String name;
    private final List<CategoryQuizRateResponse> percentage;

    @Getter
    @AllArgsConstructor
    public static class CategoryQuizRateResponse {
        private final Category category;
        private final Integer solvedQuiz;
        private final Integer correctQuiz;
    }
}
