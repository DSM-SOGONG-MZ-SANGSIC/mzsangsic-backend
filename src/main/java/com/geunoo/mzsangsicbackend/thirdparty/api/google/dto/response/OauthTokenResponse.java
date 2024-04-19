package com.geunoo.mzsangsicbackend.thirdparty.api.google.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OauthTokenResponse {
    private final String accessToken;
    private final String idToken;
}
