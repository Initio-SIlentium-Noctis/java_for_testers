package ru.non.geometry.figures;

public class Rectangle {

    private double a;
    private double b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
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



}
