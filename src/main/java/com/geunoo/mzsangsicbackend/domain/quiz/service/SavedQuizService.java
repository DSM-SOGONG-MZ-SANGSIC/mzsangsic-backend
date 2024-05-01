package com.geunoo.mzsangsicbackend.domain.quiz.service;

import com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.response.SavedQuizListResponse;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.repository.UserQuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SavedQuizService {

    private final UserQuizRepository userQuizRepository;

    @Transactional(readOnly = true)
    public SavedQuizListResponse execute() {
        return new SavedQuizListResponse(
                userQuizRepository.findAll().stream()
                        .map(saved -> new SavedQuizListResponse.SavedQuizResponse(saved.getId(), saved.getQuiz().getContent(), saved.getQuiz().getCategory(), saved.getQuiz().getAnswer()))
                        .toList()
        );
    }
}
