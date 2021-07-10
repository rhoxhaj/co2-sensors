package com.sensor.service;

import com.sensor.exception.SensorNotAvailableException;
import com.sensor.model.SensorData;
import com.sensor.model.SensorRequestBody;
import com.sensor.repository.SensorRecordRepository;
import com.sensor.repository.SensorRepository;
import com.sensor.repository.dao.SensorDAO;
import com.sensor.repository.dao.SensorRecordDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class SensorService {

    @Autowired
    SensorRecordRepository sensorRecordRepository;

    @Autowired
    SensorRepository sensorRepository;

    public long insertSensorRecord(SensorRequestBody sensorRequestBody, SensorDAO sensorByID) {
        SensorRecordDAO sensorRecordDAO = SensorRecordDAO.builder()
                .sensorDAO(sensorByID)
                .co2Level(sensorRequestBody.getCo2Level())
                .insertionTime(LocalDateTime.now())
                .build();
        SensorRecordDAO save = sensorRecordRepository.save(sensorRecordDAO);
        return save.getId();
    }

    public SensorDAO getSensorByID(int id) {
        if (id <= 0) throw new SensorNotAvailableException(id);
        SensorDAO byId = sensorRepository.findById(id);
        if (isNull(byId)) throw new SensorNotAvailableException(id);
        return byId;
    }

    public List<SensorData> getSensorDataPerCity(int cityId) {
        List<SensorRecordDAO> all = sensorRecordRepository.findByCityId(cityId);
        return all.stream().map(this::toSensorData).collect(Collectors.toList());
    }

    private SensorData toSensorData(SensorRecordDAO sensorRecordDAO) {
        return SensorData.builder().district(sensorRecordDAO.getSensorDAO().getDistrictDAO().getDistrictName())
                .sensor(sensorRecordDAO.getSensorDAO().getId())
                .city(sensorRecordDAO.getSensorDAO().getDistrictDAO().getCityDAO().getCityName())
                .co2Level(sensorRecordDAO.getCo2Level())
                .build();
    }
}
