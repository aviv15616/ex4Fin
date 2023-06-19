package exe.ex4.geo;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Polygon_2D implements GeoShape {
	private ArrayList<Point_2D> _polygon;
	public Polygon_2D() {
		_polygon=new ArrayList<>();
	}
	public Polygon_2D(Polygon_2D po) {
		_polygon=new ArrayList<>();
		for (int i = 0; i < po._polygon.size(); i++) {
			this.add(getAllPoints()[i]);


		}

	}
	public Point_2D[] getAllPoints() {
		Point_2D[] a=new Point_2D[this._polygon.size()];
		for (int i = 0; i < this._polygon.size(); i++) {
			a[i]=_polygon.get(i);


		}
		return a;
	}

	public void add(Point_2D p) {
		_polygon.add(p);
	}
	public boolean touch(Point_2D a, Point_2D b, Point_2D c, Point_2D d)
	{
		Line2D Line1= new Line2D.Double(a.x(),a.y(),b.x(),b.y());
		Line2D Line2= new Line2D.Double(c.x(),c.y(),d.x(),d.y());

		return Line1.intersectsLine(Line2);
	}
	@Override
	public String toString(){
		String s = this.getClass().getSimpleName()+",";
		s= s+_polygon.get(0).toString();
		for (int i=1; i<_polygon.size(); i++) {
			s = s +","+ _polygon.get(i).toString();
		}
		return s;
	}
	public double MostRight()
	{

		Point_2D[] AllPoints =getAllPoints();
		double MaxX=AllPoints[0].x();
		for (int i = 0; i <getAllPoints().length; i++) {
			if(AllPoints[i].x()>MaxX)
			MaxX= AllPoints[i].x();
		}
		return MaxX;
	}
	@Override
	public boolean contains(Point_2D ot) {
		Point_2D a= new Point_2D(MostRight(),ot.y());
		Point_2D b= new Point_2D(ot.x(),ot.y());
		int counter=0;
		for (int i = 0; i < _polygon.size()-1; i++) {
			if(touch(a,b,_polygon.get(i),_polygon.get(i+1)))
				counter++;


		}
		if(counter%2==0)
			return false;
		return true;

	}

	@Override
	public double area() { // using shoelace formula, also known as Gauss's area formula.
		int n = _polygon.size();
		double area = 0.0;

		for (int i = 0; i < n; i++) {
			Point_2D currentVertex = _polygon.get(i);
			Point_2D nextVertex = _polygon.get((i + 1) % n);

			double x1 = currentVertex.x();
			double y1 = currentVertex.y();
			double x2 = nextVertex.x();
			double y2 = nextVertex.y();

			area += (x1 * y2 - x2 * y1);
		}

		return Math.abs(area) / 2.0;
	}
	@Override
	public double perimeter() {
		double sum=0;
		for (int i = 0; i < _polygon.size()-1; i++) {
			sum+=_polygon.get(i).distance(_polygon.get(i+1));

		}
		sum+=_polygon.get(0).distance(_polygon.get(_polygon.size()-1));
		return sum;
	}
	@Override
	public void translate(Point_2D vec) {
		for (int i = 0; i < _polygon.size(); i++) {
			_polygon.get(i).move(vec);

		}
	}
	@Override
	public GeoShape copy() {
		Polygon_2D a1= new Polygon_2D();
		for (int i = 0; i < _polygon.size(); i++) {
			a1.add(_polygon.get(i));


		}
		return a1;
	}

	@Override
	public void scale(Point_2D center, double ratio) {

		for (int i = 0; i < _polygon.size(); i++) {
			_polygon.get(i).scale(center, ratio);
		}
	}
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		for (int i = 0; i < _polygon.size(); i++) {
			_polygon.get(i).rotate(center, angleDegrees);
		}
	}

}
