package ru.stqa.pft.sandbox;

public class Point {

    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public double distance(int х, int y) {
        int dx = this.x - х;
        int dy = this.y - y;
        return Math.sqrt(dx*dx + dy*dy);
    }
    public double distance(Point p) {
        return distance(p.x, p.y);
    }
}
