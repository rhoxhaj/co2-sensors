package com.sensor.repository.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "sensor_records")
@Data
@Builder
@AllArgsConstructor
public class SensorRecordDAO {

    public SensorRecordDAO() {
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name = "co2_level")
    private double co2Level;

    @Column(name = "insertion_time")
    private LocalDateTime insertionTime;

    @ManyToOne()
    @JoinColumn(name="sensor_id", updatable = false)
    private SensorDAO sensorDAO;
}

