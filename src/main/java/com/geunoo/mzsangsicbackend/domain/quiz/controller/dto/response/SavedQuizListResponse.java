package com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.response;

import com.geunoo.mzsangsicbackend.domain.quiz.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SavedQuizListResponse {

    private final List<SavedQuizResponse> savedQuiz;

    @Getter
    @AllArgsConstructor
    public static class SavedQuizResponse {
        private final Long id;
        private final String content;
        private final Category category;
        private final Long answer;
    }

}
