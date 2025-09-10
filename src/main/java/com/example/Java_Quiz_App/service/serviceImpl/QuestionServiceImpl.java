package com.example.Java_Quiz_App.service.serviceImpl;

import com.example.Java_Quiz_App.dto.AnswerResponse;
import com.example.Java_Quiz_App.dto.QuestionWrapper;
import com.example.Java_Quiz_App.dto.RequestQuestionDTO;
import com.example.Java_Quiz_App.entity.Question;
import com.example.Java_Quiz_App.repository.QuestionRepository;
import com.example.Java_Quiz_App.service.QuestionService;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    Environment environment;

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
        System.out.println(environment.getProperty("local.server.port"));

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

    @Override
    public ResponseEntity<List<Long>> gerQuestionFromQuiz(String categoryName, Long numQuestion) {
        List<Long> questions= questionRepository.findRamdomQuestionByCategory(categoryName, numQuestion);
//
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Long> questionIds) {
        List<QuestionWrapper> wrapper = new ArrayList<>();
        List<Question> questions = new ArrayList<>();
        System.out.println(environment.getProperty("local.server.port"));

        for(Long id: questionIds){
            questions.add(questionRepository.findById(id).get());
        }
        for(Question question : questions){
            QuestionWrapper wrapper1 = new QuestionWrapper(
                    question.getId(),
                    question.getQuestionTitle(),
                    question.getOption1(),
                    question.getOption2(),
                    question.getOption3(),
                    question.getOption4()

            );
            wrapper.add(wrapper1);
        }
    return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> getScore(List<AnswerResponse> answerResponse) {
        int right =0;
        int i=0;
        for(AnswerResponse answerResponse1: answerResponse){
            Question question= questionRepository.findById(answerResponse1.getId()).get();

            if( answerResponse.get(i).getAnswer().equals(question.getRightAnswer())){
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }


}
