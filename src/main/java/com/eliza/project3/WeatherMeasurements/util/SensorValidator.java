package com.eliza.project3.WeatherMeasurements.util;

import com.eliza.project3.WeatherMeasurements.dto.SensorDTO;
import com.eliza.project3.WeatherMeasurements.models.Sensor;
import com.eliza.project3.WeatherMeasurements.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator{
    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;

        if (sensorService.getSensorByName(sensorDTO.getName()).isPresent())
            errors.rejectValue("fullName", "", "Sensor with this name already exists");
    }
}




