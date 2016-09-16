package ru.stqa.pft.sandbox;

/**
 * Created by igolu on 15.09.2016.
 */
public class Modul1Task2 {

    public static void main(String[] args) {

        Point p1 = new Point(2, 3);
        Point p2 = new Point(1, 7);
        System.out.println("Length between points in the plane" + "coordinates x(" +  p1.x +
                "," + p2.x + ") and y(" + p1.y + "," + p2.y + ") = " + p1.distance(p2));
    }
}
