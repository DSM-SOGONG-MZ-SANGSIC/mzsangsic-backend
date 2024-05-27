package com.geunoo.mzsangsicbackend.domain.quiz.service;

import com.geunoo.mzsangsicbackend.domain.friend.entity.Friend;
import com.geunoo.mzsangsicbackend.domain.friend.entity.repository.FriendRepository;
import com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.response.QueryFriendAnswerResponse;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.repository.QuizQuerydslRepository;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.repository.QuizRepository;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.repository.SolvedQuizRepository;
import com.geunoo.mzsangsicbackend.domain.user.entity.User;
import com.gil.easyjwt.user.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryFriendAnswerService {

    private final FriendRepository friendRepository;
    private final QuizRepository quizRepository;
    private final SolvedQuizRepository solvedQuizRepository;
    private final QuizQuerydslRepository quizQuerydslRepository;
    private final CurrentUserService<User> currentUserService;

    @Transactional(readOnly = true)
    public QueryFriendAnswerResponse execute(Long quizId) {
        User user = currentUserService.getCurrentUser();
        List<User> friendUsers = new ArrayList<>();
        friendUsers.addAll(
            friendRepository.findAllByApplyUserIdAndIsAccept(user.getId(), true).stream()
                .map(Friend::getApplieUser)
                .toList()
        );
        friendUsers.addAll(
            friendRepository.findAllByApplieUserIdAndIsAccept(user.getId(), true).stream()
                .map(Friend::getApplyUser)
                .toList()
        );

        return new QueryFriendAnswerResponse(
            quizQuerydslRepository.queryCorrectFriendQuizCount(quizId, friendUsers.stream().map(User::getId).toList())
        );
    }
}
