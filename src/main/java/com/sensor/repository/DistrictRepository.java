package com.sensor.repository;

import com.sensor.repository.dao.SensorDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DistrictRepository extends JpaRepository<SensorDAO, Long> {

    @Query("SELECT d FROM DistrictDAO d WHERE d.cityDAO.id= :id AND d.cityDAO.username = ?#{ principal }")
    SensorDAO findByCityId(@Param("id") long id);
}