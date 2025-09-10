package com.example.Java_Quiz_App.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionWrapper {
    private Long id;
    private String question_title;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

}
