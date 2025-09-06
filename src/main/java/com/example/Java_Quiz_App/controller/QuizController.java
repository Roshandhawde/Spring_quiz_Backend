package com.example.Java_Quiz_App.controller;

import com.example.Java_Quiz_App.dto.AnswerResponse;
import com.example.Java_Quiz_App.dto.QuestionWrapper;
import com.example.Java_Quiz_App.entity.Question;
import com.example.Java_Quiz_App.entity.Quiz;
import com.example.Java_Quiz_App.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
@Autowired
    QuizService quizService;


    @PostMapping("create")
    public ResponseEntity<String> createQuiz (@RequestParam String category, @RequestParam int qNum, @RequestParam String title ){
        quizService.createQuiz(category, qNum, title);
        return new ResponseEntity<>("Quiz Created Successfully", HttpStatus.OK);
    }
    @GetMapping("Qset/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizById(@PathVariable Long id){
        return quizService.getQuizById(id);
    }
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Long id, @RequestBody List<AnswerResponse> answerResponses){
return  quizService.calculateResult(id, answerResponses);
    }
}
