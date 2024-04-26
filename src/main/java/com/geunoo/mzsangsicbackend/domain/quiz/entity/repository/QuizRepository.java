package com.geunoo.mzsangsicbackend.domain.quiz.entity.repository;

import com.geunoo.mzsangsicbackend.domain.quiz.entity.Category;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.Quiz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizRepository extends CrudRepository<Quiz, Long> {

    @Query("select quiz from Quiz quiz where quiz.category = :category order by RAND() limit 10")
    List<Quiz> findAllByCategoryAndRandom(@Param("category") Category category);
}
