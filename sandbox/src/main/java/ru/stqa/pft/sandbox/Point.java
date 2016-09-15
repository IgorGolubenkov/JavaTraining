package ru.stqa.pft.sandbox;

/**
 * Created by igolu on 15.09.2016.
 */
public class Point {

    public int p1;
    public int p2;

    public Point(int p1, int p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
    public double distance() {
        return Math.sqrt(this.p2 - this.p1);
    }

}
