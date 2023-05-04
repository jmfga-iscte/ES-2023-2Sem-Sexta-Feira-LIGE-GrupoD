package horario;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.JFileChooser;

public class MainPanel extends JPanel{

		private static HorarioCarregado carregado=null;
		static JFrame frame = new JFrame("Horario");
		final JLabel label = new JLabel();
		
		private static void createWindow() {    
		      JFrame frame = new JFrame("Hor�rio");
		      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      createGUI(frame);
		      frame.setSize(560, 200);      
		      frame.setLocationRelativeTo(null);  
		      frame.setVisible(true);
		   }
		
	    public static void createGUI(final JFrame frame) {
	    	JPanel panel = new JPanel();
	        LayoutManager layout = new FlowLayout();  
	        panel.setLayout(layout);
	        JButton button1 = new JButton("Carregar horario");
	        JButton button2 = new JButton("Converter horario");
	        JButton button3 = new JButton("Vizualizar horario na web");
	        JButton button4 = new JButton("Exit");
	        final JLabel label = new JLabel();

	        button1.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	JFileChooser fileChooser = new JFileChooser();
	                int result = fileChooser.showOpenDialog(frame);
	                if (result == JFileChooser.APPROVE_OPTION) {
	                    File selectedFile = fileChooser.getSelectedFile();
	                    String s = selectedFile.getAbsolutePath();
	                    System.out.println("Selected file: " + s);
	                    try {
							carregado = Horario.carregar(s);
							System.out.println(carregado.getPath());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
	                }
	            }
	        });

	        button2.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		if(!(carregado == null)){
		        		JFileChooser fileChooser = new JFileChooser();
		                int option = fileChooser.showSaveDialog(frame);
		                if(option == JFileChooser.APPROVE_OPTION){
		                	try {
		                		String extensao = carregado.getPath().substring(carregado.getPath().lastIndexOf('.') + 1);
		                		if (extensao.equalsIgnoreCase("json")) {
		                			extensao = ".csv";		
		                		} else if (extensao.equalsIgnoreCase("csv")) {
		                			extensao = ".json";	
		                		}
								ConversorFicheiro.convertFile(carregado.getPath(), fileChooser.getSelectedFile().getAbsolutePath() + extensao);
								File file = fileChooser.getSelectedFile();
				                label.setText("File Saved as: " + file.getName() + extensao);
							} catch (IOException e1) {
								
								e1.printStackTrace();
							}
		                }else{
		                   label.setText("Save command canceled");
		                }
	        		}
	                else {
	        			System.out.println("Ainda nao carregou nenhum horario!");
	        		}
	        	}
	        });

	        button3.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
//	                OtherClass.function3();
	            }
	        });

	        button4.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	System.exit(0);
	            }
	        });

	        panel.add(button1); 
	        panel.add(button2);
	        panel.add(button3);
	        panel.add(button4);
	        panel.add(label);
	        frame.getContentPane().add(panel, BorderLayout.CENTER);
	    }

	    public static void main(String[] args) {
	        
	    	createWindow();
	    }
}
