package com.mangement.controller;

import com.mangement.exception.EmployeeNotFoundException;
import com.mangement.exception.ProjectNotFoundException;
import com.mangement.domain.EmployeeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = EmployeeController.class)
public class EmployeeControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeControllerAdvice.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<EmployeeEntity> badRequest(MethodArgumentNotValidException e){
        logger.info("exception "+ e.getClass().getSimpleName() +" occurred; message: " + e.getMessage());
        return ResponseEntity.badRequest().build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<EmployeeEntity> notFound(EmployeeNotFoundException e){
        logger.info("exception "+ e.getClass().getSimpleName() +" occurred; message: " + e.getMessage());
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<EmployeeEntity> notFound(ProjectNotFoundException e){
        logger.info("exception "+ e.getClass().getSimpleName() +" occurred; message: " + e.getMessage());
        return ResponseEntity.notFound().build();
    }
}