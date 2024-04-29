package com.geunoo.mzsangsicbackend.domain.quiz.entity.repository;

import com.geunoo.mzsangsicbackend.domain.quiz.entity.SolvedQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolvedQuizRepository extends JpaRepository<SolvedQuiz, Long> {
}