package udc.psw;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class AppLines extends JFrame {

	private DrawPanel linePanel;
	private JLabel message;
	private JMenuBar menuBar;
	private JMenuItem mntmPonto;
	private JMenuItem mntmLinha;
	private JMenuItem mntmRetangulo;
	private JMenuItem mntmCirculo;
	private JMenuItem mntmTriangulo;
	private List<GeometricForm> geometries;
	private JMenu mnDesenho;
	private JMenu mnArquivo;
	private JMenu mnNewAbrir;
	private JMenu mnNewSalvar;
	private JMenuItem mntmBinary;
	private JMenuItem mntmTexto;
	private JMenuItem mntmBinary_1;
	private JMenuItem mntmTexto_1;

	//private GeometricForm geometries[] = new GeometricForm[100];
		//private int numGeometries = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppLines frame = new AppLines();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**Metodos */
	public int getNumGeometries() {
		return geometries.size();
	}
	
	public void addGeometry(GeometricForm form) {
		if(form == null)
			return;
		geometries.add(form);
	}
	
	public GeometricForm getGeometry(int i) {
		return geometries.get(i);
	}

	/**
	 * Create the frame.
	 */
	public AppLines() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		
		geometries = new LinkedList<GeometricForm>();
		
		JPanel content = new JPanel();
		content.setBorder(new EmptyBorder(5, 5, 5, 5));
		content.setLayout(new BorderLayout());
		setContentPane(content);
				
		message = new JLabel("Messges");
		content.add(message, BorderLayout.SOUTH);
		
		linePanel = new DrawPanel(message, this);
		content.add(linePanel, BorderLayout.CENTER);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnDesenho = new JMenu("Desenho");
		menuBar.add(mnDesenho);
				
		mntmPonto = new JMenuItem("Ponto");
		mntmPonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				linePanel.setControl(1);
			}
		});mnDesenho.add(mntmPonto);

		
		mntmLinha = new JMenuItem("Linha");
		mntmLinha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				linePanel.setControl(2);
			}
		});	mnDesenho.add(mntmLinha);
		

		mntmRetangulo = new JMenuItem("Retangulo");
		mntmRetangulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				linePanel.setControl(3);
			}
		}); mnDesenho.add(mntmRetangulo);
		
		
		mntmCirculo = new JMenuItem("Circulo");
		mntmCirculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				linePanel.setControl(4);
			}
		});mnDesenho.add(mntmCirculo);
		
		mntmTriangulo = new JMenuItem("Triangulo");
		mntmTriangulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				linePanel.setControl(5);
			}
		}); mnDesenho.add(mntmTriangulo);
		
		
		mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
 
		 mnNewAbrir = new JMenu("Abrir");
		 mnArquivo.add(mnNewAbrir);
		 
		 mntmBinary = new JMenuItem("Binary");
		 mntmBinary.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent arg0) {
					File f = chooseFile(1);
					if(f == null)
						return;
					
					readBinForms(f);//ler arquivo
				}
		 }); mnNewAbrir.add(mntmBinary);
		 
		 mntmTexto = new JMenuItem("Texto");
		 mntmTexto.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent arg0) {
					File f = chooseFile(1);
					if(f == null)
						return;
					
					readForms(f);//ler arquivo
				}
		 }); mnNewAbrir.add(mntmTexto);
		 
		 mnNewSalvar = new JMenu("Salvar");
		 mnArquivo.add(mnNewSalvar);
		 
		 mntmBinary_1 = new JMenuItem("Binary");
		 mntmBinary_1.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent arg0) {
					File f = chooseFile(2);
					
					if(f == null)
					return;
					
					saveBinForms(f);//verifica se salvou
				}
		 }); mnNewSalvar.add(mntmBinary_1);
		 
		 mntmTexto_1 = new JMenuItem("Texto");
		 mntmTexto_1.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent arg0) {
					File f = chooseFile(2);
					
					if(f == null)
					return;
					
					saveForms(f);//verifica se salvou
				}
		 }); mnNewSalvar.add(mntmTexto_1);
		
	}
	protected void readForms(File f) { //ler arquivo
		// TODO Auto-generated method stub
		Scanner input;
		try {
			 input = new Scanner(f);
			 geometries.clear();
			 
			 while(input.hasNext()) {
				 String form = input.next();
				 
				 if(form.equals("Point")){
					 int x = input.nextInt();
					 int y = input.nextInt();
					 Point p = new Point(x, y);
					 geometries.add(p);
				 } else if(form.equals("Line")) {
					 int xi = input.nextInt();
					 int yi = input.nextInt();
					 int xf = input.nextInt();
					 int yf = input.nextInt();
					 Line l = new Line(xi, yi, xf, yf);
					 geometries.add(l);
				 } else if(form.equals("Retangle")){
					int x = input.nextInt();
					int y = input.nextInt();
					int width = input.nextInt();
					int height = input.nextInt();
					Rectangle r = new Rectangle(x, y, width, height);
					geometries.add(r);
				 }else if(form.equals("Circle")){
					int x = input.nextInt();
					int y = input.nextInt();
					int raio = input.nextInt();
					Circle c = new Circle(x, y, raio);
					geometries.add(c);
				 }else if(form.equals("Triangle")){
					int x = input.nextInt();
					int y = input.nextInt();
					int base = input.nextInt();
					int altura = input.nextInt();
					Triangle t = new Triangle(x, y, base, altura);
					geometries.add(t);
				 }
			 } input.close();
			 
		 } catch(IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		 	}
	}
	
	protected void saveForms(File f)  { //salva o arquivo
		FileWriter output;
	
		try {
			output = new FileWriter(f);
			
			 //for diferentão
			for(GeometricForm g : geometries) { //pega cada 1 dos elementos 1 x 1 e coloca na variavel g
				output.append(g.toString() + "\n"); //para o meu arquivo vou agresentar no final do aquivo G
			}
			output.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	//ler binario
		protected void readBinForms(File f) {
			FileInputStream in = null;
			
			try { 
				in = new FileInputStream(f);
				
				int val;
				byte array[]= new byte[4];
				
				while(in.read(array) != 1) {
					val = ByteBuffer.wrap(array).getInt();
					if (val == 1) { //ponto
						in.read(array);
						int x = ByteBuffer.wrap(array).getInt();
						in.read(array);
						int y = ByteBuffer.wrap(array).getInt();
						
						Point p = new Point(x,y);
						
					} else if (val == 2) { //linha
						in.read(array);
						int xi = ByteBuffer.wrap(array).getInt();
						in.read(array);
						int yi = ByteBuffer.wrap(array).getInt();
						in.read(array);
						int xf = ByteBuffer.wrap(array).getInt();
						in.read(array);
						int yf = ByteBuffer.wrap(array).getInt();
						
						Line l = new Line(xi, yi, xf, yf);
						
					} else if(val == 3) { //retangulo
						in.read(array);
						int x = ByteBuffer.wrap(array).getInt();
						in.read(array);
						int y = ByteBuffer.wrap(array).getInt();
						in.read(array);
						int width = ByteBuffer.wrap(array).getInt();
						in.read(array);
						int height = ByteBuffer.wrap(array).getInt();
						
						Rectangle r = new Rectangle(x, y, width, height);
						
					}else if(val == 4) {//circulo
						in.read(array);
						int x = ByteBuffer.wrap(array).getInt();
						in.read(array);
						int y = ByteBuffer.wrap(array).getInt();
						in.read(array);
						int raio = ByteBuffer.wrap(array).getInt();
						
						Circle c = new Circle(x, y, raio);
						
					}else if(val == 5) {
						in.read(array);
						int x = ByteBuffer.wrap(array).getInt();
						in.read(array);
						int y = ByteBuffer.wrap(array).getInt();
						in.read(array);
						int base = ByteBuffer.wrap(array).getInt();
						in.read(array);
						int altura = ByteBuffer.wrap(array).getInt();
						
						Triangle t = new Triangle(x, y, base, altura);
						
					}
				}
			} 	catch (FileNotFoundException e) {
				e.printStackTrace();
				
			}	catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		//salva binario
		protected void saveBinForms(File f) {
			FileOutputStream out = null;
			
			try {
				out = new FileOutputStream(f);  
				
				for(GeometricForm g : geometries) {
					out.write(g.formId());
					if(g.formId() == 1) {//ponto
						out.write(((Point)g).x);
						out.write(((Point)g).y);
					}else if(g.formId() == 2) {//linha
						out.write(((Line)g).xi);
						out.write(((Line)g).yi);
						out.write(((Line)g).xf);
						out.write(((Line)g).xf);
					} else if(g.formId() == 3) {//retangulo
						out.write(((Rectangle)g).x);
						out.write(((Rectangle)g).y);
						out.write(((Rectangle)g).width);
						out.write(((Rectangle)g).height);
					}else if(g.formId() == 4) {//circulo
						out.write(((Circle)g).x);
						out.write(((Circle)g).y);
						out.write(((Circle)g).raio);
					}else if(g.formId() == 5) {//triangulo
						out.write(((Triangle)g).x);
						out.write(((Triangle)g).y);
						out.write(((Triangle)g).base);
						out.write(((Triangle)g).altura);
					}
				}
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		protected File chooseFile(int escolha) {
			JFileChooser fc = new JFileChooser();
			// metodo do java para arquivos 
			
			fc.setCurrentDirectory(new File(System.getProperty("user.home")));
			
			FileNameExtensionFilter textFilterT = new FileNameExtensionFilter("Text file", "txt");
			fc.addChoosableFileFilter(textFilterT);
			fc.setFileFilter(textFilterT);
			FileNameExtensionFilter textFilterB = new FileNameExtensionFilter("Binary file", "bin");
			fc.addChoosableFileFilter(textFilterB);
			fc.setFileFilter(textFilterB);
			
			switch(escolha) {
			case 1:
				int open = fc.showOpenDialog(null);
				if (open == JFileChooser.APPROVE_OPTION) {
					return fc.getSelectedFile();
				}
				break;
			case 2:
				int save = fc.showSaveDialog(null);
				if(save == JFileChooser.APPROVE_OPTION) {
					return fc.getSelectedFile();
				}
				break;
			}
			//verifica se escolheu o arquivo
		
			return null;
		}

}
