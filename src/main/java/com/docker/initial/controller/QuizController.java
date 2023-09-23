package com.docker.initial.controller;

import com.docker.initial.modal.ResponseObject;
import com.docker.initial.modal.exam.Category;
import com.docker.initial.modal.exam.Quiz;
import com.docker.initial.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(this.quizService.addQuiz(quiz));
    }

    @PutMapping("/")
    public ResponseEntity<ResponseObject> updateQuiz(@RequestBody Quiz quiz) {
        this.quizService.updateQuiz(quiz);
        var response = ResponseObject.builder().body(null)
                .message("Quiz is updated successfully")
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("{qid}")
    public Quiz getQuiz(@PathVariable("qid") Long qid) {
        return this.quizService.getQuiz(qid);
    }

    @GetMapping("/")
    public ResponseEntity<Set<Quiz>> getQuizzes() {
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }

    @DeleteMapping("/{qid}")
    public ResponseEntity<ResponseObject> deleteQuiz(@PathVariable("qid") Long qid) {
        this.quizService.deleteQuiz(qid);
        var response = ResponseObject.builder().body(null)
                .message("Quiz is deleted")
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{cid}")
    public ResponseEntity<ResponseObject> getQuizzesOfCategory(@PathVariable("cid") Long cid) {
        var category = Category.builder().cid(cid).build();
        var response = ResponseObject.builder().body(this.quizService.getQuizzesOfCategory(category))
                .message("Quizzes are fetched")
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/active")
    public ResponseEntity<ResponseObject> getActiveQuizzes() {

        var response = ResponseObject.builder().body(this.quizService.getActiveQuizzes())
                .message("Active Quizzes are fetched")
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/active/{cid}")
    public ResponseEntity<ResponseObject> getActiveQuizzesOfCategory(@PathVariable("cid") Long cid) {
        var category = Category.builder().cid(cid).build();
        var response = ResponseObject.builder().body(this.quizService.getActiveQuizzesOfCategory(category))
                .message("Active Quizzes are fetched for category")
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }
}
