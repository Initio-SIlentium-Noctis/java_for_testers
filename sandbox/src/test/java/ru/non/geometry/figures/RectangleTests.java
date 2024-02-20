package ru.non.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangleTests {

    @Test
    void canCalculateRectanglePerimeter() {
        Rectangle r = new Rectangle(4,6);
        double result = r.rectanglePerimeter();
        Assertions.assertEquals(20, result);
    }

    @Test
    void canCalculateRectangleArea() {
        Rectangle r = new Rectangle(4,6);
        double result = r.rectangleArea();
        Assertions.assertEquals(24, result);
    }

    @Test
    void checkSquareSideIsPositive() {
        try {
            new Rectangle(-4,6);
            Assertions.fail();
        } catch(IllegalArgumentException exception) {}
        try {
            new Rectangle(-6, 4);
            Assertions.fail();
        } catch(IllegalArgumentException exception) {}
        try {
            new Rectangle(4, 0);
            Assertions.fail();
        } catch(IllegalArgumentException exception) {}
    }

}
