package com.geunoo.mzsangsicbackend.domain.quiz.entity.repository;

import com.geunoo.mzsangsicbackend.domain.quiz.entity.Quiz;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface QuizRepository extends CrudRepository<Quiz, Long> {

    Optional<Quiz> findById(Long id);

}
