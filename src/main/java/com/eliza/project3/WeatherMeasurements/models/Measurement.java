package com.eliza.project3.WeatherMeasurements.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Measurement")
public class Measurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Temperature should not be empty")
    @Size(min = -100, max = 100, message = "Temperature should be between 2 and 30 characters")
    @Column(name = "temperature")
    private int temperature;
    private boolean is_raining;
    private LocalDateTime created_at;
    private int sensor_id;
}
