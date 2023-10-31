package com.docker.initial.modal.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "question")

//@JsonIgnoreProperties(value = "answer", allowSetters = true)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long quesId;
    @Column(length = 5000)
    private String content;
    private String image;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    //@JsonIgnore
    private String answer;
    @Transient
    private String givenAnswer;
    @ManyToOne(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private Quiz quiz;
}
