package com.sensor.exception;

public class GeneralNotFoundException extends RuntimeException {

    GeneralNotFoundException(String message) {
        super(message);
    }
}