package com.geunoo.mzsangsicbackend.domain.quiz.service;

import com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.request.QuizRequest;
import com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.response.AnswerResponse;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.*;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.repository.PickRepository;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.repository.SolvedQuizRepository;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.repository.QuizRepository;
import com.geunoo.mzsangsicbackend.domain.user.entity.User;
import com.geunoo.mzsangsicbackend.global.error.exceptions.NotFoundException;
import com.gil.easyjwt.user.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class SolveQuizService {

    private final QuizRepository quizRepository;
    private final CurrentUserService<User> currentUserService;
    private final SolvedQuizRepository solvedQuizRepository;
    private final PickRepository pickRepository;

    @Transactional
    public AnswerResponse execute(Long quizId, QuizRequest request) {
        User user = currentUserService.getCurrentUser();
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new NotFoundException("문제를 찾지 못했습니다."));
        Pick pick = pickRepository.findById(request.getPickId())
                .orElseThrow(() -> new NotFoundException("선택한 답을 찾지 못했습니다."));

        solvedQuizRepository.save(new SolvedQuiz(quiz, pick, user));

        return AnswerResponse.builder()
                .answer(quiz.getAnswer().equals(request.getPickId()))
                .commentation(quiz.getCommentation())
                .build();
    }
}