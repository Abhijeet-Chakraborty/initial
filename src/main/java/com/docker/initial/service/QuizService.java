package com.docker.initial.service;

import com.docker.initial.modal.exam.Category;
import com.docker.initial.modal.exam.Quiz;

import java.util.List;
import java.util.Set;

public interface QuizService {

    public Quiz addQuiz(Quiz quiz);

    public Quiz updateQuiz(Quiz quiz);

    public Set<Quiz> getQuizzes();

    public Quiz getQuiz(Long quizId);

    public void deleteQuiz(Long quizId);

    List<Quiz> getQuizzesOfCategory(Category category);

    List<Quiz> getActiveQuizzes();

    List<Quiz> getActiveQuizzesOfCategory(Category cat);
}
