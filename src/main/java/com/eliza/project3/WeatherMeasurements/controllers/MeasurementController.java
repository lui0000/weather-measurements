package com.eliza.project3.WeatherMeasurements.controllers;


import com.eliza.project3.WeatherMeasurements.dto.MeasurementDTO;
import com.eliza.project3.WeatherMeasurements.models.Measurement;
import com.eliza.project3.WeatherMeasurements.services.MeasurementService;
import com.eliza.project3.WeatherMeasurements.util.MeasurementErrorResponse;
import com.eliza.project3.WeatherMeasurements.util.MeasurementNotCreatedException;
import com.eliza.project3.WeatherMeasurements.util.MeasurementNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<MeasurementDTO> getMeasurements() {
        return measurementService.findAll().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public MeasurementDTO getMeasurement(@PathVariable("id") int id) {
        //status 200
        return convertToMeasurementDTO(measurementService.findOne(id));
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new MeasurementNotCreatedException(errorMsg.toString());
        }
        measurementService.save(convertToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/count")
    public String getRainyDays() {
        return measurementService.countOfRainyDays();
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotFoundException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                "Measurement with this id wasn't found", System.currentTimeMillis()
        );
        //status 400
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotCreatedException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );
        //status 400
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }


    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
