package com.onlineshop.app.exceptionUtils.exceptions.order;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderBadRequest extends RuntimeException {
    public OrderBadRequest(String message) {
        super(message);
    }
}
