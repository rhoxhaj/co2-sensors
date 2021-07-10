package com.sensor.repository;

import com.sensor.repository.dao.SensorRecordDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SensorRecordRepository extends JpaRepository<SensorRecordDAO, Long> {

    @Query("SELECT s FROM SensorRecordDAO s WHERE s.sensorDAO.districtDAO.cityDAO.id= :id AND s.sensorDAO.districtDAO.cityDAO.username = ?#{ principal }")
    List<SensorRecordDAO> findByCityId(@Param("id") long id);
}