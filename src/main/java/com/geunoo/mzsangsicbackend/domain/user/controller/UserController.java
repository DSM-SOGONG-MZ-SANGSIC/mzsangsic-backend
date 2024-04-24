package com.geunoo.mzsangsicbackend.domain.user.controller;

import com.geunoo.mzsangsicbackend.domain.user.controller.dto.response.TokenResponse;
import com.geunoo.mzsangsicbackend.domain.user.service.GoogleLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final GoogleLoginService googleLoginService;

    @PostMapping("google")
    public TokenResponse googleLogin(
        @RequestParam("code") String code
    ) {
        return googleLoginService.execute(code);
    }
}