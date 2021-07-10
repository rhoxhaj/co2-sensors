package com.sensor.api;

import com.sensor.model.SensorData;
import com.sensor.model.SensorRequestBody;
import com.sensor.repository.dao.SensorDAO;
import com.sensor.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.groupingBy;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("sensor")
public class SensorController {

    @Autowired
    SensorService sensorService;

    @GetMapping(path = "/city/{id}")
    public ResponseEntity<Map<String, List<SensorData>>> getCO2Level(@PathVariable("id") int cityId) {
        return Optional.ofNullable(sensorService.getSensorDataPerCity(cityId))
                .map(sensorData -> sensorData.stream().collect(groupingBy(SensorData::getDistrict)))
                .map(sensorMap -> ResponseEntity.ok().body(sensorMap))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping()
    public ResponseEntity insertRecords(@RequestBody SensorRequestBody sensorRequestBody) {
        SensorDAO sensorByID = sensorService.getSensorByID(sensorRequestBody.getSensorId());
        sensorService.insertSensorRecord(sensorRequestBody, sensorByID);
        return new ResponseEntity<>(OK);
    }

}