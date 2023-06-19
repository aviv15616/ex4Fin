package exe.ex4.geo;

/**
 * This class represents a 2D circle in the plane.
 * Please make sure you update it according to the GeoShape interface.
 * Ex4: you should update this class!
 * @author boaz.benmoshe
 */
public class Circle_2D implements GeoShape {
	private Point_2D _center;
	private double _radius;

	/**
	 * Constructs a Circle_2D object with the given center point and radius.
	 * @param cen the center point of the circle
	 * @param rad the radius of the circle
	 */
	public Circle_2D(Point_2D cen, double rad) {
		this._center = new Point_2D(cen);
		this._radius = rad;
	}

	/**
	 * Returns the radius of the circle.
	 * @return the radius of the circle
	 */
	public double getRadius() {
		return this._radius;
	}

	/**
	 * Returns the center point of the circle.
	 * @return the center point of the circle
	 */
	public Point_2D getCenter() {
		return _center;
	}

	/**
	 * Returns a string representation of the circle in the format "centerX,centerY,radius".
	 * @return a string representation of the circle
	 */
	@Override
	public String toString() {
		String s = this.getClass().getSimpleName()+",";
		s = s+_center.toString()+", "+_radius;
		return s;
	}

	/**
	 * Calculates the distance between the center of the circle and a given point.
	 * @param p1 the point to calculate the distance from
	 * @return the distance between the center and the given point
	 */
	public double DistanceFromCenter(Point_2D p1) {
		double distance = Math.sqrt(Math.pow(p1.x() - this._center.x(), 2) + Math.pow(p1.y() - this._center.y(), 2));
		return distance;
	}

	/**
	 * Checks if the circle contains the given point.
	 * @param ot the point to check
	 * @return true if the circle contains the point, false otherwise
	 */
	@Override
	public boolean contains(Point_2D ot) {
		if (DistanceFromCenter(ot) <= this._radius)
			return true;
		return false;
	}
	public Point_2D[] getAllPoints() {
		Point_2D[] points = new Point_2D[1];
		points[0] = _center;


		return points;
	}

	/**
	 * Calculates and returns the area of the circle.
	 * @return the area of the circle
	 */
	@Override
	public double area() {
		double ans = Math.PI * _radius* _radius;
		return ans;
	}

	/**
	 * Calculates and returns the perimeter of the circle.
	 * @return the perimeter of the circle
	 */
	@Override
	public double perimeter() {
		double ans = 2 * (Math.PI) * (this._radius);
		return ans;
	}

	/**
	 * Translates the circle by the given vector.
	 * @param vec the vector to translate the circle
	 */
	@Override
	public void translate(Point_2D vec) {

		this._center = new Point_2D(this._center.x()+vec.x(), this._center.y()+vec.y());
	}

	/**
	 * Creates and returns a copy of the circle.
	 * @return a copy of the circle
	 */
	@Override
	public GeoShape copy() {
		GeoShape copy = new Circle_2D(this._center, this._radius);
		return copy;
	}

	/**
	 * Scales the circle with respect to the specified center point by the given ratio.
	 * @param center the center point for scaling
	 * @param ratio the scaling ratio
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		this._radius = ratio * this._radius;
		this._center.scale(center,ratio);
	}

	/**
	 * Rotates the circle around the specified center point by the given angle in degrees.
	 * @param center the center point for rotation
	 * @param angleDegrees the angle of rotation in degrees
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		_center.rotate(center, angleDegrees);
	}
}
