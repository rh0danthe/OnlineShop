package com.onlineshop.app.exceptionUtils.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ErrorMessage {
    private String message;
    private List<String> details;

    public ErrorMessage(String message, List<ObjectError> errors) {
        this.message = message;
        this.details = new ArrayList<>();
        for (ObjectError error : errors) {
            this.details.add(error.getDefaultMessage());
        }
    }
}
