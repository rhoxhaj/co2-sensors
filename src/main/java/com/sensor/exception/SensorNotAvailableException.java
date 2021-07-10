package com.sensor.exception;

public class SensorNotAvailableException extends GeneralNotFoundException {

    public SensorNotAvailableException(int sensor) {
        super("Sensor cannot be found: " + sensor);
    }
}