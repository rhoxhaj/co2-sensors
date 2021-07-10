package com.sensor.api;

import com.sensor.exception.SensorNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SensorControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SensorNotAvailableException.class)
    public ResponseEntity<String> customSensorNotFound(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

    }


}