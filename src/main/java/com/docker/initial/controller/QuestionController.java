package com.docker.initial.controller;

import com.docker.initial.modal.ResponseObject;
import com.docker.initial.modal.exam.Question;
import com.docker.initial.service.QuestionService;
import com.docker.initial.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }

    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("qid") Long qid) {
        var quiz = this.quizService.getQuiz(qid);
        var questions = quiz.getQuestions();
        List<Question> list = new ArrayList<>(questions);
        if(list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
            list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));
        }
        list.forEach(que -> {
                que.setAnswer("");
        });

        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/quiz/all/{qid}")
    public ResponseEntity<?> getAllQuestionOfQuiz(@PathVariable("qid") Long qid) {
        var quiz = this.quizService.getQuiz(qid);
        var questions = quiz.getQuestions();
        var list = new ArrayList<>(questions);
        return ResponseEntity.ok(list);
    }


    @GetMapping("/{quesId}")
    public ResponseEntity<Question> getQuestion(@PathVariable("quesId") Long quesId){
        return ResponseEntity.ok(this.questionService.getQuestion(quesId));
    }

    @DeleteMapping("/{quesId}")
    public ResponseEntity<ResponseObject> deleteQuestion(@PathVariable("quesId") Long quesId) {

        this.questionService.deleteQuestion(quesId);

        var response = ResponseObject.builder().body(null)
                .message("Quiz question is deleted")
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/eval-quiz")
    public ResponseEntity<ResponseObject>  evalQuiz(@RequestBody List<Question> questions) {
        var result = questionService.evalQuiz(questions);
        var response = ResponseObject.builder().body(result)
                .message("Quiz evaluation is completed!!")
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }
}
