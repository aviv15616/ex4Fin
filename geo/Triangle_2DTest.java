package exe.ex4.geo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Triangle_2DTest {

    @Test
    void getAllPoints() {
        // Test case: Triangle with points (1, 1), (5, 1), (3, 5)
        Point_2D p1 = new Point_2D(1, 1);
        Point_2D p2 = new Point_2D(5, 1);
        Point_2D p3 = new Point_2D(3, 5);
        GeoShape triangle = new Triangle_2D(p1, p2, p3);

        Point_2D[] expectedPoints = { p1, p2, p3 };
        Point_2D[] actualPoints = ((Triangle_2D) triangle).getAllPoints();

        assertArrayEquals(expectedPoints, actualPoints);
    }

    @Test
    void area() {
        // Test case: Triangle with points (1, 1), (5, 1), (3, 5)
        Point_2D p1 = new Point_2D(1, 1);
        Point_2D p2 = new Point_2D(5, 1);
        Point_2D p3 = new Point_2D(3, 5);
        GeoShape triangle = new Triangle_2D(p1, p2, p3);

        double expectedArea = 8.0;
        double actualArea = triangle.area();

        assertEquals(expectedArea, actualArea, 0.001);
    }

    @Test
    void perimeter() {
        // Test case: Triangle with points (1, 1), (5, 1), (3, 5)
        Point_2D p1 = new Point_2D(1, 1);
        Point_2D p2 = new Point_2D(5, 1);
        Point_2D p3 = new Point_2D(3, 5);
        GeoShape triangle = new Triangle_2D(p1, p2, p3);

        double expectedPerimeter = 12.944;
        double actualPerimeter = triangle.perimeter();

        assertEquals(expectedPerimeter, actualPerimeter, 0.001);
    }

    @Test
    void testToString() {
        // Test case: Triangle with points (1, 1), (5, 1), (3, 5)
        Point_2D p1 = new Point_2D(1, 1);
        Point_2D p2 = new Point_2D(5, 1);
        Point_2D p3 = new Point_2D(3, 5);
        GeoShape triangle = new Triangle_2D(p1, p2, p3);

        String expectedString = "Triangle_2D,1.0,1.0, 5.0,1.0, 3.0,5.0";
        String actualString = triangle.toString();

        assertEquals(expectedString, actualString);
    }

    @Test
    void translate() {
        // Test case: Triangle with points (1, 1), (5, 1), (3, 5)
        Point_2D p1 = new Point_2D(1, 1);
        Point_2D p2 = new Point_2D(5, 1);
        Point_2D p3 = new Point_2D(3, 5);
        GeoShape triangle = new Triangle_2D(p1, p2, p3);

        Point_2D translationVector = new Point_2D(2, 3);
        triangle.translate(translationVector);

        Point_2D p1Translated = new Point_2D(3, 4);
        Point_2D p2Translated = new Point_2D(7, 4);
        Point_2D p3Translated = new Point_2D(5, 8);

        Point_2D[] expectedPoints = { p1Translated, p2Translated, p3Translated };
        Point_2D[] actualPoints = ((Triangle_2D) triangle).getAllPoints();

        assertArrayEquals(expectedPoints, actualPoints);
    }

    @Test
    void copy() {
        // Test case: Triangle with points (1, 1), (5, 1), (3, 5)
        Point_2D p1 = new Point_2D(1, 1);
        Point_2D p2 = new Point_2D(5, 1);
        Point_2D p3 = new Point_2D(3, 5);
        GeoShape triangle = new Triangle_2D(p1, p2, p3);

        GeoShape copy = triangle.copy();

        assertNotSame(triangle, copy);
        assertTrue(copy instanceof Triangle_2D);

        Point_2D[] expectedPoints = ((Triangle_2D) triangle).getAllPoints();
        Point_2D[] actualPoints = ((Triangle_2D) copy).getAllPoints();

        assertArrayEquals(expectedPoints, actualPoints);
    }

    @Test
    void scale() {
        // Test case: Triangle with points (1, 1), (5, 1), (3, 5)
        Point_2D p1 = new Point_2D(1, 1);
        Point_2D p2 = new Point_2D(5, 1);
        Point_2D p3 = new Point_2D(3, 5);
        GeoShape triangle = new Triangle_2D(p1, p2, p3);

        Point_2D center = new Point_2D(3, 3);
        double ratio = 2.0;
        triangle.scale(center, ratio);

        Point_2D p1Scaled = new Point_2D(-1, -1);
        Point_2D p2Scaled = new Point_2D(7, -1);
        Point_2D p3Scaled = new Point_2D(3, 7);

        Point_2D[] expectedPoints = { p1Scaled, p2Scaled, p3Scaled };
        Point_2D[] actualPoints = ((Triangle_2D) triangle).getAllPoints();

        assertArrayEquals(expectedPoints, actualPoints);
    }

    @Test
    void rotate() {
        // Test case: Triangle with points (1, 1), (5, 1), (3, 5)
        Point_2D p1 = new Point_2D(1, 1);
        Point_2D p2 = new Point_2D(5, 1);
        Point_2D p3 = new Point_2D(3, 5);
        Triangle_2D triangle = new Triangle_2D(p1, p2, p3);

        Point_2D center = new Point_2D(3, 3);
        double angleDegrees = 90.0;
        triangle.rotate(center, angleDegrees);

        Point_2D p1Rotated = new Point_2D(5, 1);
        Point_2D p2Rotated = new Point_2D(5, 5);
        Point_2D p3Rotated = new Point_2D(1, 3);

        Point_2D[] expectedPoints = { p1Rotated, p2Rotated, p3Rotated };
        Point_2D[] actualPoints = triangle.getAllPoints();

        for (int i = 0; i < expectedPoints.length; i++) {
            assertTrue((Math.abs(expectedPoints[i].x() - triangle.getAllPoints()[i].x()) < 0.001)&&(expectedPoints[i].y() - triangle.getAllPoints()[i].y()) < 0.001);

        }
    }
}
