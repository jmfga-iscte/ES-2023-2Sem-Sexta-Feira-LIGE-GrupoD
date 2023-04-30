package horario;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.JFileChooser;

public class MainPanel extends JPanel{

		private HorarioCarregado carregado=null;
	
	    public MainPanel() {
	        setLayout(new GridLayout(4, 2));
	        JButton button1 = new JButton("Carregar horário");
	        JButton button2 = new JButton("Converter horário");
	        JButton button3 = new JButton("Vizualizar horário na web");
	        JButton button4 = new JButton("Exit");

	        button1.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	JFileChooser fileChooser = new JFileChooser();
	                int result = fileChooser.showOpenDialog(MainPanel.this);
	                if (result == JFileChooser.APPROVE_OPTION) {
	                    File selectedFile = fileChooser.getSelectedFile();
	                    String s = selectedFile.getAbsolutePath();
	                    System.out.println("Selected file: " + s);
	                    try {
							carregado = Horario.carregar(s);
							System.out.println();
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
	        			fileChooser.setDialogTitle("Escolha a diretoria onde deseja salvar o arquivo");
	        			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	        			int result = fileChooser.showSaveDialog(null);
	        			if (result == JFileChooser.APPROVE_OPTION) {
	        			    File selectedDirectory = fileChooser.getSelectedFile();
	        			    String selectedPath = selectedDirectory.getAbsolutePath();
	        			    // use o caminho selecionado para salvar o arquivo
	        			    String pathOrigem = carregado.getPath();
	        			    try {
								ConversorFicheiro.convertFile(selectedPath, pathOrigem);
								System.out.println("Sucesso!");
							} catch (IOException e1) {
								e1.printStackTrace();
							}
	        			}
	        		} else {
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

	        add(button1); 
	        add(button2);
	        add(button3);
	        add(button4);
	    }

	    public static void main(String[] args) {
	        JFrame frame = new JFrame("Horário");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(400, 400);
	        frame.setLocationRelativeTo(null);
	        frame.add(new MainPanel());
	        frame.setVisible(true);
	    }
}
