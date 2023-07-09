package com.docker.initial.exception;

public class UserFoundException extends RuntimeException{

    public UserFoundException() {
        super("User already present in the database !!");
    }

    public UserFoundException(String message) {
        super(message);
    }
}
