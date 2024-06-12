package com.geunoo.mzsangsicbackend.domain.friend.service;

import com.geunoo.mzsangsicbackend.domain.friend.controller.dto.response.QueryFriendsResponse;
import com.geunoo.mzsangsicbackend.domain.friend.entity.repository.FriendRepository;
import com.geunoo.mzsangsicbackend.domain.user.controller.dto.response.QueryUserResponse;
import com.geunoo.mzsangsicbackend.domain.user.entity.User;
import com.gil.easyjwt.user.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryFriendsService {

    private final FriendRepository friendRepository;
    private final CurrentUserService<User> currentUserService;

    @Transactional(readOnly = true)
    public QueryFriendsResponse execute() {
        User user = currentUserService.getCurrentUser();
        return new QueryFriendsResponse(
            friendRepository.findAllByApplyUserIdOrApplieUserIdAndIsAccept(user.getId(), user.getId(), true).stream()
                .map(friend ->
                    new QueryUserResponse.UserResponse(
                        friend.getApplieUser().getId(),
                        friend.getApplieUser().getName(),
                        friend.getApplieUser().getImage())
                )
                .toList()
        );
    }
}
