package com.geunoo.mzsangsicbackend.domain.user.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryUserResponse {
    private final List<UserResponse> users;

    @Getter
    @AllArgsConstructor
    public static class UserResponse {
        private final Long id;
        private final String name;
        private final byte[] profileImage;
    }
}
