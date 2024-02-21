package ru.non.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculateTrianglePerimeter() {
        Triangle t = new Triangle(5,6,7);
        double result = t.trianglePerimeter();
        Assertions.assertEquals(18, result);
    }

    @Test
    void canCalculateTriangleArea() {
        Triangle t = new Triangle(5,6,7);
        double result = t.triangleArea();
        Assertions.assertEquals(14.696938456699069, result);
    }

    @Test
    void checkTriangleSideIsPositive() {
        try {
            new Triangle(-5, 6, 7);
            Assertions.fail();
        } catch(IllegalArgumentException exception) {}
        try {
            new Triangle(5, -6, 7);
            Assertions.fail();
        } catch(IllegalArgumentException exception) {}
        try {
            new Triangle(5, 6, -7);
            Assertions.fail();
        } catch(IllegalArgumentException exception) {}
        try {
            new Triangle(5, 0, 7);
            Assertions.fail();
        } catch(IllegalArgumentException exception) {}
    }

    @Test
    void checkTriangleSidesSum() {
        try {
            new Triangle(5, 6, 12);
            Assertions.fail();
        } catch(IllegalArgumentException exception) {}
    }

    @Test
    void checkTriangleEquality() {
        Triangle t = new Triangle(5, 6, 7);

        if (!t.equals(new Triangle(5, 6, 7)) || !t.equals(new Triangle(5, 7, 6)) || !t.equals(new Triangle(6, 5, 7)) || !t.equals(new Triangle(6, 7, 5)) || !t.equals(new Triangle(7, 5, 6)) || !t.equals(new Triangle(7, 6, 5))) {
            Assertions.fail();
        }
    }

}
