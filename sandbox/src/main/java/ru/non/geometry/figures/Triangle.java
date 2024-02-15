package ru.non.geometry.figures;

public class Triangle {

    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double trianglePerimeter() {
        return this.a + this.b + this.c;
    }

    public double triangleArea() {
        double p = 0.5 * trianglePerimeter();
        return Math.sqrt(p * (p - this.a) * (p - this.b) * (p - this.c));
    }



}
