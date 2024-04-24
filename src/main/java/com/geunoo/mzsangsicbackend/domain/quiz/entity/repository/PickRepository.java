package com.geunoo.mzsangsicbackend.domain.quiz.entity.repository;

import com.geunoo.mzsangsicbackend.domain.quiz.entity.Pick;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PickRepository extends JpaRepository<Pick, Long> {

    List<Pick> findAllByQuiz(Quiz quiz);
}
