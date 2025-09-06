package com.example.Java_Quiz_App.repository;

import com.example.Java_Quiz_App.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
List<Question> findByCategory(String category);


@Query(value = "SELECT * From quiz.questions q where q.category=:category ORDER BY RANDOM() LIMIT :qNum", nativeQuery = true)
    List<Question> findRamdomQuestionByCategory(String category, int qNum);

}
