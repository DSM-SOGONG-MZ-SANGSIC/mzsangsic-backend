package com.geunoo.mzsangsicbackend.domain.user.entity.repository;

import com.geunoo.mzsangsicbackend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findBySub(String sub);
}
