package udc.psw;

import java.awt.Graphics;

public class Point implements GeometricForm {

	public int x;
	public int y;
	
	public Point() {
		x = 0;
		y = 0;
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void draw(Graphics g) {
		//g.fillOval(points[i].x-2, points[i].y-2, 5, 5);
		g.fillOval(x-2, y-2, 6, 6);
	}
	public String toString() {
		//return "Point [x=" + x + ", y=" + y + "]";
		return "Point "+ x +" "+ y +" ";

	}

	@Override
	public int formId() {
		// TODO Auto-generated method stub
		return 1;
	}
	
}
