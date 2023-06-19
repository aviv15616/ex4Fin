package exe.ex4.geo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Circle_2DTest {

    @Test
    void testToString() {
        Point_2D center = new Point_2D(3, 4);
        double radius = 5;
        Circle_2D circle = new Circle_2D(center, radius);

        String expected = "Circle_2D,3.0,4.0, 5.0";
        assertEquals(expected, circle.toString());
    }

    @Test
    void distanceFromCenter() {
        Point_2D center = new Point_2D(0, 0);
        double radius = 5;
        Circle_2D circle = new Circle_2D(center, radius);

        Point_2D point = new Point_2D(3, 4);
        double expectedDistance = 5;
        assertEquals(expectedDistance, circle.DistanceFromCenter(point));
    }

    @Test
    void contains() {
        Point_2D center = new Point_2D(3, 4);
        double radius = 5;
        Circle_2D circle = new Circle_2D(center, radius);

        Point_2D insidePoint = new Point_2D(5, 6);
        Point_2D outsidePoint = new Point_2D(10, 10);

        assertTrue(circle.contains(insidePoint));
        assertFalse(circle.contains(outsidePoint));
    }

    @Test
    void getAllPoints() {
        Point_2D center = new Point_2D(3, 4);
        double radius = 5;
        Circle_2D circle = new Circle_2D(center, radius);

        Point_2D[] expectedPoints = { center };
        assertArrayEquals(expectedPoints, circle.getAllPoints());
    }

    @Test
    void area() {
        Point_2D center = new Point_2D(3, 4);
        double radius = 5;
        Circle_2D circle = new Circle_2D(center, radius);

        double expectedArea = Math.PI * radius * radius;
        assertEquals(expectedArea, circle.area());
    }

    @Test
    void perimeter() {
        Point_2D center = new Point_2D(3, 4);
        double radius = 5;
        Circle_2D circle = new Circle_2D(center, radius);

        double expectedPerimeter = 2 * Math.PI * radius;
        assertEquals(expectedPerimeter, circle.perimeter());
    }

    @Test
    void translate() {
        Point_2D center = new Point_2D(3, 4);
        double radius = 5;
        Circle_2D circle = new Circle_2D(center, radius);

        Point_2D translation = new Point_2D(2, -3);
        circle.translate(translation);

        Point_2D expectedCenter = new Point_2D(5, 1);
        assertEquals(expectedCenter, circle.getCenter());
    }

    @Test
    void copy() {
        Point_2D center = new Point_2D(3, 4);
        double radius = 5;
        Circle_2D circle = new Circle_2D(center, radius);

        GeoShape copy = circle.copy();
        assertEquals(circle.toString(), copy.toString());
        assertNotSame(circle, copy);
    }

    @Test
    void scale() {
        Point_2D center = new Point_2D(3, 4);
        double radius = 5;
        Circle_2D circle = new Circle_2D(center, radius);

        Point_2D scaleCenter = new Point_2D(1, 1);
        double scaleRatio = 2;
        circle.scale(scaleCenter, scaleRatio);

        assertEquals(10, circle.getRadius());
        assertEquals(5, circle.getCenter().x());
        assertEquals(7, circle.getCenter().y());
    }

    @Test
    void rotate() {
        Point_2D center = new Point_2D(3, 4);
        double radius = 5;
        Circle_2D circle = new Circle_2D(center, radius);

        Point_2D rotateCenter = new Point_2D(0, 0);
        double rotateAngle = 90;
        circle.rotate(rotateCenter, rotateAngle);

        assertEquals(-4, circle.getCenter().x(), 0.0001);
        assertEquals(3, circle.getCenter().y(), 0.0001);
    }
}
