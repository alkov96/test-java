package ru.stqa.pft.sandbox;

/**
 * @author a.kovtun
 * @since 19.03.2018.
 */
public class Point {
    double x;
    double y;
    Point(double a, double b) {
        x = a;
        y = b;
    }
    public double distance1 (Point p1) {
        double formula = Math.pow(p1.x - this.x,2) + Math.pow(this.y - p1.y,2);
        double res = Math.sqrt(formula);
        return res;
    }
}

