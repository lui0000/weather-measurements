package com.eliza.project3.WeatherMeasurements.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;


public class MeasurementDTO {


    @Min(value = -100, message = "Temperature should not be less than -100")
    @Max(value = 100, message = "Temperature should not be more than 100")
    private double temperature;


    private boolean raining;

    private SensorDTO owner;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getOwner() {
        return owner;
    }

    public void setOwner(SensorDTO owner) {
        this.owner = owner;
    }
}
