package com.geunoo.mzsangsicbackend.domain.user.service;

import com.geunoo.mzsangsicbackend.domain.user.controller.dto.response.QueryUserResponse;
import com.geunoo.mzsangsicbackend.domain.user.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryUserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public QueryUserResponse execute() {
        return new QueryUserResponse(
            userRepository.findAll().stream()
                .map(user -> new QueryUserResponse.UserResponse(user.getId(), user.getName(), user.getImage()))
                .toList()
        );
    }
}
