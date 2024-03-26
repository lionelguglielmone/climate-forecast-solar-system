package com.challenge.demo.jobs;

import com.challenge.demo.models.ClimateConditionType;
import com.challenge.demo.models.ClimateForecast;
import com.challenge.demo.services.ClimateForecastService;
import com.challenge.demo.services.SolarSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class GenerateClimateForecastJob implements CommandLineRunner {
    private static final int YEARS_TO_STUDY = 10;
    private static final int DAYS_IN_A_YEAR = 365;

    @Autowired
    ClimateForecastService climateForecastService;

    @Override
    public void run(String... args) {
        try {
            this.setForecasts();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setForecasts() {
        double maxPerimeter = 0;
        SolarSystemService solarSystemService = new SolarSystemService();
        ArrayList<ClimateForecast> forecasts = new ArrayList<>();
        for (long i = 0; i < YEARS_TO_STUDY*DAYS_IN_A_YEAR; i++) {
            ClimateForecast forecast = new ClimateForecast(i, ClimateConditionType.OTHER);
            if (solarSystemService.arePlanetsAlignedToSun(i)) {
                forecast = new ClimateForecast(i, ClimateConditionType.DROUGHT);
            }
            if (solarSystemService.isSunInsideTriangle(i)) {
                forecast = new ClimateForecast(i, ClimateConditionType.RAINY);
                double perimeter = solarSystemService.getTrianglePerimeter(i);
                forecast.setPerimeter(perimeter);
                if (perimeter > maxPerimeter) {
                    maxPerimeter = perimeter;
                }
            }
            if (solarSystemService.arePlanetsOnlyAlignedBetweenThem(i)) {
                forecast = new ClimateForecast(i, ClimateConditionType.OPTIMUM_PRESSURE_AND_TEMPERATURE);
            }
            forecasts.add(forecast);
        }
        this.setMaxRainyDays(maxPerimeter, forecasts);
        climateForecastService.setClimateForecasts(forecasts);
    }

    private void setMaxRainyDays(double maxPerimeter, ArrayList<ClimateForecast> forecasts) {
        for (ClimateForecast forecast : forecasts) {
            if (forecast.getPerimeter() == maxPerimeter) {
                forecast.setCondition(ClimateConditionType.MAX_RAINY);
            }
        }
    }
}
