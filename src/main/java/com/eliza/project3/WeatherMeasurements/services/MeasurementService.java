package com.eliza.project3.WeatherMeasurements.services;


import com.eliza.project3.WeatherMeasurements.models.Measurement;
import com.eliza.project3.WeatherMeasurements.models.Sensor;
import com.eliza.project3.WeatherMeasurements.repositories.MeasurementRepository;
import com.eliza.project3.WeatherMeasurements.repositories.SensorRepository;
import com.eliza.project3.WeatherMeasurements.util.MeasurementNotFoundException;
import com.eliza.project3.WeatherMeasurements.util.SensorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorRepository sensorRepository) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
    }

    public List<Measurement> findAll() {

        return measurementRepository.findAll();
    }

    public Measurement findOne(int id) {
        Optional<Measurement> foundMeasurement = measurementRepository.findById(id);
        return foundMeasurement.orElseThrow(() -> new MeasurementNotFoundException("Measurement with id " + id + " not found"));
    }

    @Transactional
    public void save(Measurement measurement) {
        if (measurement.getSensor() != null) {
            Optional<Sensor> sensorOptional = sensorRepository.findByName(measurement.getSensor().getName());

            if (sensorOptional.isPresent()) {
                Sensor sensor = sensorOptional.get();
                measurement.setSensor(sensor);
                sensor.getMeasurements().add(measurement);
            } else {
                throw new SensorNotFoundException();
            }
        } else {
            throw new IllegalArgumentException("Measurement must have an owner");
        }

        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    public void enrichMeasurement(Measurement measurement) {
        measurement.setCreatedAt(LocalDateTime.now());

    }

    public String countOfRainyDays() {
        List<Measurement> measurements = measurementRepository.findAll();
        int count = 0;
        for(Measurement measurement : measurements) {
            if(measurement.isRaining()) {
                count++;
            }
            Integer.toString(count);

        }
        return "the number of rainy days is equal to: " + count;
    }
}
