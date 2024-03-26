package com.challenge.demo.models;

import java.util.List;

public class ForecastResponse {
    private final List<ClimateForecast> data;
    private final long totalCount;

    public ForecastResponse(List<ClimateForecast> forecast) {
        this.data = forecast;
        this.totalCount = forecast.size();
    }

    public List<ClimateForecast> getData() {
        return this.data;
    }

    public long getTotalCount() {
        return this.totalCount;
    }
}
