package ru.point;

public class Point {
   double x, y;
   public Point (double x, double y){
       this.x = x;
       this.y = y;
   }
    public double distance(Point p) {

        double x = this.x - p.x;
        double y = this.y - p.y;
        double dist = Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2)));
        return dist;
    }
}
