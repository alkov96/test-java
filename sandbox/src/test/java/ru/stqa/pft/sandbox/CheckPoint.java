package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author a.kovtun
 * @since 21.03.2018.
 */
public class CheckPoint {
    @Test
    public void testArea() {
        Point a = new Point(1.0, 2.0);
        Point b = new Point(4.0, 6.0);
        Assert.assertEquals(a.distance1(b), (double)5);
        Assert.assertNotEquals(a.distance1(b), (double)7);
        Assert.assertTrue(a.distance1(b)>4);

    }
}
