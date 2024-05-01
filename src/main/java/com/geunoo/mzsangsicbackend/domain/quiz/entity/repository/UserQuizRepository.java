package com.geunoo.mzsangsicbackend.domain.quiz.entity.repository;

import com.geunoo.mzsangsicbackend.domain.quiz.entity.UserQuiz;
import com.geunoo.mzsangsicbackend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserQuizRepository extends JpaRepository<UserQuiz, Long> {

    List<UserQuiz> findByUser(User user);
}