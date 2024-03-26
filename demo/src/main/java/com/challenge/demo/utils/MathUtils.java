package com.challenge.demo.utils;

import com.challenge.demo.models.Coordinates;
import java.lang.Math;

public class MathUtils {
    public static double getTriangleArea(Coordinates vertex1, Coordinates vertex2, Coordinates vertex3) {
        double x1 = vertex1.getX();
        double y1 = vertex1.getY();
        double x2 = vertex2.getX();
        double y2 = vertex2.getY();
        double x3 = vertex3.getX();
        double y3 = vertex3.getY();

        return round(Math.abs(x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2))/2.0);
    }

    public static double getTrianglePerimeter(Coordinates vertex1, Coordinates vertex2, Coordinates vertex3) {
        double x1 = vertex1.getX();
        double y1 = vertex1.getY();
        double x2 = vertex2.getX();
        double y2 = vertex2.getY();
        double x3 = vertex3.getX();
        double y3 = vertex3.getY();

        double side12 = Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
        double side23 = Math.sqrt(Math.pow(x2-x3, 2) + Math.pow(y2-y3, 2));
        double side13 = Math.sqrt(Math.pow(x1-x3, 2) + Math.pow(y1-y3, 2));

        return round(side12 + side23 + side13);
    }

    public static double round(double number) {
        return Math.round(number * 100.0) / 100.0;
    }

    public static boolean pointIsInsideTriangle(Coordinates point, Coordinates vertex1, Coordinates vertex2, Coordinates vertex3) {
        double x = point.getX();
        double y = point.getY();
        double x1 = vertex1.getX();
        double y1 = vertex1.getY();
        double x2 = vertex2.getX();
        double y2 = vertex2.getY();
        double x3 = vertex3.getX();
        double y3 = vertex3.getY();

        double denominator = ((y2 - y3)*(x1 - x3) + (x3 - x2)*(y1 - y3));
        double alpha = ((y2 - y3)*(x - x3) + (x3 - x2)*(y - y3)) / denominator;
        double beta = ((y3 - y1)*(x - x3) + (x1 - x3)*(y - y3)) / denominator;
        double gamma = 1.0 - alpha - beta;

        return alpha > 0 && beta > 0 && gamma > 0;
    }
}
