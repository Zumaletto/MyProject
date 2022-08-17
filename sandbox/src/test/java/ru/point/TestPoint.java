package ru.point;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class TestPoint{

    @Test
    public void testLenghtRoundCeil(){
        Point p1 = new Point(42,38);
        Point p2 = new Point(-33,28);
        double dist = Math.ceil(Point.distance(p1,p2));
        Assert.assertEquals(dist,76.0);
    }

    @Test
    public void testLenghtRoundFloor(){
        Point p1 = new Point(42,38);
        Point p2 = new Point(-33,28);
        double dist = Math.floor(Point.distance(p1,p2));
        Assert.assertEquals(dist,75.0);
    }

    @Test
    public void testLenghtRound(){
        Point p1 = new Point(42,38);
        Point p2 = new Point(-33,28);
        double dist = Math.round(Point.distance(p1,p2));
        Assert.assertEquals(dist,76);
    }

    @Test
    public void LenghtRoundWereCoorNull1(){
        Point p1 = new Point(42,0);
        Point p2 = new Point(-58,0);
        double dist = Math.round(Point.distance(p1,p2));
        Assert.assertEquals(dist,100);
    }
    @Test
    public void LenghtRoundWereCoorNull2(){
        Point p1 = new Point(42,0);
        Point p2 = new Point(0,27);
        double dist = Math.round(Point.distance(p1,p2));
        Assert.assertEquals(dist,50);
    }

}
