package com.challenge.demo.models;

import java.time.LocalDate;

public class ClimateForecast {
    private final LocalDate date;
    private ClimateConditionType condition;
    private double perimeter = 0;

    public ClimateForecast(long days, ClimateConditionType condition) {
        this.date = (LocalDate.of(2024, 01, 01)).plusDays(days);
        this.condition = condition;
    }

    public ClimateConditionType getCondition() {
        return this.condition;
    }

    public void setCondition(ClimateConditionType condition) {
        this.condition = condition;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    public double getPerimeter() {
        return this.perimeter;
    }

    public LocalDate getDate() {
        return this.date;
    }
}
