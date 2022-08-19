package ru.point;

public class Main {
    public static void main(String[] args) {

        Point p1 = new Point(15,-12);
        Point p2 = new Point(-15, 33);

        double dist = p1.distance(p2);

        System.out.println("Расстояние между двумя точками = " + dist);
    }

}
