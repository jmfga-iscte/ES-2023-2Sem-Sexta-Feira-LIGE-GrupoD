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
import javax.swing.table.DefaultTableModel;

import com.opencsv.CSVWriter;

/**
 * A classe MainPanel é uma subclasse de JPanel e representa o painel principal da aplicação.
 * Ele contém os componentes gráficos necessários para interagir com o usuário e exibir informações relevantes.
 * É usado para exibir a interface gráfica da aplicação e responder aos eventos gerados pelos usuários.
 */
public class MainPanel extends JPanel {
	
	private static int auxSobrepostas=0;
	private static int auxSobrelotadas=0;
	
	private static HorarioCarregado carregado = null;
	private static HorarioCarregado selected =  new HorarioCarregado();
	
	private static JFrame frame2 = new JFrame();
	private static JFrame frameTabela = new JFrame();
	private static JFrame frameTabelaSobrepostas = new JFrame();
	
	private static List<String> listUC= new ArrayList<>();
	static List<String> selectedUC = new ArrayList<>();
	
	static List<Aula> duplicates = new ArrayList<>();
	private static List<Aula> sobrelotadas = new ArrayList<>();
	
	private static List<JCheckBox> checkboxes = new ArrayList<>();
	private static String pathAux = "";

	/**
	 * 
	 * Cria uma nova janela da aplicação e exibe o painel principal.
	 * 
	 */

	static void createWindow() {    
		JFrame frame = new JFrame("Horario");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createGUI(frame);
		frame.setSize(560, 200);      
		frame.setLocationRelativeTo(null);  
		frame.setVisible(true);
	}
	
	/**
	 * 
	 * Este metodo cria uma GUI com varios botoes que posteriormente poderao ser usados tanto para carregar ficheiross, converter ficheiros,
	 * criar horarios, ver aulas sobrepostas e aulas sobrelotadas.
	 * 
	 * @param frame a ser criado para a aplicacao
	 */

	public static void createGUI(final JFrame frame) {
		JPanel panel = new JPanel();
		JPanel panelTable = new JPanel();
		JPanel panelTableSobrepostas = new JPanel();
		LayoutManager layout = new FlowLayout();  
		panel.setLayout(layout);
		JButton button1 = new JButton("Carregar horario");
		JButton button2 = new JButton("Converter horario");
		JButton button3 = new JButton("Criar horario");
		JButton button4 = new JButton("Ver aulas sobrepostas");
		JButton button5 = new JButton("Ver sala sobrelotadas");
		JButton button6 = new JButton("Exit");
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
					label2.setText("Carregue um horario primeiro!");
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
					frame2.setTitle("Seleï¿½ï¿½o de Unidades Curriculares");
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

					JButton button = new JButton("Criar Horario");
					JButton button2 = new JButton("Guardar Horario Criado");
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
								label3.setText("Horï¿½rio criado!");

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
								label3.setText("Ainda nï¿½o criou nenhum horï¿½rio!");
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
					label2.setText("Carregue um horï¿½rio primeiro!");
				}
			}

		});

		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(carregado==null)){
					getSobrepostas();
					if(duplicates.size()==0){
						label2.setText("Nï¿½o existem aulas sobrepostas no horï¿½rio que carregou!");
						return;
					} else if (auxSobrepostas == 0){
						frameTabela.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
						frameTabela.setTitle("Aulas sobrepostas");
						frameTabela.setLocationRelativeTo(null);
						frameTabela.setLayout(new BorderLayout());
						frameTabela.setSize(1000, 400);
		            	String[] columnNames = {"Curso", "Unidade Curricular", "Turno", "Turma", "Inscritos no turno",
		            			"Dia da semana", "Hora inï¿½cio da aula", "Hora fim da aula", "Data da aula", "Sala atribuï¿½da ï¿½ aula", "Lotaï¿½ï¿½o da sala"};
						DefaultTableModel model = new DefaultTableModel(columnNames, 0);
						
						List<String[]> dados = new ArrayList<>();
						for (Aula aula : duplicates) {
							if(aula.Data == null){
								String[] data = {aula.getCurso(), aula.getUnidadeCurricular(), aula.getTurno(), aula.getTurma(), 
										Integer.toString(aula.getInscritosNoTurno()), aula.getdiaDaSemana(), aula.getHoraInicioString(),
										aula.getHoraFim(), "0", aula.getSala(), Integer.toString(aula.getLotacaoDaSala())};
								dados.add(data);
							} else {
								String[] data = {aula.getCurso(), aula.getUnidadeCurricular(), aula.getTurno(), aula.getTurma(), 
										Integer.toString(aula.getInscritosNoTurno()), aula.getdiaDaSemana(), aula.getHoraInicioString(),
										aula.getHoraFim(), aula.getDataString(), aula.getSala(), Integer.toString(aula.getLotacaoDaSala())};
								dados.add(data);
							}
						}
						for (String[] row : dados) {
							model.addRow(row);
						}
						JTable table = new JTable(model);
						Dimension preferredSize = new Dimension(800, 300);
						JScrollPane scrollPane = new JScrollPane(table);
				        scrollPane.setPreferredSize(preferredSize);
						scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
						panelTable.add(scrollPane);
						frameTabela.getContentPane().add(panelTable, BorderLayout.CENTER);
						auxSobrepostas++;
					}
					frameTabela.setVisible(true);
				} else {
					label2.setText("Carregue um horï¿½rio primeiro!");
				}
			}
		});

		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(carregado==null)){
					getSobrelotadas();
					if(sobrelotadas.size()==0){
						label2.setText("Nï¿½o existem aulas sobrelotadas no horï¿½rio que carregou!");
						return;
					} else if (auxSobrelotadas == 0){
					
						frameTabelaSobrepostas.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
						frameTabelaSobrepostas.setTitle("Aulas sobrelotadas");
						frameTabelaSobrepostas.setLocationRelativeTo(null);
						frameTabelaSobrepostas.setLayout(new BorderLayout());
						frameTabelaSobrepostas.setSize(1000, 400);
						
						String[] columnNames = {"Curso", "Unidade Curricular", "Turno", "Turma", "Inscritos no turno",
		            			"Dia da semana", "Hora inï¿½cio da aula", "Hora fim da aula", "Data da aula", "Sala atribuï¿½da ï¿½ aula", "Lotaï¿½ï¿½o da sala"};
						DefaultTableModel model = new DefaultTableModel(columnNames, 0);
						
						List<String[]> dados = new ArrayList<>();
						for (Aula aula : sobrelotadas) {
							if(aula.Data == null){
								String[] data = {aula.getCurso(), aula.getUnidadeCurricular(), aula.getTurno(), aula.getTurma(), 
										Integer.toString(aula.getInscritosNoTurno()), aula.getdiaDaSemana(), aula.getHoraInicioString(),
										aula.getHoraFim(), "0", aula.getSala(), Integer.toString(aula.getLotacaoDaSala())};
								dados.add(data);
							} else {
								String[] data = {aula.getCurso(), aula.getUnidadeCurricular(), aula.getTurno(), aula.getTurma(), 
										Integer.toString(aula.getInscritosNoTurno()), aula.getdiaDaSemana(), aula.getHoraInicioString(),
										aula.getHoraFim(), aula.getDataString(), aula.getSala(), Integer.toString(aula.getLotacaoDaSala())};
								dados.add(data);
							}
						}
						for (String[] row : dados) {
							model.addRow(row);
						}
						
						JTable table = new JTable(model);
						Dimension preferredSize = new Dimension(800, 300);
						JScrollPane scrollPane = new JScrollPane(table);
				        scrollPane.setPreferredSize(preferredSize);
						scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
						panelTableSobrepostas.add(scrollPane);
						frameTabelaSobrepostas.getContentPane().add(panelTableSobrepostas, BorderLayout.CENTER);
						auxSobrelotadas++;
					}
					frameTabelaSobrepostas.setVisible(true);
				
				} else {
					label2.setText("Carregue um horï¿½rio primeiro!");
				}
				
			}
		});
		
		button6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		panel.add(button1); 
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		panel.add(button5);
		panel.add(button6);
		panel.add(label2);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
	}
	
	/**
	 * 
	 * Este método recebe uma lista de aulas e identifica as aulas que possuem a mesma hora de início.
	 * Em seguida, armazena essas aulas em uma lista de duplicadas.
	 * 
	 */

	public static void getSobrepostas(){
			Map<String, Integer> countMap = new HashMap<>();
			for (Aula aula : carregado.getAulas()) {
				String horaInicio = aula.getHoraInicioString();
				countMap.put(horaInicio, countMap.getOrDefault(horaInicio, 0) + 1);
			}
			for (Aula aula : carregado.getAulas()) {
				String horaInicio = aula.getHoraInicioString();
				if (countMap.get(horaInicio) > 1) {
					duplicates.add(aula);
				}
			}
	}
	
	/**
	 * Identifica as aulas que estão sobrelotadas.
	 * Uma aula está sobrelotada quando o número de inscritos no turno é maior do que a lotação da sala.
	 * As aulas sobrelotadas são adicionadas à lista "sobrelotadas".
	 */

	public static void getSobrelotadas(){
			for (Aula aula : carregado.getAulas()) {
				if(aula.getInscritosNoTurno()>aula.getLotacaoDaSala()){
					sobrelotadas.add(aula);
				}
			}
	}
	
	/**
	 * 
	 * Adiciona uma unidade curricular selecionada à lista de UCs selecionadas, se ainda não estiver presente.
	 * @param uc String contendo o código da unidade curricular a ser adicionada.
	 * 
	 */
	
	public static void addSelectedUC(String uc){
		if (!selectedUC.contains(uc)){
			selectedUC.add(uc);
		}
	}
	
	/**
	 * 
	 * Remove uma unidade curricular da lista de UCs selecionadas.
	 * @param uc a unidade curricular a ser removida da lista.
	 * 
	 */
	
	public static void removeSelectedUC(String uc){
		if (selectedUC.contains(uc)){
			selectedUC.remove(uc);
		}
	}
	
	/**
	 * 
	 * Guarda os dados do horário carregado num ficheiro CSV no diretório especificado.
	 * @param hc HorarioCarregado contendo os dados a serem gravados.
	 * @param pathDestino Caminho do diretório de destino onde o ficheiro CSV será guardado.
	 * 
	 */

	public static void saveCsv(HorarioCarregado hc, String pathDestino){
		File file = new File(pathDestino);
		for (int i=0; i!=hc.getAulas().size(); i++) {
			try {
				CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8), ';', CSVWriter.DEFAULT_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
	
				String[] headers = {"Curso", "Unidade Curricular", "Turno", "Turma", "Inscritos no turno", "Dia da semana", "Hora inicio da aula", "Hora fim da aula", "Data da aula", "Sala atribuida a aula", "Lotacao da sala"};
				csvWriter.writeNext(headers);
	
				for (Aula aula : hc.getAulas()){
					String curso = aula.getCurso();
					String uC = aula.getUnidadeCurricular();
					String turno = aula.getTurno();
					String turma = aula.getTurma();
					int inscritos = aula.getInscritosNoTurno();
					String dds = aula.getdiaDaSemana();
					String hia = aula.getHoraInicioString();
					String hfa = aula.getHoraFim();
					String data = aula.getDataString();
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
	
	/**
	 * 
	 * Este método tem como objetivo criar uma lista das unidades curriculares presentes no horário carregado.
	 * Para isso, percorre todas as aulas presentes no horário carregado e adiciona nessa lista de unidades curriculares 
	 * apenas aquelas que ainda não foram adicionadas.
	 * 
	 */

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
