package com.sensor.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class SensorData {
    private long sensor;
    private double co2Level;
    private LocalDateTime insertionDate;
    private String district;
    private String city;
}