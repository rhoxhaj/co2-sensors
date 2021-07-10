package com.sensor.service;

import com.sensor.exception.SensorNotAvailableException;
import com.sensor.model.SensorData;
import com.sensor.repository.SensorRecordRepository;
import com.sensor.repository.SensorRepository;
import com.sensor.repository.dao.CityDAO;
import com.sensor.repository.dao.DistrictDAO;
import com.sensor.repository.dao.SensorDAO;
import com.sensor.repository.dao.SensorRecordDAO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class SensorServiceTest {

    @Mock
    SensorRecordRepository sensorRecordRepository;
    @Mock
    SensorRepository sensorRepository;
    @InjectMocks
    private SensorService sensorService;

    @Test
    public void shouldReturnSensorById() {
        SensorDAO sensorDAO = new SensorDAO(1, null);
        given(sensorRepository.findById(1)).willReturn(sensorDAO);

        SensorDAO sensorByID = sensorService.getSensorByID(1);

        Assert.assertEquals(sensorByID.getId(), 1);
    }

    @Test(expected = SensorNotAvailableException.class)
    public void shouldNoTReturnSensorByIdAndThrowException() {
        sensorService.getSensorByID(-5);
    }

    @Test
    public void shouldReturnSensorDataPerCity() {
        CityDAO testCityDAO = new CityDAO(1, "test", "test");
        CityDAO testCityNotAvailableDAO = new CityDAO(1, "test2", "test2");
        DistrictDAO testDistrictDAO = new DistrictDAO(1, "testDistrict", testCityDAO);
        DistrictDAO testDistrict2DAO = new DistrictDAO(2, "testDistrict2", testCityNotAvailableDAO);
        SensorDAO sensorDAO = new SensorDAO(1, testDistrictDAO);
        SensorDAO sensor2DAO = new SensorDAO(2, testDistrict2DAO);
        SensorDAO sensor3DAO = new SensorDAO(3, testDistrictDAO);
        SensorRecordDAO sensorRecordDAO = new SensorRecordDAO(1, 2, null, sensorDAO);
        SensorRecordDAO sensorRecord2DAO = new SensorRecordDAO(2, 2, null, sensor2DAO);
        SensorRecordDAO sensorRecord3DAO = new SensorRecordDAO(3, 2, null, sensor3DAO);
        SensorRecordDAO sensorRecord32DAO = new SensorRecordDAO(4, 2, null, sensor3DAO);
        given(sensorRecordRepository.findByCityId(1)).willReturn(List.of(sensorRecordDAO, sensorRecord3DAO, sensorRecord32DAO));

        List<SensorData> sensorDataPerCity = sensorService.getSensorDataPerCity(1);

        Assert.assertNotNull(sensorDataPerCity);
        Assert.assertEquals(sensorDataPerCity.size(), 3);
        Assert.assertEquals(sensorDataPerCity.get(2).getCity(), "test");
        Assert.assertEquals(sensorDataPerCity.get(1).getSensor(), 3);
    }
}
