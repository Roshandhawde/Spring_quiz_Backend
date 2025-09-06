package com.example.Java_Quiz_App.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor

public class AnswerResponse {
    private Long id;
    private String answer;


}
