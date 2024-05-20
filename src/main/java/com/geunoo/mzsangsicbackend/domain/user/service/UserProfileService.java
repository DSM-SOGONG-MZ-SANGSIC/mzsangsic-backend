package com.geunoo.mzsangsicbackend.domain.user.service;

import com.geunoo.mzsangsicbackend.domain.user.controller.dto.request.ProfileRequest;
import com.geunoo.mzsangsicbackend.domain.user.entity.User;
import com.gil.easyjwt.user.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserProfileService {

    private final CurrentUserService<User> currentUserService;

    @Transactional
    public void execute(ProfileRequest request) {
        User user = currentUserService.getCurrentUser();
        user.setProfileImageUrl(request.getImageUrl());
    }
}
