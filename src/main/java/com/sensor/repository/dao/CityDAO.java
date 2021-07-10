package com.sensor.repository.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDAO {

    @Id
    private long id;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "username")
    private String username;
}