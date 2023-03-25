package com.docker.initial.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExamPortalController {

    @GetMapping(value = "v1/api/getMessage")
    private ResponseEntity getMessage(@RequestParam String user) {
        return new ResponseEntity(String.format("Hi User %s!", user), HttpStatus.OK);
    }
}
