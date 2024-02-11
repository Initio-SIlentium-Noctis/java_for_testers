package ru.non.geometry.figures;

public class Square {

    private double side;

    public Square(double side) {
        this.side = side;
    }

    public static void printSquareArea(Square s) {
        String text = String.format("Площадь квадрата со стороной %f = %f ", s.side, s.squareArea());
        System.out.println(text);
    }

    public double squarePerimeter() {
        return this.side * 4;
    }

    public double squareArea() {
        return this.side * this.side;
    }




}
