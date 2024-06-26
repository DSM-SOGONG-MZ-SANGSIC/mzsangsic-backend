package com.geunoo.mzsangsicbackend.domain.quiz.controller;

import com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.request.QuizRequest;
import com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.response.AnswerResponse;
import com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.response.QueryFriendAnswerResponse;
import com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.response.QueryPickResponse;
import com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.response.QueryQuizListResponse;
import com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.response.QueryQuizRateResponse;
import com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.response.SavedQuizListResponse;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.Category;
import com.geunoo.mzsangsicbackend.domain.quiz.service.QueryFriendAnswerService;
import com.geunoo.mzsangsicbackend.domain.quiz.service.QueryPickService;
import com.geunoo.mzsangsicbackend.domain.quiz.service.QueryQuizRateService;
import com.geunoo.mzsangsicbackend.domain.quiz.service.QueryQuizService;
import com.geunoo.mzsangsicbackend.domain.quiz.service.SavedQuizService;
import com.geunoo.mzsangsicbackend.domain.quiz.service.SolveQuizService;
import com.geunoo.mzsangsicbackend.domain.quiz.service.UserQuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final SolveQuizService solveQuizService;
    private final UserQuizService userQuizService;
    private final QueryQuizService queryQuizService;
    private final QueryPickService queryPickService;
    private final SavedQuizService savedQuizService;
    private final QueryQuizRateService queryQuizRateService;
    private final QueryFriendAnswerService queryFriendAnswerService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{quiz-id}")
    public AnswerResponse solvingQuiz(@PathVariable("quiz-id") Long quizId, @RequestBody @Valid QuizRequest request) {
        return solveQuizService.execute(quizId, request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user/{quiz-id}")
    public void saveQuiz(@PathVariable("quiz-id") Long quizId) {
        userQuizService.execute(quizId);
    }

    @GetMapping
    public QueryQuizListResponse queryQuiz(@RequestParam("category") Category category) {
        return queryQuizService.execute(category);
    }

    @GetMapping("/pick/{quiz-id}")
    public QueryPickResponse queryPicks(@PathVariable("quiz-id") Long quizId) {
        return queryPickService.execute(quizId);
    }

    @GetMapping("/saved")
    public SavedQuizListResponse savedQuiz() {
        return savedQuizService.execute();
    }

    @GetMapping("/percentage/{user-id}")
    public QueryQuizRateResponse queryQuizRate(@PathVariable("user-id") Long userId) {
        return queryQuizRateService.execute(userId);
    }

    @GetMapping("/friend/{quiz-id}")
    public QueryFriendAnswerResponse FriendAnswer(@PathVariable("quiz-id") Long quizId) {
        return queryFriendAnswerService.execute(quizId);
    }
}
