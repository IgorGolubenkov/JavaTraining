package ru.stqa.pft.sandbox;

public class MyFirstProgramm {

    public static void main(String[] args) {
        hello("world");

        Square s = new Square(5);
        System.out.println("Area of a square, with parties = " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(4, 6);
        System.out.println("Area oa a rectangle, with parties = " + r.a + " and " + r.b + " = " + r.area());
    }
    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }
}

