package exe.ex4.geo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Polygon_2DTest {

    @Test
    void getAllPoints() {
        Polygon_2D polygon = new Polygon_2D();
        polygon.add(new Point_2D(1, 1));
        polygon.add(new Point_2D(2, 2));
        polygon.add(new Point_2D(3, 3));

        Point_2D[] points = polygon.getAllPoints();

        assertEquals(new Point_2D(1, 1), points[0]);
        assertEquals(new Point_2D(2, 2), points[1]);
        assertEquals(new Point_2D(3, 3), points[2]);
    }

    @Test
    void add() {
        Polygon_2D polygon = new Polygon_2D();
        polygon.add(new Point_2D(1, 1));
        polygon.add(new Point_2D(2, 2));

        Point_2D[] points = polygon.getAllPoints();

        assertEquals(new Point_2D(1, 1), points[0]);
        assertEquals(new Point_2D(2, 2), points[1]);
    }

    @Test
    void touch() {
        Polygon_2D polygon = new Polygon_2D();
        polygon.add(new Point_2D(1, 1));
        polygon.add(new Point_2D(2, 2));
        polygon.add(new Point_2D(3, 3));

        Point_2D a = new Point_2D(1, 1);
        Point_2D b = new Point_2D(3, 3);
        Point_2D c = new Point_2D(2, 1);
        Point_2D d = new Point_2D(2, 3);

        boolean isIntersecting = polygon.touch(a, b, c, d);

        assertTrue(isIntersecting);
    }

    @Test
    void testToString() {
        Polygon_2D polygon = new Polygon_2D();
        polygon.add(new Point_2D(1, 1));
        polygon.add(new Point_2D(2, 2));
        polygon.add(new Point_2D(3, 3));
        String polygonString = polygon.toString();
        assertEquals("Polygon_2D,1.0,1.0,2.0,2.0,3.0,3.0", polygonString);
    }

    @Test
    void mostRight() {
        Polygon_2D polygon = new Polygon_2D();
        polygon.add(new Point_2D(1, 2));
        polygon.add(new Point_2D(3, 4));
        polygon.add(new Point_2D(5, 6));

        double mostRightX = polygon.MostRight();

        assertEquals(5.0, mostRightX);
    }

    @Test
    void translate() {
        Polygon_2D polygon = new Polygon_2D();
        polygon.add(new Point_2D(1, 1));
        polygon.add(new Point_2D(2, 2));
        polygon.add(new Point_2D(3, 3));

        Point_2D translationVector = new Point_2D(1, 2);
        polygon.translate(translationVector);

        Point_2D[] translatedPoints = polygon.getAllPoints();
        Point_2D[] expectedPoints = {
                new Point_2D(2, 3),
                new Point_2D(3, 4),
                new Point_2D(4, 5)
        };

        assertArrayEquals(expectedPoints, translatedPoints);
    }

    @Test
    void copy() {
        Polygon_2D polygon = new Polygon_2D();
        polygon.add(new Point_2D(1, 1));
        polygon.add(new Point_2D(2, 2));
        polygon.add(new Point_2D(3, 3));

        Polygon_2D copiedPolygon = (Polygon_2D) polygon.copy();

        // Check if the points are copied correctly
        Point_2D[] originalPoints = polygon.getAllPoints();
        Point_2D[] copiedPoints = copiedPolygon.getAllPoints();

        assertArrayEquals(originalPoints, copiedPoints);

        // Check if modifying the copied polygon does not affect the original polygon
        copiedPolygon.add(new Point_2D(4, 4));

        assertEquals(3, polygon.getAllPoints().length);
        assertEquals(4, copiedPolygon.getAllPoints().length);
    }

    @Test
    void scale() {
        Polygon_2D polygon = new Polygon_2D();
        polygon.add(new Point_2D(1, 1));
        polygon.add(new Point_2D(2, 2));
        polygon.add(new Point_2D(3, 3));

        Point_2D center = new Point_2D(2, 2);
        double ratio = 2.0;
        polygon.scale(center, ratio);

        Point_2D[] scaledPoints = polygon.getAllPoints();
        Point_2D[] expectedPoints = {
                new Point_2D(0, 0),
                new Point_2D(2, 2),
                new Point_2D(4, 4)
        };

        assertArrayEquals(expectedPoints, scaledPoints);
    }

    @Test
    void rotate() {
        Polygon_2D polygon = new Polygon_2D();
        polygon.add(new Point_2D(1, 1));
        polygon.add(new Point_2D(2, 2));
        polygon.add(new Point_2D(3, 3));

        Point_2D center = new Point_2D(2, 2);
        double angleDegrees = 90.0;
        polygon.rotate(center, angleDegrees);

        Point_2D[] rotatedPoints = polygon.getAllPoints();
        Point_2D[] expectedPoints = {
                new Point_2D(3, 1),
                new Point_2D(2, 2),
                new Point_2D(1, 3)
        };

        assertArrayEquals(expectedPoints, rotatedPoints);
    }
}
