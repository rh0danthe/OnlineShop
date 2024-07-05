package com.onlineshop.app.middleware;

import com.onlineshop.app.models.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();

        ErrorMessage errorMessage = new ErrorMessage("Ошибка при валидации: ", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Object> handleIOException(IOException ex) {
        List<ObjectError> errors = new ArrayList<>();
        errors.add(new ObjectError("IOException", ex.getMessage()));

        ErrorMessage errorMessage = new ErrorMessage("Ошибка ввода-вывода", errors);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleExceptions(Exception ex) {
        List<ObjectError> errors = new ArrayList<>();
        errors.add(new ObjectError("Exception", ex.getMessage()));

        ErrorMessage errorMessage = new ErrorMessage("Произошла ошибка: ", errors);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}
