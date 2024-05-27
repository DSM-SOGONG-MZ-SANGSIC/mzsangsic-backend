package com.geunoo.mzsangsicbackend.domain.quiz.service;

import com.geunoo.mzsangsicbackend.domain.friend.entity.Friend;
import com.geunoo.mzsangsicbackend.domain.friend.entity.repository.FriendRepository;
import com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.response.FriendAnswerListResponse;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.Quiz;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.repository.QuizRepository;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.repository.SolvedQuizRepository;
import com.geunoo.mzsangsicbackend.global.error.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FriendAnswerService {

    private final FriendRepository friendRepository;
    private final QuizRepository quizRepository;
    private final SolvedQuizRepository solvedQuizRepository;

    @Transactional(readOnly = true)
    public FriendAnswerListResponse execute(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new NotFoundException("Quiz Not Found"));

        return new FriendAnswerListResponse(
                friendRepository.findAllByQuiz(quiz).stream()
                        .map(friend -> new FriendAnswerListResponse.FriendAnswerResponse(friend.getId(), friend.getApplyUser().getName(), friend))
        );
    }
}
