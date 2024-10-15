package com.eliza.project3.WeatherMeasurements.dto;

import com.eliza.project3.WeatherMeasurements.models.Sensor;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class MeasurementDTO {

    @NotEmpty(message = "Temperature should not be empty")
    @Size(min = -100, max = 100, message = "Temperature should be between 2 and 30 characters")
    private int temperature;

    @NotEmpty(message = "isRaining should not be empty")
    private boolean isRaining;

    private Sensor owner;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public boolean isRaining() {
        return isRaining;
    }

    public void setRaining(boolean raining) {
        isRaining = raining;
    }

    public Sensor getOwner() {
        return owner;
    }

    public void setOwner(Sensor owner) {
        this.owner = owner;
    }
}
