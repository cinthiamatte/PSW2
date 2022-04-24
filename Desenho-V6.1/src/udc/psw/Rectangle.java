package udc.psw;
import java.awt.Graphics;

public class Rectangle implements GeometricForm{
	public int x, y;
	public int width, height;
	
	public Rectangle() {
		x = 0;
		y = 0;
		width = 0;
		height = 0;
	}
	
	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(x<width?x:width, y<height?y:height, Math.abs(x-width), Math.abs(y-height));
		
	}
	
	public String toString() {
		return "Rectangle "+ x +" "+ y +" "+ width +" "+ height +" ";
	}

	@Override
	public int formId() {
		// TODO Auto-generated method stub
		return 3;
	}

}