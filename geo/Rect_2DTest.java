package exe.ex4.geo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Rect_2DTest {

    @Test
    public void testContains() {
        // Test case 1: Rectangle with points (1, 1), (5, 4)
        Point_2D p1 = new Point_2D(1, 1);
        Point_2D p2 = new Point_2D(5, 4);
        GeoShape rectangle1 = new Rect_2D(p1, p2);

        Point_2D point1 = new Point_2D(3, 2);
        assertTrue(rectangle1.contains(point1));

        Point_2D point2 = new Point_2D(1, 3);
        assertTrue(rectangle1.contains(point2));

        Point_2D point3 = new Point_2D(6, 3);
        assertFalse(rectangle1.contains(point3));

        // Test case 2: Rectangle with points (0, 2), (6, 4)
        Point_2D p3 = new Point_2D(0, 2);
        Point_2D p4 = new Point_2D(6, 4);
        GeoShape rectangle2 = new Rect_2D(p3, p4);

        Point_2D point4 = new Point_2D(3, 3);
        assertTrue(rectangle2.contains(point4));

        Point_2D point5 = new Point_2D(0, 4);
        assertTrue(rectangle2.contains(point5));

        Point_2D point6 = new Point_2D(5, 5);
        assertFalse(rectangle2.contains(point6));
    }

    @Test
    void area() {
        // Test case 1: Rectangle with points (1, 1), (5, 4)
        Point_2D p1 = new Point_2D(1, 1);
        Point_2D p2 = new Point_2D(5, 4);
        GeoShape rectangle1 = new Rect_2D(p1, p2);
        double expectedArea1 = 12.0;
        assertEquals(expectedArea1, rectangle1.area());

        // Test case 2: Rectangle with points (0, 2), (6, 4)
        Point_2D p3 = new Point_2D(0, 2);
        Point_2D p4 = new Point_2D(6, 4);
        GeoShape rectangle2 = new Rect_2D(p3, p4);
        double expectedArea2 = 12.0;
        assertEquals(expectedArea2, rectangle2.area());
    }

    @Test
    void testToString() {
        Point_2D p1 = new Point_2D(1, 1);
        Point_2D p2 = new Point_2D(5, 4);
        GeoShape rectangle = new Rect_2D(p1, p2);
        String expectedString = "Rect_2D,1.0,1.0, 5.0,4.0, 1.0,4.0, 5.0,1.0";
        assertEquals(expectedString, rectangle.toString());
    }

    @Test
    void getAllPoints() {
        Point_2D p1 = new Point_2D(1, 1);
        Point_2D p2 = new Point_2D(5, 4);
        Rect_2D rectangle = new Rect_2D(p1, p2);
        Point_2D[] expectedPoints = { p1, p2, new Point_2D(1, 4), new Point_2D(5, 1) };
        assertArrayEquals(expectedPoints, rectangle.getAllPoints());
    }

    @Test
    void perimeter() {
        Point_2D p1 = new Point_2D(1, 1);
        Point_2D p2 = new Point_2D(5, 4);
        GeoShape rectangle = new Rect_2D(p1, p2);
        double expectedPerimeter = 14.0;
        assertEquals(expectedPerimeter, rectangle.perimeter());
    }

    @Test
    void translate() {
        Point_2D p1 = new Point_2D(1, 1);
        Point_2D p2 = new Point_2D(5, 4);
        Rect_2D rectangle = new Rect_2D(p1, p2);

        Point_2D translationVector = new Point_2D(2, 3);
        rectangle.translate(translationVector);

        Point_2D translatedP1 = new Point_2D(3, 4);
        Point_2D translatedP2 = new Point_2D(7, 7);
        Point_2D translatedP3 = new Point_2D(3, 7);
        Point_2D translatedP4 = new Point_2D(7, 4);

        Point_2D[] expectedPoints = { translatedP1, translatedP2, translatedP3, translatedP4 };
        assertArrayEquals(expectedPoints, rectangle.getAllPoints());
    }

    @Test
    void copy() {
        Point_2D p1 = new Point_2D(1, 1);
        Point_2D p2 = new Point_2D(5, 4);
        GeoShape rectangle = new Rect_2D(p1, p2);

        GeoShape copy = rectangle.copy();
        assertEquals(rectangle.toString(), copy.toString());
    }

    @Test
    void scale() {
        Point_2D p1 = new Point_2D(1, 1);
        Point_2D p2 = new Point_2D(5, 4);
        Rect_2D rectangle = new Rect_2D(p1, p2);

        Point_2D center = new Point_2D(3, 2);
        double ratio = 2.0;
        rectangle.scale(center, ratio);

        Point_2D scaledP1 = new Point_2D(-1, 0);
        Point_2D scaledP2 = new Point_2D(7, 4);
        Point_2D scaledP3 = new Point_2D(-1, 4);
        Point_2D scaledP4 = new Point_2D(7, 0);

        Point_2D[] expectedPoints = { scaledP1, scaledP2, scaledP3, scaledP4 };
        for (int i = 0; i < expectedPoints.length; i++) {
            assertTrue((Math.abs(expectedPoints[i].x() - rectangle.getAllPoints()[i].x()) < 0.001)&&(expectedPoints[i].y() - rectangle.getAllPoints()[i].y()) < 0.001);

        }
    }

    @Test
    void rotate() {
        Point_2D p1 = new Point_2D(1, 1);
        Point_2D p2 = new Point_2D(5, 4);
        Rect_2D rectangle = new Rect_2D(p1, p2);

        Point_2D center = new Point_2D(3, 2);
        double angleDegrees = 90.0;
        rectangle.rotate(center, angleDegrees);

        Point_2D rotatedP1 = new Point_2D(4, 0);
        Point_2D rotatedP2 = new Point_2D(1, 4);
        Point_2D rotatedP3 = new Point_2D(1, 0);
        Point_2D rotatedP4 = new Point_2D(4, 4);

        Point_2D[] expectedPoints = { rotatedP1, rotatedP2, rotatedP3, rotatedP4 };

        for (int i = 0; i < expectedPoints.length; i++) {
            assertTrue((Math.abs(expectedPoints[i].x() - rectangle.getAllPoints()[i].x()) < 0.001)&&(expectedPoints[i].y() - rectangle.getAllPoints()[i].y()) < 0.001);

        }
    }
}
