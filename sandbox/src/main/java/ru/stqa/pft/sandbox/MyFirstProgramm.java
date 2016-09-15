package ru.stqa.pft.sandbox;

public class MyFirstProgramm {

    public static void main(String[] args) {
        hello("world");

        double len = 5;
        System.out.println("Area of a square, with parties = " + len + " = " + area(len));

        double a = 4;
        double b = 6;
        System.out.println("Area oa a rectangle, with parties = " + a + " and " + b + " = " + area(a, b));
    }
    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }
    public static double area(double l) {
        return l*l;
    }
    public static double area(double a, double b) {
        return a*b;
    }
}

