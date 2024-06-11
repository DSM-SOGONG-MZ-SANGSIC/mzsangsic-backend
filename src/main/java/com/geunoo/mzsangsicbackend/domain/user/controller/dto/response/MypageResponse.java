package com.geunoo.mzsangsicbackend.domain.user.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MypageResponse {
    private final Long id;
    private final String name;
    private final String email;
    private final String profileImage;
}
