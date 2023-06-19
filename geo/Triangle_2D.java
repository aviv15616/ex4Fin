package exe.ex4.geo;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle_2D implements GeoShape {
	private Point_2D _p1;
	private Point_2D _p2;
	private Point_2D _p3;
	public Triangle_2D(Point_2D p1, Point_2D p2, Point_2D p3) {
		this._p1 = new Point_2D(p1);
		this._p2 = new Point_2D(p2);
		this._p3 = new Point_2D(p3);
	}
	public Point_2D get_p1() {
		return this._p1;

	}
	public Point_2D get_p2() {
		return this._p2;

	}
	public Point_2D get_p3() {
		return this._p3;

	}
	public Triangle_2D(Triangle_2D t1) {
		_p1=t1.get_p1();
		_p2=t1.get_p2();
		_p3=t1.get_p3();
	}

	public Point_2D[] getAllPoints() {
		Point_2D[] points = new Point_2D[3];
		points[0] = _p1;
		points[1] = _p2;
		points[2] = _p3;
		return points;
	}

	@Override
	public boolean contains(Point_2D ot) {

		double totalArea = area();
		double area1 = new Triangle_2D(ot, _p2, _p3).area();
		double area2 = new Triangle_2D(_p1, ot, _p3).area();
		double area3 = new Triangle_2D(_p1, _p2, ot).area();

		// Check if the sum of the areas is equal to the total area
		if(totalArea==(area1+area2+area3))return true;
		return false;
	}

	@Override
	public double area() {
		double area=0.5 * Math.abs((_p1.x() * (_p2.y() - _p3.y()) + _p2.x() * (_p3.y() - _p1.y()) + _p3.x() * (_p1.y() - _p2.y())));

		return area;
	}

	@Override
	public double perimeter() {
		return _p1.distance(_p2)+_p1.distance(_p3)+_p2.distance(_p3);
	}
	@Override
	public String toString(){
		String s = this.getClass().getSimpleName()+",";
		s = s+ _p1.toString()+", "+_p2.toString()+", "+_p3.toString();
		return s;
	}

	@Override
	public void translate(Point_2D vec) {
		this._p1=new Point_2D(this._p1.x()+vec.x(),this._p1.y()+vec.y());
		this._p2=new Point_2D(this._p2.x()+vec.x(),this._p2.y()+vec.y());
		this._p3=new Point_2D(this._p3.x()+vec.x(),this._p3.y()+vec.y());
	}

	@Override
	public GeoShape copy() {

		return new Triangle_2D(_p1 , _p2 , _p3);
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		_p1.scale(center,ratio);
		_p2.scale(center,ratio);
		_p3.scale(center,ratio);
	}

	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		_p1.rotate(center,angleDegrees);
		_p2.rotate(center,angleDegrees);
		_p3.rotate(center,angleDegrees);

	}
}
