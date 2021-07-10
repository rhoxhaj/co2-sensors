package com.sensor.repository;

import com.sensor.repository.dao.SensorDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<SensorDAO, Long> {

    SensorDAO findById(long id);
}