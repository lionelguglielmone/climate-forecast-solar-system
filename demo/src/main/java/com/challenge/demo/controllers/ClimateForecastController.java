package com.challenge.demo.controllers;

import com.challenge.demo.models.ClimateConditionType;
import com.challenge.demo.models.ClimateForecast;
import com.challenge.demo.models.ForecastResponse;
import com.challenge.demo.services.ClimateForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class ClimateForecastController {
    @Autowired
    private ClimateForecastService climateForecastService;

    @GetMapping("/climate")
    public ResponseEntity<ForecastResponse> getAllClimateForecasts(
            @RequestParam(value="condition") Optional<ClimateConditionType> climateCondition,
            @RequestParam(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> date) throws Exception {
        try {
            ClimateConditionType condition = climateCondition.orElse(null);
            LocalDate filterDate = date.orElse(null);
            if (filterDate != null) {
                List<ClimateForecast> forecasts = climateForecastService.getForecastByDate(filterDate);
                return ResponseEntity.ok(new ForecastResponse(forecasts));
            }

            if (condition != null) {
                List<ClimateForecast> forecasts = climateForecastService.getForecastsByCondition(condition);
                return ResponseEntity.ok(new ForecastResponse(forecasts));
            }

            List<ClimateForecast> forecasts = climateForecastService.getClimateForecasts();
            return ResponseEntity.ok(new ForecastResponse(forecasts));
        } catch (MethodArgumentTypeMismatchException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
