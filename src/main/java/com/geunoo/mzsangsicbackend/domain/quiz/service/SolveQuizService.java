package com.geunoo.mzsangsicbackend.domain.quiz.service;

import com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.request.QuizRequest;
import com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.response.QuizResponse;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.Quiz;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.UserQuiz;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.repository.UserQuizRepository;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.repository.QuizRepository;
import com.geunoo.mzsangsicbackend.domain.user.entity.User;
import com.geunoo.mzsangsicbackend.global.error.exceptions.NotFoundException;
import com.gil.easyjwt.user.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SolveQuizService {

    private final QuizRepository quizRepository;
    private final UserQuizRepository userQuizRepository;
    private final CurrentUserService<User> currentUserService;

    @Transactional
    public QuizResponse execute(Long quizId, QuizRequest request) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
        Quiz quiz = optionalQuiz.orElseThrow(() -> new NotFoundException("문제를 찾지 못했습니다."));

        User user = currentUserService.getCurrentUser();

        UserQuiz userQuiz = new UserQuiz(user, quiz);
        userQuizRepository.save(userQuiz);

        return QuizResponse.builder()
                .answer(quiz.getAnswer().equals(request.getPickId()))
                .commentation(quiz.getCommentation())
                .build();
    }
}