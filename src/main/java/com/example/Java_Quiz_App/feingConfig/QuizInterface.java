package com.example.Java_Quiz_App.feingConfig;

import com.example.Java_Quiz_App.dto.AnswerResponse;
import com.example.Java_Quiz_App.dto.QuestionWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("api/generate")
    public ResponseEntity<List<Long>> getQuestionFromQuiz
            (@RequestParam String categoryName, @RequestParam Long numQuestion);

    @PostMapping("api/getQuestions")
     ResponseEntity<List<QuestionWrapper>> getQuestionsFromId
            (@RequestBody List<Long> questionIds);

    @PostMapping("api/getScore")
     ResponseEntity<Integer> GetScore(@RequestBody List<AnswerResponse> answerResponse);
}
