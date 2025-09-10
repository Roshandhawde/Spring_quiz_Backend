package com.example.Java_Quiz_App.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class QuizDto {
    private String categoryName;
    private Long numQuestion;
    private String title;


}
