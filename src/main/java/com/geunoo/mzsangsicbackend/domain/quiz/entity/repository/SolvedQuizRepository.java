package com.geunoo.mzsangsicbackend.domain.quiz.entity.repository;

import com.geunoo.mzsangsicbackend.domain.quiz.entity.SolvedQuiz;
import com.geunoo.mzsangsicbackend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolvedQuizRepository extends JpaRepository<SolvedQuiz, Long> {

    List<SolvedQuiz> findByUser(User user);
}