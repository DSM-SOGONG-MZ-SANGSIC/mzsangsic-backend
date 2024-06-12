package com.geunoo.mzsangsicbackend.domain.user.service;

import com.geunoo.mzsangsicbackend.domain.user.controller.dto.request.CreateUserCategoryRequest;
import com.geunoo.mzsangsicbackend.domain.user.entity.User;
import com.geunoo.mzsangsicbackend.domain.user.entity.UserCategory;
import com.geunoo.mzsangsicbackend.domain.user.entity.repository.UserCategoryRepository;
import com.gil.easyjwt.user.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateUserCategoryService {

    private final UserCategoryRepository userCategoryRepository;
    private final CurrentUserService<User> currentUserService;

    public void execute(CreateUserCategoryRequest request) {
        User user = currentUserService.getCurrentUser();
        userCategoryRepository.deleteAll(userCategoryRepository.findAllByUserId(user.getId()));
        userCategoryRepository.saveAll(
            request.getCategory().stream()
                .map(category -> new UserCategory(category, user))
                .toList()
        );
    }
}
