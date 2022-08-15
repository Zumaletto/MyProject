package ru.point;

public class Point {
   double x, y;
   public Point (double x, double y){
       this.x = x;
       this.y = y;
   }
    public static double distance(Point p1, Point p2) {

        double x = p1.x - p2.x;
        double y = p1.y - p2.y;
        double dist = Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2)));
        return dist;
    }
}
