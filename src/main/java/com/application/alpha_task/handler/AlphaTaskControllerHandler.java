package com.application.alpha_task.handler;

import feign.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AlphaTaskControllerHandler {
    @SneakyThrows
    @ExceptionHandler(FeignException.Forbidden.class)
    ResponseEntity<String> handleFeignForbidden(FeignException.Forbidden forbidden) {
        log.error(forbidden.getMessage());
        return new ResponseEntity<>(forbidden.getMessage(), HttpStatus.FORBIDDEN);
    }

    @SneakyThrows
    @ExceptionHandler(FeignException.Unauthorized.class)
    ResponseEntity<String> handleFeignUnauthorized(FeignException.Unauthorized unauthorized) {
        log.error(unauthorized.getMessage());
        return new ResponseEntity<>(unauthorized.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @SneakyThrows
    @ExceptionHandler(FeignException.MethodNotAllowed.class)
    ResponseEntity<String> handleFeignMethodNotAllowed(FeignException.MethodNotAllowed methodNotAllowed) {
        log.error(methodNotAllowed.getMessage());
        return new ResponseEntity<>(methodNotAllowed.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @SneakyThrows
    @ExceptionHandler(FeignException.BadRequest.class)
    ResponseEntity<String> handleFeignBadRequest(FeignException.BadRequest badRequest) {
        log.error(badRequest.getMessage());
        return new ResponseEntity<>(badRequest.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @SneakyThrows
    @ExceptionHandler(FeignException.BadGateway.class)
    ResponseEntity<String> handleFeignBadRequest(FeignException.BadGateway badGateway) {
        log.error(badGateway.getMessage());
        return new ResponseEntity<>(badGateway.getMessage(), HttpStatus.BAD_GATEWAY);
    }

    @SneakyThrows
    @ExceptionHandler(BindException.class)
    ResponseEntity<String> handleBindException(BindException bindException) {
        log.error(bindException.getMessage());
        return new ResponseEntity<>(bindException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @SneakyThrows
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    ResponseEntity<String> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException supportedException) {
        log.error(supportedException.getMessage());
        return new ResponseEntity<>(supportedException.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @SneakyThrows
    @ExceptionHandler(Throwable.class)
    ResponseEntity<String> handleThrowableException(Throwable throwable) {
        log.error(throwable.getMessage());
        return new ResponseEntity<>(throwable.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
