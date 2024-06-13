package com.geunoo.mzsangsicbackend.domain.user.service;

import com.geunoo.mzsangsicbackend.domain.user.controller.dto.request.ProfileRequest;
import com.geunoo.mzsangsicbackend.domain.user.entity.User;
import com.gil.easyjwt.user.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class UserProfileService {

    private final CurrentUserService<User> currentUserService;

    @Transactional
    public void execute(ProfileRequest request) {
        User user = currentUserService.getCurrentUser();
        MultipartFile image = request.getImage();

        if (image != null && !image.isEmpty()) {
            try {
                byte[] imageBytes = image.getBytes();
                user.setProfileImage(imageBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
