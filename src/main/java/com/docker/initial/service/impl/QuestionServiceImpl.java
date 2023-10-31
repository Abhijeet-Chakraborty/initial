package com.docker.initial.service.impl;

import com.docker.initial.modal.exam.Question;
import com.docker.initial.modal.exam.Quiz;
import com.docker.initial.repository.QuestionRepository;
import com.docker.initial.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService {

    double marksGot = 0;
    int attempted = 0;
    int rightAnswer = 0;
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Set<Question> getQuestions() {
        return new LinkedHashSet<>(questionRepository.findAll());
    }

    @Override
    public Question getQuestion(Long questionId) {
        return questionRepository.findById(questionId).get();
    }

    @Override
    public Set<Question> getQuestionByQuiz(Quiz quiz) {
        return questionRepository.findByQuiz(quiz);
    }

    @Override
    public void deleteQuestion(Long questionId) {
        var question = Question.builder().quesId(questionId).build();
        questionRepository.delete(question);
    }

    @Override
    public Map<String, String> evalQuiz(List<Question> questions) {
        attempted = 0;
        rightAnswer = 0;
        marksGot = 0;
        questions.forEach(ques->{
            var question = getQuestion(ques.getQuesId());
            if(StringUtils.isNotBlank(ques.getGivenAnswer()) && ques.getGivenAnswer().trim().equals(question.getAnswer())) {
                rightAnswer++;
                var singleMarks = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks()) / questions.size();
                marksGot += singleMarks;
            }
            if(StringUtils.isNotBlank(ques.getGivenAnswer())) {
                attempted++;
            }
        });
        return Map.of(
                "rightAnswer", String.valueOf(rightAnswer),
                "marksGot", String.valueOf(marksGot),
                "attempted", String.valueOf(attempted)
        );
    }
}
