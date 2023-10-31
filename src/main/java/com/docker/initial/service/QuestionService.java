package com.docker.initial.service;

import com.docker.initial.modal.exam.Question;
import com.docker.initial.modal.exam.Quiz;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface QuestionService {

    public Question addQuestion(Question question);
    public Question updateQuestion(Question question);
    public Set<Question> getQuestions();
    public Question getQuestion(Long questionId);
    public Set<Question> getQuestionByQuiz(Quiz quiz);
    public void deleteQuestion(Long questionId);
    public Map<String, String> evalQuiz(List<Question> questions);
}
