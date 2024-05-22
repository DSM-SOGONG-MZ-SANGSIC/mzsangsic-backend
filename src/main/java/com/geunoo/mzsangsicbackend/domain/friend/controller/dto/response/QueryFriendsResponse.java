package com.geunoo.mzsangsicbackend.domain.friend.controller.dto.response;

import com.geunoo.mzsangsicbackend.domain.user.controller.dto.response.QueryUserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryFriendsResponse {
    private final List<QueryUserResponse.UserResponse> friends;
}
