package ru.point;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPoint{

    @Test
    public void ifOneCoorOfPointNull(){
        Point p1 = new Point(0,3);
        Point p2 = new Point(115,0);
        double dist = Math.round(p1.distance(p2));
        Assert.assertEquals(dist,121.0);
    }

    @Test
    public void ifOneCoorOfPointMinus(){
        Point p1 = new Point(42,38);
        Point p2 = new Point(-33,28);
        double dist = Math.round(p1.distance(p2));
        Assert.assertEquals(dist,76.0);
    }


}
