package com.dm.taskmanagaer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundResource extends RuntimeException{

    public NotFoundResource(String message) {
        super(message);
    }
}
