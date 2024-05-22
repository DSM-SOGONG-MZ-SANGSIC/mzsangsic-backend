package com.geunoo.mzsangsicbackend.domain.friend.service;

import com.geunoo.mzsangsicbackend.domain.friend.entity.Friend;
import com.geunoo.mzsangsicbackend.domain.friend.entity.repository.FriendRepository;
import com.geunoo.mzsangsicbackend.domain.user.entity.User;
import com.geunoo.mzsangsicbackend.domain.user.entity.repository.UserRepository;
import com.geunoo.mzsangsicbackend.global.error.exceptions.NotFoundException;
import com.gil.easyjwt.user.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateFriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;
    private final CurrentUserService<User> currentUserService;

    @Transactional
    public void execute(Long userId) {
        User applyUser = currentUserService.getCurrentUser();
        User applieUser = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException("User Not Found"));

        friendRepository.save(new Friend(applyUser, applieUser, false));
    }
}
