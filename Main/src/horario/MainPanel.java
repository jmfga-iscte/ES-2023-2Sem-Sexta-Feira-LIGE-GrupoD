package horario;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.JFileChooser;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.opencsv.CSVWriter;

import javafx.application.Application;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainPanel extends JPanel {

		private static HorarioCarregado carregado = null;
		private static HorarioCarregado selected =  new HorarioCarregado();
		static JFrame frame = new JFrame("Hor�rio");
		static JFrame frame2 = new JFrame();
		private static List<String> listUC= new ArrayList<>();
		private static List<String> selectedUC = new ArrayList<>();
		private static List<JCheckBox> checkboxes = new ArrayList<>();
		private static JCheckBox checkbox = new JCheckBox();
		private static String pathAux = "";
		
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
	        JButton button1 = new JButton("Carregar hor�rio");
	        JButton button2 = new JButton("Converter hor�rio");
	        JButton button3 = new JButton("Criar hor�rio");
	        JButton button4 = new JButton("Exit");
	        final JLabel label2 = new JLabel();

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
				                label2.setText("File Saved as: " + file.getName() + extensao);
							} catch (IOException e1) {
								
								e1.printStackTrace();
							}
		                }else{
		                   label2.setText("Save command canceled");
		                }
	        		}
	                else {
	        			label2.setText("Carregue um hor�rio antes de converter!");
	        		}
	        	}
	        });
	        
	        button3.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	if(!(carregado == null)){
		            	JPanel topPanel = new JPanel();  
		            	topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		                JScrollPane scrollPane = new JScrollPane(topPanel);
		                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		            	JPanel midPanel = new JPanel(new GridLayout(2,0));   
		            	JPanel bottomPanel = new JPanel(new GridLayout(1,0));
		            	JRadioButton option1 = new JRadioButton("Guardar em csv");
		                JRadioButton option2 = new JRadioButton("Guardar em json");
		                JLabel label3 = new JLabel();
		                ButtonGroup buttonGroup = new ButtonGroup();
		                buttonGroup.add(option1);
		                buttonGroup.add(option2);
		             
		                frame2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		                frame2.setTitle("Sele��o de Unidades Curriculares");
		                frame2.setLocationRelativeTo(null);
		                frame2.setSize(500, 200);
		                 
		                JPanel panel = new JPanel(new BorderLayout());
		                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	
		                JLabel label = new JLabel("Selecione as Unidades Curriculares:");
		                topPanel.add(label);
		                setListUC();
		                for (String uc : listUC) {
							JCheckBox checkbox = new JCheckBox(uc);
							checkboxes.add(checkbox);
							System.out.println(checkbox.getLabel());
							topPanel.add(checkbox);
						}
	
		                JButton button = new JButton("Criar Hor�rio");
		                JButton button2 = new JButton("Guardar Hor�rio Criado");
		                button.addActionListener(new ActionListener() {
		                    @Override
		                    public void actionPerformed(ActionEvent e) {
		                    	
		                        for (JCheckBox jCheckBox : checkboxes) {
		                        	if (jCheckBox.isSelected()) {
		                        		addSelectedUC(jCheckBox.getLabel());
			                        }
		                        	if (!jCheckBox.isSelected()) {
		                        		removeSelectedUC(jCheckBox.getLabel());
			                        }
								}
		                        if(!(selectedUC.isEmpty())){
			                        List<Aula> aux = new ArrayList<>();
			                        for (Aula aula : carregado.getAulas()) {
										if(selectedUC.contains(aula.getUnidadeCurricular())){
											aux.add(aula);
										}
									}
			                        selected.setAulas(aux);
			                        label3.setText("Hor�rio criado!");
			                    
		                    } else {
		                    	label3.setText("Selecione uma UC primeiro!");
		                    }
	                    }
	                });
		                button2.addActionListener(new ActionListener() {
		                    @Override
		                    public void actionPerformed(ActionEvent e) {
		                    	if(!(selected.getAulas()==null)){
		                    		if(!(!option1.isSelected() && !option2.isSelected())){
			                    		String path;
			    		        		JFileChooser fileChooser = new JFileChooser();
			    		                int option = fileChooser.showSaveDialog(frame);
			    		                if(option == JFileChooser.APPROVE_OPTION){
			    		                	if(option1.isSelected()){
			    		                		path = fileChooser.getSelectedFile().getAbsolutePath() + ".csv";
			    								saveCsv(selected, path);
			    				                label.setText("File Saved as: " + path);	 
			    		                	} else if (option2.isSelected()) {
													try {
				    		                			path = fileChooser.getSelectedFile().getAbsolutePath() + ".csv";
														saveCsv(selected, path);
														ConversorFicheiro.convertFile(path, pathAux + ".json");
														File file = new File(path);
														file.delete();
													} catch (IOException e1) {
														e1.printStackTrace();
													}
													label.setText("File Saved as: " + pathAux + ".json");
				    		                	}
			    		                }else{
			    		                   label3.setText("Save command canceled");
			    		                }
		                    		} else {
		                    			label3.setText("Escolha o formato que quer guardar!");
		                    		}
		    	        		} else {
		    	                	label3.setText("Ainda n�o criou nenhum hor�rio!");
		    	        		}
		                    }
		                });
		                panel.add(scrollPane);
		                midPanel.add(option1);
		                midPanel.add(option2);
		                midPanel.add(label3);
		                panel.add(midPanel);
		                bottomPanel.add(button);
		                bottomPanel.add(button2);
		                panel.add(bottomPanel);
		                frame2.getContentPane().add(panel, BorderLayout.CENTER);
		                frame2.setVisible(true);
	            
	            } else {
	            	label2.setText("Ainda n�o carregou nenhum horario!");
        		}
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
	        panel.add(label2);
	        frame.getContentPane().add(panel, BorderLayout.CENTER);
	    }
	    
	    public static void addSelectedUC(String uc){
        	if (!selectedUC.contains(uc)){
        		selectedUC.add(uc);
        	}
	    }
	    
	    public static void removeSelectedUC(String uc){
        	if (selectedUC.contains(uc)){
        		selectedUC.remove(uc);
        	}
	    }
	    
	    public static void saveCsv(HorarioCarregado hc, String pathDestino){
	    	File file = new File(pathDestino);
	    	for (int i=0; i!=hc.getAulas().size(); i++) {
	    		try {
					CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8), ';', CSVWriter.DEFAULT_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
				
					String[] headers = {"Curso", "Unidade Curricular", "Turno", "Turma", "Inscritos no turno", "Dia da semana", "Hora in�cio da aula", "Hora fim da aula", "Data da aula", "Sala atribu�da � aula", "Lota��o da sala"};
				    csvWriter.writeNext(headers);

				    for (Aula aula : hc.getAulas()){
				        String curso = aula.getCurso();
				        String uC = aula.getUnidadeCurricular();
				        String turno = aula.getTurno();
				        String turma = aula.getTurma();
				        int inscritos = aula.getInscritosNoTurno();
				        String dds = aula.getdiaDaSemana();
				        String hia = aula.getHoraInicio();
				        String hfa = aula.getHoraFim();
				        String data = aula.getData();
				        String sala = aula.getSala();
				        int lotacao = aula.getLotacaoDaSala();
				        String[] record = {curso, uC, turno, turma, String.valueOf(inscritos), dds, hia, hfa, data, sala, String.valueOf(lotacao)};
				        csvWriter.writeNext(record);
				    }
				    

				    try {
						csvWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	    		} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
	    	pathAux = pathDestino.substring(0, pathDestino.lastIndexOf("."));
	    }
	    
	    public static void setListUC(){
	    	for (int i=0; i!=carregado.getAulas().size(); i++) {
            	if (!listUC.contains(carregado.getAulas().get(i).getUnidadeCurricular())){
            		listUC.add(carregado.getAulas().get(i).getUnidadeCurricular());
            	}
			}
	    }

	    public static void main(String[] args) {
	        
	    	createWindow();
	    }
}
