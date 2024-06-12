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
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping(value = "/profile", consumes = "/multipart/form-data")
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
