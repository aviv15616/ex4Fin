package exe.ex4.geo;

public class Main {
    public static void main(String[] args) {
        // Create a new Polygon_2D object using the default constructor
        Polygon_2D polygon1 = new Polygon_2D();

        // Create a new Polygon_2D object using the copy constructor
        Polygon_2D polygon2 = new Polygon_2D(polygon1);

        // Add points to the polygon using the addPoint method
        Point_2D point1 = new Point_2D(0, 0);
        Point_2D point2 = new Point_2D(1, 1);
        Point_2D point3 = new Point_2D(2, 2);

        polygon1.add(point1);
        polygon1.add(point2);
        polygon1.add(point3);

        polygon1.toString();
    }
}
