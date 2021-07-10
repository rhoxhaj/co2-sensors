package com.sensor.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SensorData {
    private long sensor;
    private double co2Level;
    private String district;
    private String city;
}