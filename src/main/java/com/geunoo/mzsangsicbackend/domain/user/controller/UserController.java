package com.geunoo.mzsangsicbackend.domain.user.controller;

import com.geunoo.mzsangsicbackend.domain.user.controller.dto.request.CreateUserCategoryRequest;
import com.geunoo.mzsangsicbackend.domain.user.controller.dto.request.ProfileRequest;
import com.geunoo.mzsangsicbackend.domain.user.controller.dto.response.MypageResponse;
import com.geunoo.mzsangsicbackend.domain.user.controller.dto.response.QueryUserResponse;
import com.geunoo.mzsangsicbackend.domain.user.controller.dto.response.TokenResponse;
import com.geunoo.mzsangsicbackend.domain.user.controller.dto.response.UrlResponse;
import com.geunoo.mzsangsicbackend.domain.user.service.CreateUserCategoryService;
import com.geunoo.mzsangsicbackend.domain.user.service.GoogleLoginService;
import com.geunoo.mzsangsicbackend.domain.user.service.MypageService;
import com.geunoo.mzsangsicbackend.domain.user.service.QueryUserService;
import com.geunoo.mzsangsicbackend.domain.user.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final GoogleLoginService googleLoginService;
    private final UserProfileService userProfileService;
    private final QueryUserService queryUserService;
    private final MypageService mypageService;
    private final CreateUserCategoryService createUserCategoryService;

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

    @GetMapping("/mypage")
    public MypageResponse getMypage() {
        return mypageService.execute();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/category")
    public void createUserCategory(@RequestBody @Valid CreateUserCategoryRequest request) {
        createUserCategoryService.execute(request);
    }
}
