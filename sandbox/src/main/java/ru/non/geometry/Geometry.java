package ru.non.geometry;

import ru.non.geometry.figures.Square;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Geometry {

    public static void main(String[] args) {
        Supplier<Square> randomSquare = () -> new Square(new Random().nextDouble(100.0));
        var squares = Stream.generate(randomSquare).limit(5);
        squares.peek(Square::printSquareArea).forEach(Square::printSquarePerimeter);
    }

}
