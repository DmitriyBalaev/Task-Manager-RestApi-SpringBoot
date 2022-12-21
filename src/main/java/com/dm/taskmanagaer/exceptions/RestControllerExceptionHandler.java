package com.dm.taskmanagaer.exceptions;

import com.dm.taskmanagaer.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse> catchBadRequestException(BadRequestException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundResource.class)
    public ResponseEntity<ApiResponse> catchResourceNotFoundException(NotFoundResource e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiResponse> catchApplicationException(ApplicationException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
