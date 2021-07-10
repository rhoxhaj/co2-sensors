package com.sensor.repository.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "sensors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorDAO {

    @Id
    private long id;

    @ManyToOne()
    @JoinColumn(name="district_id", updatable = false)
    private DistrictDAO districtDAO;
}