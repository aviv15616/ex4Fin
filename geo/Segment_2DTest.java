package exe.ex4.geo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Segment_2DTest {
    @Test

    public void testContains() {
        Point_2D p1 = new Point_2D(0, 0);
        Point_2D p2 = new Point_2D(4, 4);
        GeoShape line = new Segment_2D(p1, p2);

        // Test case 1: Point lies on the line
        Point_2D point1 = new Point_2D(2, 2);
        assertTrue(line.contains(point1));

        // Test case 2: Point does not lie on the line
        Point_2D point2 = new Point_2D(3, 5);
        assertFalse(line.contains(point2));

        // Test case 3: Vertical line (infinite slope)
        Point_2D p3 = new Point_2D(2, 1);
        Point_2D p4 = new Point_2D(2, 5);
        GeoShape verticalLine = new Segment_2D(p3, p4);

        Point_2D point3 = new Point_2D(2, 3);
        assertTrue(verticalLine.contains(point3));

        Point_2D point4 = new Point_2D(3, 3);
        assertFalse(verticalLine.contains(point4));
    }

    @Test
    public void testPerimeter() {
        Point_2D p1 = new Point_2D(0, 0);
        Point_2D p2 = new Point_2D(3, 4);

        Segment_2D segment = new Segment_2D(p1, p2);

        assertEquals(10.0, segment.perimeter(), 0.001);
    }

    @Test
    public void testArea() {
        Point_2D p1 = new Point_2D(0, 0);
        Point_2D p2 = new Point_2D(3, 4);

        Segment_2D segment = new Segment_2D(p1, p2);

        assertEquals(0.0, segment.area(), 0.001);
    }

    @Test
    public void testScale() {
        Point_2D p1 = new Point_2D(1, 1);
        Point_2D p2 = new Point_2D(3, 4);
        Point_2D center = new Point_2D(2, 3);
        double ratio = 2.0;

        Segment_2D segment = new Segment_2D(p1, p2);
        segment.scale(center, ratio);

        Point_2D expectedP1 = new Point_2D(0, -1);
        Point_2D expectedP2 = new Point_2D(4, 7);

        assertEquals(expectedP1, segment.get_p1());
        assertEquals(expectedP2, segment.get_p2());
    }

    @Test
    public void testRotate() {
        Point_2D p1 = new Point_2D(1, 1);
        Point_2D p2 = new Point_2D(3, 4);
        Point_2D center = new Point_2D(2, 3);
        double angleDegrees = 90.0;

        Segment_2D segment = new Segment_2D(p1, p2);
        segment.rotate(center, angleDegrees);

        Point_2D expectedP1 = new Point_2D(3, 5);
        Point_2D expectedP2 = new Point_2D(2, 1);

        assertEquals(expectedP1, segment.get_p1());
        assertEquals(expectedP2, segment.get_p2());
    }

    @Test
    public void testCopy() {
        Point_2D p1 = new Point_2D(0, 0);
        Point_2D p2 = new Point_2D(3, 4);

        Segment_2D segment = new Segment_2D(p1, p2);
        Segment_2D copy = (Segment_2D) segment.copy();

        assertEquals(segment.get_p1(), copy.get_p1());
        assertEquals(segment.get_p2(), copy.get_p2());
    }
}