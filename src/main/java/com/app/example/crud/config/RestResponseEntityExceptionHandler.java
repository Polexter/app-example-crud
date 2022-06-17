package com.app.example.crud.config;

import com.app.example.crud.exception.NotFoundException;
import com.app.example.crud.utils.ApiResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponseError> handleConflict(final NotFoundException exception) {
        return new ResponseEntity<>(ApiResponseError.builder()
                .error("Cliente no encontrado.")
                .codigo("404").build(),
        HttpStatus.NOT_FOUND);
    }

}
