package com.eliza.project3.WeatherMeasurements.repositories;

import com.eliza.project3.WeatherMeasurements.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
}
