package com.eliza.project3.WeatherMeasurements.services;

import com.eliza.project3.WeatherMeasurements.models.Measurement;
import com.eliza.project3.WeatherMeasurements.models.Sensor;
import com.eliza.project3.WeatherMeasurements.repositories.SensorRepository;
import com.eliza.project3.WeatherMeasurements.util.SensorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    @Autowired
    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }


    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }

    public Sensor findOne(int id) {
        Optional<Sensor> foundPerson = sensorRepository.findById(id);
        return foundPerson.orElseThrow(SensorNotFoundException::new);
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    public Optional<Sensor> getSensorByName(String name) {
        return sensorRepository.findByName(name);
    }


}
