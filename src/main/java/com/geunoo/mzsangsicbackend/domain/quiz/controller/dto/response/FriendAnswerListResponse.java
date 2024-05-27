package com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FriendAnswerListResponse {

    private final List<FriendAnswerResponse> friends;

    @Getter
    @AllArgsConstructor
    public static class FriendAnswerResponse {
        private final Long friendId;
        private final String name;
        private final boolean answer;
    }
}
