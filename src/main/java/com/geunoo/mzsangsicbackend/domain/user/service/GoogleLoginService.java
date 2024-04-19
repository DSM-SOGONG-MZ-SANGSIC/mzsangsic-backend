package com.geunoo.mzsangsicbackend.domain.user.service;

import com.geunoo.mzsangsicbackend.domain.user.controller.dto.response.TokenResponse;
import com.geunoo.mzsangsicbackend.domain.user.entity.User;
import com.geunoo.mzsangsicbackend.domain.user.entity.repository.UserRepository;
import com.geunoo.mzsangsicbackend.thirdparty.api.google.GoogleClient;
import com.geunoo.mzsangsicbackend.thirdparty.api.google.GoogleOauthClient;
import com.geunoo.mzsangsicbackend.thirdparty.api.google.GoogleProperties;
import com.geunoo.mzsangsicbackend.thirdparty.api.google.dto.response.OauthTokenResponse;
import com.geunoo.mzsangsicbackend.thirdparty.api.google.dto.response.UserInfoResponse;
import com.gil.easyjwt.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GoogleLoginService {

    private final GoogleClient googleClient;
    private final GoogleOauthClient googleOauthClient;
    private final GoogleProperties googleProperties;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponse execute(String code) {
        OauthTokenResponse tokens = googleOauthClient.getGoogleAccessToken(
            googleProperties.getClientId(),
            googleProperties.getClientSecret(),
            googleProperties.getRedirectUri(),
            "authorization_code",
            code
        );
        UserInfoResponse userInfo = googleClient.getUserInfo("json", tokens.getAccessToken());

        User user = userRepository.findBySub(userInfo.getSub())
            .orElseGet(() -> userRepository.save(
                User.builder()
                    .email(userInfo.getEmail())
                    .name(userInfo.getName())
                    .profileUrl(userInfo.getPicture())
                    .sub(userInfo.getSub())
                    .build()
            ));

        googleOauthClient.revokeAccessToken(tokens.getAccessToken());
        return new TokenResponse(jwtTokenProvider.generateAccessToken(user.getEmail()));
    }
}
