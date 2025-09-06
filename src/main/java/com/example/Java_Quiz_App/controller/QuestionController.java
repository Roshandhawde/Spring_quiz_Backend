package com.example.Java_Quiz_App.controller;

import com.example.Java_Quiz_App.dto.RequestQuestionDTO;
import com.example.Java_Quiz_App.entity.Question;
import com.example.Java_Quiz_App.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("/questions")
    public  ResponseEntity<List<Question>> getQuestion() {

        return  questionService.getQuestions() ;
    }

    @GetMapping("/question/{id}")
    public ResponseEntity< Question> getQuestionById(@PathVariable Long id){
        return questionService.getQuestionById(id);
    }

    @PostMapping("/question")
    public String addQuestion(@RequestBody Question question){
        System.out.println(question);
        questionService.addQuestion(question);

        return "Added Successfully";
    }
    @GetMapping("/category/{topic}")
    public List<Question> getListQuestionByCategory(@PathVariable String topic){

        return questionService.getQuestionByCategory(topic);

    }

    @DeleteMapping("/delete/{id}")
public String deleteQuestion(@PathVariable Long id){
        questionService.deleteQuestion(id);
        return "Deleted Successfully";
    }


    @PostMapping("/question/{id}")
    public String updateQuestion(@PathVariable Long id, @RequestBody Question question){
        questionService.updateQuestion(id,question );
        return "update Successfully";
    }




}
