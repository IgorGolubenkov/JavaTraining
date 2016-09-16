package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by igolu on 16.09.2016.
 */
public class PointTests {

    @Test
    public void testDistanceOne() {
        Point p1 = new Point(0, 4);
        Point p2 = new Point(0, 7);
        double dist = p2.distance(p1);
        Assert.assertEquals(dist, 3.0);
    }
    @Test
    public void testDistanceTwo() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 0);
        double dist = p2.distance(p1);
        Assert.assertEquals(dist, 0.0);
    }
    @Test
    public void testDistanceThree() {
        Point p1 = new Point(-2, -2);
        Point p2 = new Point(-5, -5);
        double dist = p2.distance(p1);
        Assert.assertEquals(dist, 4.242640687119285);
    }
}
