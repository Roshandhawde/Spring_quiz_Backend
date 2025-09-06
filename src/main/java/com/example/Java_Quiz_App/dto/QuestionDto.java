package com.example.Java_Quiz_App.dto;

import jakarta.persistence.Column;

public class QuestionDto {
    private Long id;

    private String category;
    private String difficulty_level;
    private String question_title;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String right_answer;
}
