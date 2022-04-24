package udc.psw;

import java.awt.Graphics;

public class Triangle implements GeometricForm{
	public int x, y;
	public int base, altura;
	
	
	public Triangle() {
		x = 0;
		y = 0;
		base = 0;
		altura = 0;
		
	}
	
	public Triangle(int x, int y, int base, int altura) {
		this.x = x;
		this.y = y;
		this.base = base;
		this.altura = altura;
	}
	
	public void draw(Graphics g){
		g.drawLine(x, y, x + base, y); //esquerda da base para a direita
		g.drawLine(x + base, y, x + base/2, y - altura); // da base direita até o topo, dividido por 2 encontra p medio
		g.drawLine(x + base/2, y - altura , x, y); //desde o topo até a esquerda inferior 
	}
	
	public String toString() {
		return "Triangle "+ x +" "+ y +" "+ base +" "+ altura +" ";
	}

	@Override
	public int formId() {
		// TODO Auto-generated method stub
		return 5;
	}
}
