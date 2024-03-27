package ru.non.geometry.figures;

public record Square(double side) {


    public Square {
        if (side <= 0) {
            throw new IllegalArgumentException("Сторона квадрата не может быть отрицательной или равной нулю");
        }
    }

    public static void printSquareArea(Square s) {
        String text = String.format("Площадь квадрата со стороной %f = %f ", s.side, s.squareArea());
        System.out.println(text);
    }

    public static void printSquarePerimeter(Square s) {
        String text = String.format("Периметр квадрата со стороной %f = %f ", s.side, s.squarePerimeter());
        System.out.println(text);
    }

    public double squarePerimeter() {
        return this.side * 4;
    }

    public double squareArea() {
        return this.side * this.side;
    }




}
