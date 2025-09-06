package com.example.Java_Quiz_App.service;

import com.example.Java_Quiz_App.dto.RequestQuestionDTO;
import com.example.Java_Quiz_App.entity.Question;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {

     ResponseEntity<List<Question>> getQuestions();

    ResponseEntity< Question> getQuestionById(Long id);

     void addQuestion(Question questionDTO);

     List<Question> getQuestionByCategory(String category);

     void deleteQuestion(Long id);

     void updateQuestion(Long id, Question question);

}
