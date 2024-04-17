package com.geunoo.mzsangsicbackend.domain.quiz.service;

import com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.request.QuizRequest;
import com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.response.QuizResponse;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.Quiz;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.repository.QuizRepository;
import com.geunoo.mzsangsicbackend.global.error.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuizSolvingService {

    private final QuizRepository quizRepository;

    @Transactional
    public QuizResponse execute(Long quiz_id, QuizRequest request) {

        Optional<Quiz> optionalQuiz = quizRepository.findById(quiz_id);
        Quiz quiz = optionalQuiz.orElseThrow(() -> new BadRequestException("문제를 찾지 못했습니다."));

        return QuizResponse.builder()
                .answer(quiz.getAnswer().equals(request.getPick_id()))
                .commentation(quiz.getCommentation())
                .build();
    }
}
