package com.geunoo.mzsangsicbackend.thirdparty.api.google;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "google")
public class GoogleProperties {

    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
}
