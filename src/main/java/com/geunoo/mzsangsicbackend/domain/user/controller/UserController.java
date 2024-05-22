package com.geunoo.mzsangsicbackend.domain.user.controller;

import com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.request.QuizRequest;
import com.geunoo.mzsangsicbackend.domain.user.controller.dto.request.ProfileRequest;
import com.geunoo.mzsangsicbackend.domain.user.controller.dto.response.QueryUserResponse;
import com.geunoo.mzsangsicbackend.domain.user.controller.dto.response.TokenResponse;
import com.geunoo.mzsangsicbackend.domain.user.controller.dto.response.UrlResponse;
import com.geunoo.mzsangsicbackend.domain.user.service.GoogleLoginService;
import com.geunoo.mzsangsicbackend.domain.user.service.UserProfileService;
import jakarta.validation.Valid;
import com.geunoo.mzsangsicbackend.domain.user.service.QueryUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final GoogleLoginService googleLoginService;
    private final UserProfileService userProfileService;
    private final QueryUserService queryUserService;

    @PostMapping("/google")
    public TokenResponse googleLogin(
        @RequestParam("code") String code
    ) {
        return googleLoginService.execute(code);
    }

    @PostMapping("/google/ios")
    public TokenResponse googleLoginWithIOS(
        @RequestParam("token") String token
    ) {
        return googleLoginService.ios(token);
    }

    @GetMapping("/oauth")
    public UrlResponse getOauthUrl() {
        return googleLoginService.getOauthUrl();
    }

    @GetMapping
    public QueryUserResponse queryUsers() {
        return queryUserService.execute();
    }

    @PatchMapping("/profile")
    public void userProfile(@RequestBody @Valid ProfileRequest request) {
        userProfileService.execute(request);
    }
}
