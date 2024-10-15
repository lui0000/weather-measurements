package com.eliza.project3.WeatherMeasurements.services;


import com.eliza.project3.WeatherMeasurements.models.Measurement;
import com.eliza.project3.WeatherMeasurements.repositories.MeasurementRepository;
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

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    public Measurement findOne(int id) {
        Optional<Measurement> foundMeasurement = measurementRepository.findById(id);
        return foundMeasurement.orElseThrow(null);
    }

    @Transactional
    public void save(Measurement measurement) {
        measurementRepository.save(measurement);
    }


    public void enrichMeasurement(Measurement measurement) {
        measurement.setCreatedAt(LocalDateTime.now());
    }
}
