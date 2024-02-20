package ru.non.geometry.figures;

import java.util.Objects;

public record Rectangle(double a, double b) {


    public Rectangle {
        if (a <= 0 || b <= 0) {
            throw new IllegalArgumentException("Сторона прямоугольникане может быть отрицательной или равной нулю");
        }
    }

    public static void printRectangleArea(Rectangle r) {
        String text = String.format("Площадь прямоугольника со сторонами %f и %f = %f", r.a, r.b, r.rectangleArea());
        System.out.println(text);
    }

    public double rectanglePerimeter() {
        return (this.a * 2) + (this.b * 2);
    }

    public double rectangleArea() {
        return this.a * this.b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return (Double.compare(rectangle.a, this.a) == 0 && Double.compare(rectangle.b, this.b) == 0) || (Double.compare(rectangle.a, this.b) == 0 && (Double.compare(rectangle.b, this.a) == 0 ));
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }



}
