package com.example.Java_Quiz_App.repository;

import com.example.Java_Quiz_App.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;



public interface QuizRepository extends JpaRepository<Quiz, Long> {


}
