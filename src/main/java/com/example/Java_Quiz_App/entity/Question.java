package com.example.Java_Quiz_App.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "questions", schema = "quiz")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private String category;


    @Column(name = "difficulty_level")
    private String difficultyLevel;


    @Column(name = "question_title")
    private String questionTitle;
    
    private String option1;
    
    private String option2;
    
    private String option3;
    
    private String option4;


    @Column(name = "right_answer")
    private String rightAnswer;


}
