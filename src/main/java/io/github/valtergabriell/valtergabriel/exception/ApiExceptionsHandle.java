package io.github.valtergabriell.valtergabriel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionsHandle {
    @ExceptionHandler(value = {RequestExceptions.class})
    public ResponseEntity<Object> handleApiRequestException(RequestExceptions e) {
        APIException exception = new APIException(
                e.getMessage()
        );
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
}