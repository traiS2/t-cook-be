package com.trainh.tcookbe.config;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders httpHeaders, HttpStatusCode
                    httpStatus,
            WebRequest
                    webRequest
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("current timestamp", new Date());
        body.put("status", httpStatus.value());

        List<String> exceptions = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(
                        DefaultMessageSourceResolvable::getDefaultMessage
                ).toList();
        body.put("errors", exceptions);
        return new ResponseEntity<>(body, httpStatus);
    }
}
