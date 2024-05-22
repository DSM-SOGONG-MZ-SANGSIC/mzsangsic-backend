package com.geunoo.mzsangsicbackend.global.security;

import com.geunoo.mzsangsicbackend.domain.user.entity.repository.UserRepository;
import com.gil.easyjwt.user.JwtUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class QueryJwtUserService implements com.gil.easyjwt.user.QueryJwtUserService {

    private final UserRepository userRepository;

    @Override
    public Optional<JwtUser> execute(String email) {
        return userRepository.findByEmail(email)
            .map(JwtUser.class::cast);
    }
}
