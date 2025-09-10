package com.example.Java_Quiz_App.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Setter
@Getter
@Data
@Entity
@Table(name = "quiz", schema = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
//    @ManyToMany
//    @JoinTable(
//            name = "question_tags",
//            schema = "quiz"
////            joinColumns = @JoinColumn(name = "question_id"),
////            inverseJoinColumns = @JoinColumn(name = "tag_id")
//    )
@ElementCollection
@CollectionTable(
        name = "quiz_questions_ids",
        schema = "quiz"
//        joinColumns = @JoinColumn(name = "quiz_id")
)
@Column(name = "question_id")
    private List<Long> questionsIds;

}
