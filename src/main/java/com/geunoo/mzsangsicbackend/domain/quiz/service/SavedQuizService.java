package com.geunoo.mzsangsicbackend.domain.quiz.service;

import com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.response.SavedQuizListResponse;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.repository.UserQuizRepository;
import com.geunoo.mzsangsicbackend.domain.user.entity.User;
import com.gil.easyjwt.user.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SavedQuizService {

    private final UserQuizRepository userQuizRepository;
    private final CurrentUserService<User> currentUserService;

    @Transactional(readOnly = true)
    public SavedQuizListResponse execute() {
        User user = currentUserService.getCurrentUser();

        return new SavedQuizListResponse(
                userQuizRepository.findByUser(user).stream()
                        .map(saved -> new SavedQuizListResponse.SavedQuizResponse(saved.getQuiz().getId(), saved.getQuiz().getContent(), saved.getQuiz().getCategory(), saved.getQuiz().getAnswer()))
                        .toList()
        );
    }
}
