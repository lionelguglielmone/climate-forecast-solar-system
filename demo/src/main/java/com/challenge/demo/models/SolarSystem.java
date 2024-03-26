package com.challenge.demo.models;

import java.util.ArrayList;
import java.util.Collections;

public class SolarSystem {
    private ArrayList<Planet> planets;

    public SolarSystem() {
        Planet ferengi = new Planet("ferengi", -1, 500, 0);
        Planet vulcano = new Planet("vulcano", 5, 1000, 0);
        Planet betasoide = new Planet("betasoide", -3, 2000, 0);
        ArrayList<Planet> planets = new ArrayList();
        Collections.addAll(planets, ferengi, vulcano, betasoide);

        this.planets = planets;
    }

    public ArrayList<Planet> getPlanets() {
        return this.planets;
    }
}
