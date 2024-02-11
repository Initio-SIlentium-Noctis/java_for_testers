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

}
