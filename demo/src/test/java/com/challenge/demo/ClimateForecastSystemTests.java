package com.challenge.demo;

import com.challenge.demo.models.Coordinates;
import com.challenge.demo.models.Planet;
import com.challenge.demo.services.SolarSystemService;
import com.challenge.demo.utils.MathUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class ClimateForecastSystemTests {

	@Test
	void getAngleAtTimeReturnsCorrectAngle() {
		Planet planet =  new Planet("ferengi", -1, 500, 7.5);
		double days = 16;
		double angle = planet.getAngleAtTime(days);
		Assertions.assertEquals(-8.5, angle);
	}

	@Test
	void getTriangleAreaReturnsCorrectArea() {
		Coordinates p1 = new Coordinates(-2,-3);
		Coordinates p2 = new Coordinates(3, 4);
		Coordinates p3 = new Coordinates(-1, 2);
		double area = MathUtils.getTriangleArea(p1, p2, p3);
		Assertions.assertEquals(9, area);
	}

	@Test
	void getTrianglePerimeterReturnsCorrectPerimeter() {
		Coordinates p1 = new Coordinates(-2,-3);
		Coordinates p2 = new Coordinates(3, 4);
		Coordinates p3 = new Coordinates(-1, 2);
		double area = MathUtils.getTrianglePerimeter(p1, p2, p3);
		Assertions.assertEquals(18.17, area);
	}

	@Test
	void pointInsideTriangleReturnsTrueIfPointInside() {
		Coordinates p1 = new Coordinates(-2,-3);
		Coordinates p2 = new Coordinates(3, 4);
		Coordinates p3 = new Coordinates(-1, 2);
		Coordinates p = new Coordinates(-1, 0.5);
		boolean isInside = MathUtils.pointIsInsideTriangle(p, p1, p2, p3);
		Assertions.assertTrue(isInside);
	}

	@Test
	void pointInsideTriangleReturnsFalseIfPointNotInside() {
		Coordinates p1 = new Coordinates(-2,-3);
		Coordinates p2 = new Coordinates(3, 4);
		Coordinates p3 = new Coordinates(-1, 2);
		Coordinates p = new Coordinates(-3, -3);
		boolean isInside = MathUtils.pointIsInsideTriangle(p, p1, p2, p3);
		Assertions.assertFalse(isInside);
	}

	@Test
	void pointInsideTriangleReturnsFalseIfPointOnEdge() {
		Coordinates p1 = new Coordinates(-2,-3);
		Coordinates p2 = new Coordinates(3, 4);
		Coordinates p3 = new Coordinates(-1, 2);
		Coordinates p = new Coordinates(-2, -3);
		boolean isInside = MathUtils.pointIsInsideTriangle(p, p1, p2, p3);
		Assertions.assertFalse(isInside);
	}

	@Test
	void solarSystemServiceReturnsPositionAtGivenDays() {
		SolarSystemService solarSystemService = new SolarSystemService();
		ArrayList<Planet> planets = solarSystemService.getPlanets();
		Coordinates position = solarSystemService.getPositionOnDate(10, planets.get(0));
		Assertions.assertEquals(492.40, position.getX());
		Assertions.assertEquals(-86.82, position.getY());
	}

	@Test
	void arePlanetsAlignedToSunReturnsTrueIfPlanetsAreAlignedToSun() {
		SolarSystemService solarSystemService = new SolarSystemService();
		boolean aligned = solarSystemService.arePlanetsAlignedToSun(720);
		Assertions.assertTrue(aligned);
	}

	@Test
	void arePlanetsAlignedToSunReturnsFalseIfPlanetsAreNotAlignedToSun() {
		SolarSystemService solarSystemService = new SolarSystemService();
		boolean aligned = solarSystemService.arePlanetsAlignedToSun(10);
		Assertions.assertFalse(aligned);
	}

	@Test
	void arePlanetsAlignedToSunReturnsFalseIfPlanetsAreAlignedButNotToSun() {
		SolarSystemService solarSystemService = new SolarSystemService();
		boolean aligned = solarSystemService.arePlanetsAligned(90);
		Assertions.assertTrue(aligned);
		boolean alignedToSun = solarSystemService.arePlanetsAlignedToSun(90);
		Assertions.assertFalse(alignedToSun);
	}

	@Test
	void arePlanetsOnlyAlignedBetweenThemReturnsTrue() {
		SolarSystemService solarSystemService = new SolarSystemService();
		boolean aligned = solarSystemService.arePlanetsOnlyAlignedBetweenThem(90);
		Assertions.assertTrue(aligned);
	}

	@Test
	void arePlanetsOnlyAlignedBetweenThemReturnsFalseWhenAlignedToSun() {
		SolarSystemService solarSystemService = new SolarSystemService();
		boolean aligned = solarSystemService.arePlanetsOnlyAlignedBetweenThem(720);
		Assertions.assertFalse(aligned);
	}

	@Test
	void isSunInsideTriangleReturnsTrueWhenSunInside() {
		SolarSystemService solarSystemService = new SolarSystemService();
		boolean inside = solarSystemService.isSunInsideTriangle(25);
		Assertions.assertTrue(inside);
	}

	@Test
	void isSunInsideTriangleReturnsFalseWhenSunNotInside() {
		SolarSystemService solarSystemService = new SolarSystemService();
		boolean inside = solarSystemService.isSunInsideTriangle(1);
		Assertions.assertFalse(inside);
	}

	@Test
	void isSunInsideTriangleReturnsFalseWhenAligned() {
		SolarSystemService solarSystemService = new SolarSystemService();
		boolean inside = solarSystemService.isSunInsideTriangle(90);
		Assertions.assertFalse(inside);
	}
}
