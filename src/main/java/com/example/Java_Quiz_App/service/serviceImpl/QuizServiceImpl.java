package com.example.Java_Quiz_App.service.serviceImpl;

import com.example.Java_Quiz_App.dto.AnswerResponse;
import com.example.Java_Quiz_App.dto.QuestionWrapper;
import com.example.Java_Quiz_App.dto.QuizDto;
import com.example.Java_Quiz_App.entity.Quiz;
import com.example.Java_Quiz_App.feingConfig.QuizInterface;
import com.example.Java_Quiz_App.repository.QuizRepository;
import com.example.Java_Quiz_App.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    QuizRepository quizRepository;

    @Autowired
    private QuizInterface quizInterface;


    @Override
    public ResponseEntity<String> createQuiz(String category, Long qNum, String title) {

        // Call Another Microservices of Create Questions | generate Api USing RestTemplate
        // RestTemplate :- http://localhost:8181/api/generate
        List<Long> questionIds = quizInterface.getQuestionFromQuiz(category, qNum).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsIds(questionIds);

        quizRepository.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuizById(Long id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Long> questionIds = quiz.get().getQuestionsIds();

      List<QuestionWrapper> questions= quizInterface.getQuestionsFromId(questionIds).getBody();


        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> calculateResult(Long id, List<AnswerResponse> answerResponses) {
        Quiz quiz = quizRepository.findById(id).get();

    Integer result=  quizInterface.GetScore(answerResponses).getBody();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
