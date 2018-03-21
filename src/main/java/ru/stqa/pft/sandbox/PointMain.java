package ru.stqa.pft.sandbox;

/**
 * @author a.kovtun
 * @since 19.03.2018.
 */
public class PointMain {
    public static double distance(Point p1, Point p2) {
        double formula = Math.pow(p1.x - p2.x,2) + Math.pow(p2.y - p1.y,2);
        double res = Math.sqrt(formula);
        return res;
    }

    public static void main(String[] args) {
        Point aa = new Point(1.0,2.0);
        Point bb = new Point(3.0,4.0);
        System.out.println(aa.distance1(bb));
        //System.out.println(distance(aa,bb));

    }
}
