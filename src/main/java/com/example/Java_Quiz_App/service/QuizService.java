package com.example.Java_Quiz_App.service;

import com.example.Java_Quiz_App.dto.AnswerResponse;
import com.example.Java_Quiz_App.dto.QuestionWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizService {

    ResponseEntity<String> createQuiz(String category, int qNum, String title);

    ResponseEntity<List<QuestionWrapper>> getQuizById(Long id);

    ResponseEntity<Integer> calculateResult(Long id, List<AnswerResponse> answerResponses);
}
