package com.geunoo.mzsangsicbackend.domain.quiz.service;

import com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.response.QueryPickResponse;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.Quiz;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.repository.PickRepository;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.repository.QuizRepository;
import com.geunoo.mzsangsicbackend.global.error.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryPickService {

    private final PickRepository pickRepository;
    private final QuizRepository quizRepository;

    @Transactional(readOnly = true)
    public QueryPickResponse execute(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
            .orElseThrow(() -> new NotFoundException("Quiz Not Found"));

        return new QueryPickResponse(
            pickRepository.findAllByQuiz(quiz).stream()
                .map(pick -> new QueryPickResponse.PickResponse(pick.getId(), pick.getContent()))
                .toList()
        );
    }
}
