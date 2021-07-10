package com.sensor.model;

import lombok.Data;

@Data
public class SensorRequestBody {
    private int sensorId;
    private double co2Level;
}
