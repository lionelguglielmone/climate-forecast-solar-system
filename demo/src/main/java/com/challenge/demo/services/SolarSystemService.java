package com.challenge.demo.services;

import com.challenge.demo.models.Coordinates;
import com.challenge.demo.models.Planet;
import com.challenge.demo.models.SolarSystem;
import com.challenge.demo.utils.MathUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SolarSystemService {
    private final SolarSystem solarSystem;

    public SolarSystemService() {
        this.solarSystem = new SolarSystem();
    }

    public ArrayList<Planet> getPlanets() {
        return this.solarSystem.getPlanets();
    }

    public Coordinates getPositionOnDate(long days, Planet planet) {
        double finalAngle = planet.getAngleAtTime(days);
        double finalAngleInRadians = Math.toRadians(finalAngle);
        double x = MathUtils.round(planet.getSunDistance() * Math.cos(finalAngleInRadians));
        double y = MathUtils.round(planet.getSunDistance() * Math.sin(finalAngleInRadians));

        return new Coordinates(x, y);
    }

    public boolean arePlanetsAlignedToSun(long days) {
        ArrayList<Planet> planets = this.solarSystem.getPlanets();
        double angle = planets.get(0).getAngleAtTime(days);
        for (Planet planet : planets) {
            double positionAngle = planet.getAngleAtTime(days);
            if (positionAngle != angle && (360 - positionAngle) != angle) {
                return false;
            }
        }

        return true;
    }

    public boolean arePlanetsAligned(long days) {
        ArrayList<Planet> planets = this.solarSystem.getPlanets();
        Coordinates planet1Position = this.getPositionOnDate(days, planets.get(0));
        Coordinates planet2Position = this.getPositionOnDate(days, planets.get(1));
        Coordinates planet3Position = this.getPositionOnDate(days, planets.get(2));
        return MathUtils.getTriangleArea(planet1Position, planet2Position, planet3Position) == 0.0;
    }

    public boolean arePlanetsOnlyAlignedBetweenThem(long days) {
        return this.arePlanetsAligned(days) && !this.arePlanetsAlignedToSun(days);
    }

    public boolean isSunInsideTriangle(long days) {
        if (!this.arePlanetsAligned(days)) {
            ArrayList<Planet> planets = this.solarSystem.getPlanets();
            Coordinates sunPosition = new Coordinates(0,0);
            Coordinates planet1Position = this.getPositionOnDate(days, planets.get(0));
            Coordinates planet2Position = this.getPositionOnDate(days, planets.get(1));
            Coordinates planet3Position = this.getPositionOnDate(days, planets.get(2));

            return MathUtils.pointIsInsideTriangle(sunPosition, planet1Position, planet2Position, planet3Position);
        }
        return false;
    }

    public double getTrianglePerimeter(long days) {
        ArrayList<Planet> planets = this.solarSystem.getPlanets();
        Coordinates planet1Position = this.getPositionOnDate(days, planets.get(0));
        Coordinates planet2Position = this.getPositionOnDate(days, planets.get(1));
        Coordinates planet3Position = this.getPositionOnDate(days, planets.get(2));
        return MathUtils.getTrianglePerimeter(planet1Position, planet2Position, planet3Position);
    }
}
