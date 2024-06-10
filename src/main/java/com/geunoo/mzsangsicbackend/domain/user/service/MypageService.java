package com.geunoo.mzsangsicbackend.domain.user.service;

import com.geunoo.mzsangsicbackend.domain.user.controller.dto.response.MypageResponse;
import com.geunoo.mzsangsicbackend.domain.user.entity.User;
import com.geunoo.mzsangsicbackend.domain.user.entity.repository.UserRepository;
import com.gil.easyjwt.user.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MypageService {

    private final UserRepository userRepository;
    private final CurrentUserService<User> currentUserService;

    @Transactional
    public MypageResponse execute() {
        User user = currentUserService.getCurrentUser();
        return MypageResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .profileImage(user.getProfileUrl())
                .build();
    }
}
