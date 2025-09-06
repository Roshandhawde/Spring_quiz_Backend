package com.example.Java_Quiz_App.service.serviceImpl;

import com.example.Java_Quiz_App.dto.RequestQuestionDTO;
import com.example.Java_Quiz_App.entity.Question;
import com.example.Java_Quiz_App.repository.QuestionRepository;
import com.example.Java_Quiz_App.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    @Override
    public ResponseEntity<List<Question>> getQuestions() {
        try {
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Question> getQuestionById(Long id) {
        return questionRepository.findById(id)
                .map(question -> new ResponseEntity<>(question, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public void addQuestion(Question questionDTO) {
     questionRepository.save(questionDTO);

    }

    @Override
    public List<Question> getQuestionByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public void updateQuestion(Long id, Question question) {

        questionRepository.save(question);
    }


}
