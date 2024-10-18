package com.eliza.project3.WeatherMeasurements.repositories;


import com.eliza.project3.WeatherMeasurements.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    Optional<Sensor> findByName(String name);

}
