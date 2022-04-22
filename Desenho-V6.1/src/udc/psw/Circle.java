package udc.psw;

import java.awt.Graphics;

public class Circle implements GeometricForm {
	public int x, y;
	public int raio;
	public static final double pi = 3.14159;
	
	
	public Circle() {
		x = 0;
		y = 0;
		raio = 0;
		
	}
	
	public Circle(int x, int y, int r) {
		this.x = x;
		this.y = y;
		this.raio = r;
		
	}
	
	public double circunferencia() {
		return 2*pi*raio;
	}
	
	
	public void draw(Graphics g) {
		g.drawOval(x-raio, y-raio, raio*2, raio*2);
	}
	
	public String toString() {
		return "Circle "+ x +" "+ y +" "+ raio +" ";
	}

	@Override
	public int formId() {
		// TODO Auto-generated method stub
		return 4;
	}
}
