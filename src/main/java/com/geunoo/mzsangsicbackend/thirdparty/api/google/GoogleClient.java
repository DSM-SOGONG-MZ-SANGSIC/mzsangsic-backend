package com.geunoo.mzsangsicbackend.thirdparty.api.google;

import com.geunoo.mzsangsicbackend.thirdparty.api.google.dto.response.UserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "google", url = "https://www.googleapis.com")
public interface GoogleClient {

    @GetMapping("/oauth2/v3/userinfo")
    UserInfoResponse getUserInfo(
        @RequestParam("alt") String alt,
        @RequestParam("access_token") String accessToken
    );
}
