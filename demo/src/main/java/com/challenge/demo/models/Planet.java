package com.challenge.demo.models;

public class Planet {
    private String name;
    private int angularVelocity;
    private int sunDistance;
    private double initialAngle;

    public Planet(String name, int angularVelocity, int sunDistance, double initialAngle) {
        this.name = name;
        this.angularVelocity = angularVelocity;
        this.sunDistance = sunDistance;
        this.initialAngle = initialAngle;
    }

    public int getAngularVelocity() {
        return this.angularVelocity;
    }

    public int getSunDistance() {
        return this.sunDistance;
    }

    public double getInitialAngle() {
        return this.initialAngle;
    }

    public double getAngleAtTime(double time) {
        return (this.getInitialAngle() + this.getAngularVelocity() * time) % 360;
    }
}
