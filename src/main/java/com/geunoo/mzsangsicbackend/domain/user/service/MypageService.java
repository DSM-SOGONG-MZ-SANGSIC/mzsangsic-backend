package com.geunoo.mzsangsicbackend.domain.user.service;

import com.geunoo.mzsangsicbackend.domain.user.controller.dto.response.MypageResponse;
import com.geunoo.mzsangsicbackend.domain.user.entity.User;
import com.geunoo.mzsangsicbackend.domain.user.entity.UserCategory;
import com.geunoo.mzsangsicbackend.domain.user.entity.repository.UserCategoryRepository;
import com.gil.easyjwt.user.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MypageService {

    private final CurrentUserService<User> currentUserService;
    private final UserCategoryRepository userCategoryRepository;

    @Transactional
    public MypageResponse execute() {
        User user = currentUserService.getCurrentUser();
        return MypageResponse.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .profileImage(user.getProfileUrl())
            .categories(userCategoryRepository.findAllByUserId(user.getId()).stream().map(UserCategory::getCategory).toList())
            .build();
    }
}
