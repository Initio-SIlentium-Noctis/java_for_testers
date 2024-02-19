package ru.non.geometry.figures;

public record Triangle(double a, double b, double c) {


    public Triangle {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("Сторона треугольника не может быть отрицательной или равной нулю");
        }
        if (a + b < c || a + c < b || b + c < a) {
            throw new IllegalArgumentException("Cумма двух любых сторон треугольника должна быть не меньше третьей стороны");
        }
    }

    public double trianglePerimeter() {
        return this.a + this.b + this.c;
    }

    public double triangleArea() {
        double p = 0.5 * trianglePerimeter();
        return Math.sqrt(p * (p - this.a) * (p - this.b) * (p - this.c));
    }



}
