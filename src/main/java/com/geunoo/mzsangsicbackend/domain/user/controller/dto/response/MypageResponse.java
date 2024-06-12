package com.geunoo.mzsangsicbackend.domain.user.controller.dto.response;

import com.geunoo.mzsangsicbackend.domain.quiz.entity.Category;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MypageResponse {
    private final Long id;
    private final String name;
    private final String email;
    private final String profileImage;
    private final List<Category> categories;
}
