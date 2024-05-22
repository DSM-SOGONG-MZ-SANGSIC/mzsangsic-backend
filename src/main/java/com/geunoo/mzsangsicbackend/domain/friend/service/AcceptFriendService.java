package com.geunoo.mzsangsicbackend.domain.friend.service;

import com.geunoo.mzsangsicbackend.domain.friend.entity.Friend;
import com.geunoo.mzsangsicbackend.domain.friend.entity.repository.FriendRepository;
import com.geunoo.mzsangsicbackend.domain.user.entity.User;
import com.geunoo.mzsangsicbackend.global.error.exceptions.NotFoundException;
import com.gil.easyjwt.user.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AcceptFriendService {

    private final FriendRepository friendRepository;
    private final CurrentUserService<User> currentUserService;

    @Transactional
    public void execute(Long applyUserId) {
        User appliedUser = currentUserService.getCurrentUser();
        Friend friend = friendRepository.findByApplieUserIdAndApplyUserId(appliedUser.getId(), applyUserId)
            .orElseThrow(() -> new NotFoundException("Friend Not Found"));

        friend.acceptFriend();
    }
}
