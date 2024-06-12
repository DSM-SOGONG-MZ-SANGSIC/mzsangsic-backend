package com.geunoo.mzsangsicbackend.domain.user.controller.dto.request;

import com.geunoo.mzsangsicbackend.domain.quiz.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreateUserCategoryRequest {

    private List<Category> category;
}
