package com.geunoo.mzsangsicbackend.thirdparty.api.google.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoResponse {

    private final String sub;
    private final String name;
    private final String picture;
    private final String email;
}
