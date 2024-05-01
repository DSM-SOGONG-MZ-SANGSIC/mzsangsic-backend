package com.geunoo.mzsangsicbackend.domain.quiz.service;

import com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.response.QueryQuizListResponse;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.Category;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryQuizService {

    private final QuizRepository quizRepository;

    @Transactional(readOnly = true)
    public QueryQuizListResponse execute(Category category) {
        return new QueryQuizListResponse(
            quizRepository.findAllByCategoryAndRandom(category).stream()
                .map(quiz -> new QueryQuizListResponse.QuizResponse(quiz.getId(), quiz.getContent()))
                .toList()
        );
    }
}
