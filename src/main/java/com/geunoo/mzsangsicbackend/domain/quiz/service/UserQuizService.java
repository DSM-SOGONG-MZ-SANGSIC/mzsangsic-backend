package com.geunoo.mzsangsicbackend.domain.quiz.service;

import com.geunoo.mzsangsicbackend.domain.quiz.entity.Quiz;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.UserQuiz;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.repository.QuizRepository;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.repository.UserQuizRepository;
import com.geunoo.mzsangsicbackend.domain.user.entity.User;
import com.geunoo.mzsangsicbackend.global.error.exceptions.NotFoundException;
import com.gil.easyjwt.user.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class UserQuizService {

    private final QuizRepository quizRepository;
    private final UserQuizRepository userQuizRepository;
    private final CurrentUserService<User> currentUserService;

    @Transactional
    public void execute(Long quizId) {
        User user = currentUserService.getCurrentUser();
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new NotFoundException("문제를 찾지 못했습니다."));

        userQuizRepository.save(new UserQuiz(user, quiz));

    }
}
