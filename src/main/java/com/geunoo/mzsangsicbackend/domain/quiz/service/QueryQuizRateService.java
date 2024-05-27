package com.geunoo.mzsangsicbackend.domain.quiz.service;

import com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.response.QueryQuizRateResponse;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.Category;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.repository.QuizQuerydslRepository;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.repository.vo.QuerySolvedQuizVO;
import com.geunoo.mzsangsicbackend.domain.user.entity.User;
import com.geunoo.mzsangsicbackend.domain.user.entity.repository.UserRepository;
import com.geunoo.mzsangsicbackend.global.error.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryQuizRateService {

    private final QuizQuerydslRepository quizQuerydslRepository;
    private final UserRepository userRepository;

    public QueryQuizRateResponse execute(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException("User Not Found"));

        List<QueryQuizRateResponse.CategoryQuizRateResponse> categoryQuizRates = new ArrayList<>();
        List<QuerySolvedQuizVO> solvedQuiz = quizQuerydslRepository.querySolvedQuiz(user.getId());
        List<Category> categories = solvedQuiz.stream().map(QuerySolvedQuizVO::getCategory).distinct().toList();
        for (Category category : categories) {
            List<QuerySolvedQuizVO> categorySolvedQuiz = solvedQuiz.stream()
                .filter(quiz -> quiz.getCategory().equals(category))
                .toList();
            categoryQuizRates.add(
                new QueryQuizRateResponse.CategoryQuizRateResponse(
                    category,
                    categorySolvedQuiz.size(),
                    categorySolvedQuiz.stream().filter(QuerySolvedQuizVO::isCorrect).toList().size()
                )
            );
        }

        return new QueryQuizRateResponse(user.getName(), categoryQuizRates);
    }
}
