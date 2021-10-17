package com.application.alpha_task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NO_CONTENT)
public class EqualExchangeRateException extends RuntimeException {
    public EqualExchangeRateException(String message) {
        super(message);
    }
}
