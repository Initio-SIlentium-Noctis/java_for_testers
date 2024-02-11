package ru.non.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {

    @Test
    void canCalculateSquarePerimeter() {
        Square s = new Square(3);
        double result = s.squarePerimeter();
        Assertions.assertEquals(12, result);
    }

    @Test
    void canCalculateSquareArea() {
        Square s = new Square(5);
        double result = s.squareArea();
        Assertions.assertEquals(25, result);
    }

}
