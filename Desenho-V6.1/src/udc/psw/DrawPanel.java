package udc.psw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawPanel extends JPanel implements MouseMotionListener, 
MouseListener{
	
	private JLabel status;
	
	private int control = 0;
	
	//private GeometricForm geometries[] = new GeometricForm[100];
	//private int numGeometries = 0;
	private AppLines app;
	private Point p;
	private Line l;
	private Rectangle r;
	private Circle c;
	private Triangle t;
	
	/**
	 * Create the panel.
	 */
	public DrawPanel(JLabel status, AppLines app) {
		this.status = status;
		this.app = app;
		
		addMouseMotionListener(this);
		addMouseListener(this);

		setBackground(Color.WHITE);
	}
	
	public void setControl(int control) {
		if(control == 1) {
			this.control = 1;
			p = new Point();
			
		} else if(control == 2) {
			this.control = 2;
			l = new Line();
			
		} else if(control == 3) {
			this.control = 3;
			r = new Rectangle();
			
		}else if(control == 4) {
			this.control = 4;
			c = new Circle();
	
		}else if(control == 5) {
			this.control = 5;
			t = new Triangle();
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		switch(control) {
		case 1: // ponto
			//g.fillOval(p.x-2, p.y-2, 5, 5);
			p.draw(g);
			break;
		case 2: // linha
			//g.drawLine(l.xi, l.yi, l.xf, l.yf);
			l.draw(g);
			break;
		case 3: //retangulo
			//g.drawRect(r.x, r.y, r.width, r.height);
			r.draw(g);
			break;
		case 4: //circulo
			//g.drawOval(c.x, c.y, c.raio);
			c.draw(g);
			break;
		case 5://triangulo
			//g.drawLine(t.x, t.y, t.base, t.altura);
			t.draw(g);
			break;
		}
		for(int i = 0; i < app.getNumGeometries(); i++) {
			app.getGeometry(i).draw(g);
		}
		
		/*for(int i = 0; i < numPoints; i++) {
			g.fillOval(points[i].x-2, points[i].y-2, 5, 5);
		}
		for(int i = 0; i < numLines; i++) {
			g.drawLine(lines[i].xi, lines[i].yi, lines[i].xf, lines[i].yf);
		}*/
	}

/*Todos os métodos contêm um argumento do tipo MouseEvent que contêm as dados, 
 * como informações de coordenadas do mouse. O getX() e getY() são usado para obter as coordenadas x e y do mouse*/
	
	public void mouseClicked(MouseEvent e) {//é chamado uma vez que há um movimento do mouse dentro do quadro.
		switch(control) {
		case 1: // ponto
			p.x = e.getX();
			p.y = e.getY();
			
				app.addGeometry(p);
				p = new Point();
			
			break;
		case 2: // linha
			break;
		case 3: //retangulo
			break;
		case 4: //circulo
			break;
		case 5: //triangulo
			
			break;
		}
		repaint();
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {//é chamado quando o mouse vem de uma janela exterior ao quadro.
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {//é chamado quando o mouse passa fora do quadro i.e. JFrame
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {//é chamado quando o mouse é pressionado.
		switch(control) {
		case 1: // ponto
			break;
		case 2: // linha
			l.xi = l.xf = e.getX();
			l.yi = l.yf = e.getY();
			break;
		case 3: //restangulo
			r.x = r.width = e.getX();
			r.y = r.height = e.getY();
			break;
		case 4: //circulo
			c.x = c.raio = e.getX();
			c.y = c.raio = e.getY();
			break;
		case 5: //triangulo
			t.x = t.base = e.getX();
			t.y = t.altura = e.getX();
			break;
		}
		repaint();
	}


	@Override
	public void mouseDragged(MouseEvent e) { //é chamado quando o mouse é arrastado
		switch(control) {
		case 1: // ponto
			break;
		case 2: // linha
			l.xf = e.getX();
			l.yf = e.getY();
			break;
		case 3: //retangulo
			r.width = e.getX();
			r.height = e.getY();
			break;
		case 4: //circulo
			c.raio = e.getX();
			c.raio = e.getY();
			break;
		case 5: //triangulo
			t.base = e.getX();
			t.altura = e.getY();
			break;
		}
		repaint();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {//é chamado quando o botão do mouse é liberado.
		switch(control) {
		case 1: // ponto
			break;
		case 2: // linha
			l.xf = e.getX();
			l.yf = e.getY();
			
				app.addGeometry(l);
				l = new Line();
			
			break;
		case 3: //retangulo
			r.width = e.getX();
			r.height = e.getY();
			
				app.addGeometry(r);
				r = new Rectangle();
			break;
			
		case 4: //circulo
			c.raio = e.getX();
			c.raio = e.getY();
			
				app.addGeometry(c);
				c = new Circle();
			break;
		case 5: //triangulo
			t.base = e.getX();
		 	t.altura = e.getY();
		 	
		 		app.addGeometry(t);
		 		t = new Triangle();
			break;
		}
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) { //é chamado quando o mouse é mouse de localização.
		status.setText(
				String.format("Movimento na posição [%d, %d]",
						e.getX(), e.getY())
				);
	}

}
