package exe.ex4.geo;

/**
	 * This class represents a 2D axis parallel rectangle.
	 * Ex4: you should implement this class!
	 * @author I2CS
	 *
	 */
	public class Rect_2D implements GeoShape {
		private Point_2D _p1;
		private Point_2D _p2;
		private Point_2D _p3;
		private Point_2D _p4;

		public Rect_2D(Point_2D p1, Point_2D p2) {
			_p1 = p1;
			_p2 = p2;
			_p3 = new Point_2D(p1.x(), p2.y());
			_p4 = new Point_2D(p2.x(), p1.y());
		}
		public Rect_2D(Rect_2D t1) {
			_p1=new Point_2D(t1.get_p1());
			_p2=new Point_2D(t1.get_p2());
			_p3=new Point_2D(t1.get_p1().y(),t1.get_p2().x());
			_p4=new Point_2D(t1.get_p1().x(),t1.get_p2().y());
		}
		public Point_2D get_p1() {
			return this._p1;

		}
		public Point_2D get_p2() {
			return this._p2;

		}

		@Override
		public boolean contains(Point_2D ot) {

			double totalArea = this.area();
			Triangle_2D a = new Triangle_2D(ot, _p2, _p3);
			Triangle_2D b = new Triangle_2D(_p1, ot, _p3);
			Triangle_2D c = new Triangle_2D(_p1, _p4, ot);
			Triangle_2D d = new Triangle_2D(_p4, _p2, ot);

			// Check if the sum of the areas is equal to the total area
			if(totalArea==(a.area()+b.area()+c.area()+d.area()))return true;
			return false;
		}

		@Override
		public double area() {
			return Math.abs(_p1.x()-_p2.x())*Math.abs(_p1.y()-_p2.y());
		}
		@Override
		public String toString(){
			String s = this.getClass().getSimpleName()+",";
			s = s+_p1.toString()+", "+_p2.toString()+", "+_p3.toString()+", "+_p4.toString();
			return s;
		}
		public Point_2D[] getAllPoints() {
			Point_2D[] points = new Point_2D[4];
			points[0] = _p1;
			points[1] = _p2;
			points[2] = _p3;
			points[3] = _p4;

			return points;
		}

		@Override
		public double perimeter() {
			return 2*(Math.abs(_p1.x()-_p2.x())+Math.abs(_p1.y()-_p2.y()));
		}

		@Override
		public void translate(Point_2D vec) {
			_p1.move(vec);
			_p2.move(vec);
			_p3.move(vec);
			_p4.move(vec);
		}

		@Override
		public GeoShape copy() {
			GeoShape a1= new Rect_2D(_p1,_p2);
			return a1;
		}

		@Override
		public void scale(Point_2D center, double ratio) {
			_p1.scale(center,ratio);
			_p2.scale(center,ratio);
			_p3.scale(center,ratio);
			_p4.scale(center,ratio);
		}

		@Override
		public void rotate(Point_2D center, double angleDegrees) {
			_p1.rotate(center,angleDegrees);
			_p2.rotate(center,angleDegrees);
			_p3.rotate(center,angleDegrees);
			_p4.rotate(center,angleDegrees);
		}
	}
