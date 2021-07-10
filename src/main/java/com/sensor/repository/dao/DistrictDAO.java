package com.sensor.repository.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "districts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDAO {

    @Id
    private long id;

    @Column(name = "district_name")
    private String districtName;

    @ManyToOne()
    @JoinColumn(name="city_id", updatable = false)
    private CityDAO cityDAO;
}