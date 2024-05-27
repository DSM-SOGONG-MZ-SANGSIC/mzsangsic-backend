package com.geunoo.mzsangsicbackend.domain.friend.service;

import com.geunoo.mzsangsicbackend.domain.friend.controller.dto.response.QueryApplyUsersResponse;
import com.geunoo.mzsangsicbackend.domain.friend.entity.repository.FriendRepository;
import com.geunoo.mzsangsicbackend.domain.user.controller.dto.response.QueryUserResponse;
import com.geunoo.mzsangsicbackend.domain.user.entity.User;
import com.gil.easyjwt.user.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryApplyUsersService {

    private final FriendRepository friendRepository;
    private final CurrentUserService<User> currentUserService;

    @Transactional(readOnly = true)
    public QueryApplyUsersResponse execute() {
        User appliedUser = currentUserService.getCurrentUser();
        return new QueryApplyUsersResponse(
            friendRepository.findAllByApplieUserIdAndIsAccept(appliedUser.getId(), false).stream()
                .map(friend ->
                    new QueryUserResponse.UserResponse(
                        friend.getApplyUser().getId(),
                        friend.getApplyUser().getName(),
                        friend.getApplyUser().getProfileUrl()
                    )
                ).toList()
        );
    }
}
