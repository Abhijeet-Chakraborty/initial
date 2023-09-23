package com.docker.initial.modal;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ResponseObject {
    private Object body;
    private String message;
    private HttpStatus status;
}
