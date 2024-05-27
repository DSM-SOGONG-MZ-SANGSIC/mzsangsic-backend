package com.geunoo.mzsangsicbackend.domain.quiz.entity.repository.vo;

import com.geunoo.mzsangsicbackend.domain.quiz.entity.Category;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class QuerySolvedQuizVO {

    private final Long quizId;
    private final Category category;
    private final boolean isCorrect;

    @QueryProjection
    public QuerySolvedQuizVO(Long quizId, Category category, boolean isCorrect) {
        this.quizId = quizId;
        this.category = category;
        this.isCorrect = isCorrect;
    }
}
