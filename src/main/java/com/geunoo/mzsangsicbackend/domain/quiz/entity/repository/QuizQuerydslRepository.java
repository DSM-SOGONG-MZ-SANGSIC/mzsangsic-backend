package com.geunoo.mzsangsicbackend.domain.quiz.entity.repository;

import com.geunoo.mzsangsicbackend.domain.quiz.entity.repository.vo.QQuerySolvedQuizVO;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.repository.vo.QuerySolvedQuizVO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.geunoo.mzsangsicbackend.domain.friend.entity.QFriend.friend;
import static com.geunoo.mzsangsicbackend.domain.quiz.entity.QQuiz.quiz;
import static com.geunoo.mzsangsicbackend.domain.quiz.entity.QSolvedQuiz.solvedQuiz;
import static com.geunoo.mzsangsicbackend.domain.user.entity.QUser.user;

@RequiredArgsConstructor
@Repository
public class QuizQuerydslRepository {

    private final JPAQueryFactory queryFactory;

    public List<QuerySolvedQuizVO> querySolvedQuiz(Long userId) {
        return queryFactory
            .select(
                new QQuerySolvedQuizVO(
                    quiz.id,
                    quiz.category,
                    solvedQuiz.pick.id.eq(quiz.answer)
                )
            )
            .from(solvedQuiz)
            .join(solvedQuiz.user, user)
            .join(solvedQuiz.quiz, quiz)
            .where(user.id.eq(userId))
            .fetch();
    }

    public Long queryCorrectFriendQuizCount(Long quizId, List<Long> friendUserId) {
        return queryFactory
            .select(solvedQuiz.count())
            .from(solvedQuiz)
            .join(solvedQuiz.user, user)
            .join(solvedQuiz.quiz, quiz)
            .where(
                quiz.id.eq(quizId),
                quiz.answer.eq(solvedQuiz.pick.id),
                user.id.in(friendUserId)
            )
            .fetchOne();
    }
}
