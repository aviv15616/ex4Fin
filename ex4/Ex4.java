package exe.ex4.ex4;

import exe.ex4.geo.*;
import exe.ex4.gui.Ex4_GUI;
import exe.ex4.gui.GUIShape;
import exe.ex4.gui.GUI_Shape;
import exe.ex4.gui.StdDraw_Ex4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * 
 * This class is a simple "inter-layer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a 
 * "Singleton-like" implementation.
 * @author boaz.benmoshe
 *
 */
public class Ex4 implements Ex4_GUI {
	private  GUI_Shape_Collection _shapes = new ShapeCollection();
	private GUI_Shape _gs;
	private  Color _color = Color.blue;
	private  boolean _fill = false;
	private  String _mode = "";
	private Point_2D _p1;
	
	private  static Ex4 _winEx4 = null;
	
	private Ex4() {
			init(null);
	}
	public void init(ShapeCollection s) {
		if(s==null) {_shapes = new ShapeCollection();}
		else {_shapes = s.copy();}
		GUI_Shape _gs = null;
		Polygon_2D _pp = null;
		_color = Color.blue;
		_fill = false;
		_mode = "";
		Point_2D _p1 = null;
	}
	public void show(double d) {
		StdDraw_Ex4.setScale(0,d);
		StdDraw_Ex4.show();
		drawShapes();
	}
	public static Ex4 getInstance() {
		if(_winEx4 ==null) {
			_winEx4 = new Ex4();
		}
		return _winEx4;
	}

	public void drawShapes() {
		StdDraw_Ex4.clear();
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape sh = _shapes.get(i);

			drawShape(sh);
		}
		if(_gs!=null) {drawShape(_gs);}
		StdDraw_Ex4.show();
	}
	private static void drawShape(GUI_Shape g) {
		StdDraw_Ex4.setPenColor(g.getColor());
		if(g.isSelected()) {StdDraw_Ex4.setPenColor(Color.gray);}
		GeoShape gs = g.getShape();
		boolean isFill = g.isFilled();


		if(gs instanceof Circle_2D) {
			Circle_2D c = (Circle_2D)gs;
			Point_2D cen = c.getAllPoints()[0];
			double rad = c.getRadius();
			if(isFill) {
				StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
			}
			else {
				StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
			}
		}

		if(gs instanceof Rect_2D) {
			Rect_2D r = (Rect_2D)gs;
			double [] x = new double [4];
			x[0] = r.getAllPoints()[0].x();
			x[1] = r.getAllPoints()[1].x();
			x[2] = r.getAllPoints()[2].x();
			x[3] = r.getAllPoints()[3].x();
			double [] y = new double [4];
			y[0] = r.getAllPoints()[0].y();
			y[1] = r.getAllPoints()[1].y();
			y[2] = r.getAllPoints()[2].y();
			y[3] = r.getAllPoints()[3].y();
			if(isFill) {
				StdDraw_Ex4.filledPolygon(x, y);
			}
			else {
				StdDraw_Ex4.polygon(x, y);
			}
		}


		if(gs instanceof Segment_2D) {
			Segment_2D s = (Segment_2D)gs;
			Point_2D p1 = s.getAllPoints()[0];
			Point_2D p2 = s.getAllPoints()[1];
			if(isFill) {
				StdDraw_Ex4.line(p1.x(),p1.y(),p2.x(),p2.y());
			}
			else {
				StdDraw_Ex4.line(p1.x(),p1.y(),p2.x(),p2.y());
			}
		}

		if(gs instanceof Triangle_2D) {
			Triangle_2D t = (Triangle_2D)gs;
			double [] x = new double [3];
			x[0] = t.getAllPoints()[0].x();
			x[1] = t.getAllPoints()[1].x();
			x[2] = t.getAllPoints()[2].x();
			double [] y = new double [3];
			y[0] = t.getAllPoints()[0].y();
			y[1] = t.getAllPoints()[1].y();
			y[2] = t.getAllPoints()[2].y();
			if(isFill) {
				StdDraw_Ex4.filledPolygon(x, y);
			}
			else {
				StdDraw_Ex4.polygon(x, y);
			}
		}

		if(gs instanceof Polygon_2D) {
			Polygon_2D p = (Polygon_2D)gs;
			double[] x = new double[p.getAllPoints().length];
			double[] y = new double[p.getAllPoints().length];
			for(int i=0; i<p.getAllPoints().length; i++) {
				x[i] = p.getAllPoints()[i].x();
				y[i] = p.getAllPoints()[i].y();
			}
			if(isFill) {
				StdDraw_Ex4.filledPolygon(x, y);
			}
			else {
				StdDraw_Ex4.polygon(x, y);
			}
		}

	}
	private void setColor(Color c) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			if(s.isSelected()) {
				s.setColor(c);
			}
		}
	}
	private void setFill() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			if(s.isSelected()) {
				s.setFilled(_fill);
			}
		}
	}

	public void actionPerformed(String p) {
		_mode = p;
		if(p.equals("Blue")) {_color = Color.BLUE; setColor(_color);}
		if(p.equals("Red")) {_color = Color.RED; setColor(_color);}
		if(p.equals("Green")) {_color = Color.GREEN; setColor(_color);}
		if(p.equals("White")) {_color = Color.WHITE; setColor(_color);}
		if(p.equals("Black")) {_color = Color.BLACK; setColor(_color);}
		if(p.equals("Yellow")) {_color = Color.YELLOW; setColor(_color);}
		if(p.equals("Fill")) {_fill = true; setFill();}
		if(p.equals("Empty")) {_fill = false; setFill();}
		if(p.equals("Clear")) {
			_shapes.removeAll();
		}

		if(_mode.equals("Save")) {
			JFrame frame = new JFrame();
			FileDialog chooser = new FileDialog(frame,"Save a File", FileDialog.SAVE);
			chooser.setDirectory("c:\\");
			chooser.setVisible(true);
			String filename = chooser.getDirectory()+chooser.getFile();
			if (filename != null) {
				_shapes.save(filename);
			}
		}

		if(_mode.equals("Load")) {
			JFrame frame = new JFrame();
			FileDialog chooser = new FileDialog(frame,"Choose a File", FileDialog.LOAD);
			chooser.setDirectory("c:\\");
			chooser.setVisible(true);
			String filename = chooser.getDirectory()+chooser.getFile();
			int size = _shapes.size();
			if(filename==null||filename.equals("null")) {
				System.out.println("cancel");
			}
			else  {
				_shapes.load(filename);
				if(_shapes.size()!=size) {
					for(int i =0;i<size;i++){
						_shapes.removeElementAt(0);
					}
				}
				drawShapes();
			}
		}

		if(_mode.equals("Info")) {
			String s = getInfo();
			System.out.println(s);

		}
		if(_mode.equals("All")) {
			ALl_None(true);
		}
		if(_mode.equals("None")) {
			ALl_None(false);
		}
		if(_mode.equals("Anti")) {
			anti();
		}
		if(_mode.equals("Remove")) {
			remove();
		}
		if(_mode.equals("ByArea")) {
			_shapes.sort(ShapeComp.CompByArea);
		}
		if(_mode.equals("ByAntiArea")) {
			_shapes.sort(ShapeComp.CompByAntiArea);
		}
		if(_mode.equals("ByPerimeter")) {
			_shapes.sort(ShapeComp.CompByPerimeter);
		}
		if(_mode.equals("ByAntiPerimeter")) {
			_shapes.sort(ShapeComp.CompByAntiPerimeter);
		}
		if(_mode.equals("ByToString")) {
			_shapes.sort(ShapeComp.CompByToString);
		}
		if(_mode.equals("ByAntiToString")) {
			_shapes.sort(ShapeComp.CompByAntiToString);
		}
		if(_mode.equals("ByTag")) {
			_shapes.sort(ShapeComp.CompByTag);
		}
		if(_mode.equals("ByAntiTag")) {
			_shapes.sort(ShapeComp.CompByAntiTag);
		}

		drawShapes();
	}


	
	public void mouseClicked(Point_2D p) {
		System.out.println("Mode: "+_mode+"  "+p);
		if(_mode.equals("Circle")) {
			if(_gs==null) {
				_p1 = new Point_2D(p);
			}
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}
			if(_mode.equals("Move")) {
				if(_p1==null) {_p1 = new Point_2D(p);}
				else {
					_p1 = new Point_2D(p.x()-_p1.x(), p.y()-_p1.y());
					move();
					_p1 = null;
				}
			}
	
		if(_mode.equals("Point")) {
			select(p);
		}
	
		drawShapes();
	}
	
	private void select(Point_2D p) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if(g!=null && g.contains(p)) {
				s.setSelected(!s.isSelected());
			}
		}
	}
	private void move() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if(s.isSelected() && g!=null) {
				g.translate(_p1);
			}
		}
	}
	
	public void mouseRightClicked(Point_2D p) {
		System.out.println("right click!");
	
	}
	public void mouseMoved(MouseEvent e) {
		if(_p1!=null) {
			double x1 = StdDraw_Ex4.mouseX();
			double y1 = StdDraw_Ex4.mouseY();
			GeoShape gs = null;
		//	System.out.println("M: "+x1+","+y1);
			Point_2D p = new Point_2D(x1,y1);
			if(_mode.equals("Circle")) {
				double r = _p1.distance(p);
				gs = new Circle_2D(_p1,r);
			}
			_gs = new GUIShape(gs,false, Color.pink, 0);
			drawShapes();
		}
	}
	@Override
	public GUI_Shape_Collection getShape_Collection() {
		// TODO Auto-generated method stub
		return this._shapes;
	}
	@Override
	public void show() {show(Ex4_Const.DIM_SIZE); }
	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		String ans = "";
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			ans +=s.toString()+"\n";
		}
		return ans;
	}
}
