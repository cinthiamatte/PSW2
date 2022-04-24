package udc.psw;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
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
	private JMenuItem mntmClear;

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
					repaint();
				}
		 }); mnNewAbrir.add(mntmBinary);
		 
		 mntmTexto = new JMenuItem("Texto");
		 mntmTexto.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent arg0) {
					File f = chooseFile(1);
					if(f == null)
						return;
					
					readForms(f);//ler arquivo
					repaint();
					
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
					
					saveBinForms(f);//salvar
				}
		 }); mnNewSalvar.add(mntmBinary_1);
		 
		 mntmTexto_1 = new JMenuItem("Texto");
		 mntmTexto_1.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent arg0) {
					File f = chooseFile(2);
					
					if(f == null)
					return;
					
					saveForms(f);//salvar
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
				 } else if(form.equals("Rectangle")){
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
				 geometries.clear();
				
				int val;
				byte array [] = new byte[4];
				
				while(in.read(array) != -1) {
					
					val = ByteBuffer.wrap(array).getInt();
					
					
					if (val == 1) { //ponto
					in.read(array);
					int x =	ByteBuffer.wrap(array).getInt();
					in.read(array);
					int y =	ByteBuffer.wrap(array).getInt();
					 
						Point p = new Point(x,y);
						geometries.add(p);
						
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
						geometries.add(l);
						
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
						geometries.add(r);
						
					}else if(val == 4) {//circulo
						in.read(array);
						int x = ByteBuffer.wrap(array).getInt();
						in.read(array);
						int y = ByteBuffer.wrap(array).getInt();
						in.read(array);
						int raio = ByteBuffer.wrap(array).getInt();
						
						Circle c = new Circle(x, y, raio);
						geometries.add(c);
						
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
						geometries.add(t);
						
					}
				}
			} catch (EOFException endOfFileException) { // fim do arquivo foi alcançado
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//salva binario
		protected void saveBinForms(File f) {
			FileOutputStream out = null;
			
			byte[] bytes = new byte [4];
			
			try {
				out = new FileOutputStream(f);  
				
				for(GeometricForm g : geometries) {
					out.write(g.formId());
					if(g.formId() == 1) {//ponto
						ByteBuffer.wrap(bytes).putInt(((Point)g).x);
						out.write(bytes);
						ByteBuffer.wrap(bytes).putInt(((Point)g).y);
						out.write(bytes);
						System.out.printf("%d %d %d\n", g.formId(), ((Point)g).x, ((Point)g).y);
					}else if(g.formId() == 2) {//linha
						ByteBuffer.wrap(bytes).putInt(((Line)g).xi);
						out.write(bytes);
						ByteBuffer.wrap(bytes).putInt(((Line)g).yi);
						out.write(bytes);
						ByteBuffer.wrap(bytes).putInt(((Line)g).xf);
						out.write(bytes);
						ByteBuffer.wrap(bytes).putInt(((Line)g).yf);
						out.write(bytes);
						System.out.printf("%d %d %d %d %d\n", g.formId(), ((Line)g).xi, ((Line)g).yi, ((Line)g).xf, ((Line)g).yf);
					} else if(g.formId() == 3) {//retangulo
						ByteBuffer.wrap(bytes).putInt(((Rectangle)g).x);
						out.write(bytes);
						ByteBuffer.wrap(bytes).putInt(((Rectangle)g).y);
						out.write(bytes);
						ByteBuffer.wrap(bytes).putInt(((Rectangle)g).width);
						out.write(bytes);
						ByteBuffer.wrap(bytes).putInt(((Rectangle)g).height);
						out.write(bytes);
						System.out.printf("%d %d %d %d %d\n", g.formId(), ((Rectangle)g).x, ((Rectangle)g).y, ((Rectangle)g).width, ((Rectangle)g).height);
					}else if(g.formId() == 4) {//circulo
						ByteBuffer.wrap(bytes).putInt(((Circle)g).x);
						out.write(bytes);
						ByteBuffer.wrap(bytes).putInt(((Circle)g).x);
						out.write(bytes);
						ByteBuffer.wrap(bytes).putInt(((Circle)g).raio);
						out.write(bytes);
						System.out.printf("%d %d %d %d \n", g.formId(), ((Circle)g).x, ((Circle)g).y, ((Circle)g).raio);
					}else if(g.formId() == 5) {//triangulo
						ByteBuffer.wrap(bytes).putInt(((Triangle)g).x);
						out.write(bytes);
						ByteBuffer.wrap(bytes).putInt(((Triangle)g).x);
						out.write(bytes);
						ByteBuffer.wrap(bytes).putInt(((Triangle)g).base);
						out.write(bytes);
						ByteBuffer.wrap(bytes).putInt(((Triangle)g).altura);
						out.write(bytes);
						System.out.printf("%d %d %d %d %d\n", g.formId(), ((Triangle)g).x, ((Triangle)g).y, ((Triangle)g).base, ((Triangle)g).altura);
					}
				}out.close();
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
			FileNameExtensionFilter textFilterB = new FileNameExtensionFilter("Binary file", "bin");
			fc.addChoosableFileFilter(textFilterB);
			
			
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
