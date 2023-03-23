package com.meawallet.weatherapp.in.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> responseBody = new LinkedHashMap<>();

        responseBody.put("timestamp", LocalDateTime.now(ZoneOffset.UTC));
        responseBody.put("status", status);

        var errors = ex.getFieldErrors()
                .stream()
                .map(this::errorDescription)
                .collect(Collectors.toList());

        responseBody.put("errors", errors);
        responseBody.put("path:", ((ServletWebRequest) request).getRequest().getRequestURI());

        log.debug("Request failed because method argument not valid: {}, {}", request, errors);

        return new ResponseEntity<>(responseBody, headers, status);
    }



    private String errorDescription(FieldError fieldError) {
        return "Field: " + fieldError.getField() + ", description: " + fieldError.getDefaultMessage();
    }
}
