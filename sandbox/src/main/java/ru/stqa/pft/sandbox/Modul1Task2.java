package ru.stqa.pft.sandbox;

/**
 * Created by igolu on 15.09.2016.
 */
public class Modul1Task2 {

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.sqrt(p1.p2-p1.p1) + Math.sqrt(p2.p2-p2.p1));
    }

    public static void main(String[] args) {

        Point p1 = new Point(2, 3);
        Point p2 = new Point(1, 7);
        System.out.println("Length between points in the plane" + "coordinates x(" +  p1.p2 +
                "," + p1.p2 + ") and y(" + p2.p1 + "," + p2.p2 + ") = " + distance(p1, p2));
    }

}
