package com.example.Java_Quiz_App.service.serviceImpl;

import com.example.Java_Quiz_App.dto.AnswerResponse;
import com.example.Java_Quiz_App.dto.QuestionWrapper;
import com.example.Java_Quiz_App.entity.Question;
import com.example.Java_Quiz_App.entity.Quiz;
import com.example.Java_Quiz_App.repository.QuestionRepository;
import com.example.Java_Quiz_App.repository.QuizRepository;
import com.example.Java_Quiz_App.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public ResponseEntity<String> createQuiz(String category, int qNum, String title) {
List<Question> questions= questionRepository.findRamdomQuestionByCategory(category, qNum);
        Quiz quiz= new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsList(questions);
quizRepository.save(quiz);
return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuizById(Long id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<QuestionWrapper> questionFromDB= quiz.get().getQuestionsList().stream().map(question->
                new QuestionWrapper(
                        question.getId(),
                        question.getQuestionTitle(),
                        question.getOption1(),
                        question.getOption2(),
                        question.getOption3(),
                        question.getOption4()
                )
                ).collect(Collectors.toList());
return new ResponseEntity<>(questionFromDB , HttpStatus.OK) ;
    }

    @Override
    public ResponseEntity<Integer> calculateResult(Long id, List<AnswerResponse> answerResponses) {
Quiz quiz= quizRepository.findById(id).get();
int right =0;
for(int i=0; i<answerResponses.size(); i++){
   if( answerResponses.get(i).getAnswer().equals(quiz.getQuestionsList().get(i).getRightAnswer())){
       right++;
   }
}



        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
