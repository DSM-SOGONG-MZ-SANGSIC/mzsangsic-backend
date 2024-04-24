package com.geunoo.mzsangsicbackend.domain.quiz.controller;

import com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.request.QuizRequest;
import com.geunoo.mzsangsicbackend.domain.quiz.controller.dto.response.QuizResponse;
import com.geunoo.mzsangsicbackend.domain.quiz.service.SolveQuizService;
import com.geunoo.mzsangsicbackend.domain.quiz.service.UserQuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final SolveQuizService solveQuizService;
    private final UserQuizService userQuizService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{quiz-id}")
    public QuizResponse solvingQuiz(@PathVariable("quiz-id") Long quizId, @RequestBody @Valid QuizRequest request) {
        return solveQuizService.execute(quizId, request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user/{quiz-id}")
    public void saveQuiz(@PathVariable("quiz-id") Long quizId) {
        userQuizService.execute(quizId);
    }
}
