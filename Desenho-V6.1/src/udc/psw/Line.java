package udc.psw;

import java.awt.Graphics;

public class Line implements GeometricForm {	

	public int xi, yi;
	public int xf, yf;
	
	public Line() {
		xi =yi = 0;
		xf = yf = 0;
	}
	
	public Line(int xi, int yi, int xf, int yf) {
		this.xi = xi;
		this.xf = xf;
		this.yi = yi;
		this.yf = yf;
	}

	@Override
	public void draw(Graphics g) {
		//g.drawLine(lines[i].xi, lines[i].yi, lines[i].xf, lines[i].yf);
		g.drawLine(xi, yi, xf, yf);
		
	}
	
	public String toString() {
		//return "Line [xi=" + xi + ", yi=" + yi + ", xf=" + xf + ", yf=" + yf + "]";
		return "Line "+ xi +" "+ yi +" "+ xf +" "+ yf +" ";

	}

	@Override
	public int formId() {
		// TODO Auto-generated method stub
		return 2;
	}
}
