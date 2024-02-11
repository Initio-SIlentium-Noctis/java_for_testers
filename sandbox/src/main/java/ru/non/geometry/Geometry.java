package ru.non.geometry;

import ru.non.geometry.figures.Rectangle;
import ru.non.geometry.figures.Square;

public class Geometry {

    public static void main(String[] args) {
        Square.printSquareArea(new Square(4));
        Rectangle.printRectangleArea(new Rectangle(3, 5));
    }

}
