package com.challenge.demo.services;

import com.challenge.demo.models.ClimateConditionType;
import com.challenge.demo.models.ClimateForecast;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClimateForecastService {
    private List<ClimateForecast> climateForecasts;

    public ClimateForecastService() {
        this.climateForecasts = new ArrayList<>();
    }
    public List<ClimateForecast> getForecastByDate(LocalDate date) {
        return this.climateForecasts.stream().filter(forecast -> date.equals(forecast.getDate())).toList();
    }

    public List<ClimateForecast> getClimateForecasts() {
        return this.climateForecasts;
    }

    public void setClimateForecasts(List<ClimateForecast> forecasts) {
        this.climateForecasts = forecasts;
    }

    public List<ClimateForecast> getForecastsByCondition(ClimateConditionType climateCondition) {
        return this.climateForecasts.stream().filter(forecast -> climateCondition.equals(forecast.getCondition())).toList();
    }
}
