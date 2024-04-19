package com.geunoo.mzsangsicbackend.thirdparty.api.google;

import com.geunoo.mzsangsicbackend.thirdparty.api.google.dto.response.OauthTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "googleOauth", url = "https://oauth2.googleapis.com")
public interface GoogleOauthClient {

    @PostMapping("/token")
    OauthTokenResponse getGoogleAccessToken(
        @RequestParam("client_id") String clientId,
        @RequestParam("client_secret") String clientSecret,
        @RequestParam("redirect_uri") String redirectUri,
        @RequestParam("grant_type") String grantType,
        @RequestParam("code") String code
    );

    @PostMapping("/revoke")
    void revokeAccessToken(
        @RequestParam("token") String token
    );
}
