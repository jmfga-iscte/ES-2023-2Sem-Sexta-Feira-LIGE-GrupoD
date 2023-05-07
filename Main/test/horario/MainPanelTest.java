package horario;

import java.io.FileReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainPanelTest {

	private Aula aula1 = new Aula();
	private Aula aula2 = new Aula();;
	private List<Aula> aulas = new ArrayList<Aula>();
	HorarioCarregado hc = new HorarioCarregado(aulas);
	private static HorarioCarregado carregado;
		
	@BeforeEach
	void setUp(){
		aula1.setCurso("ME");
		aula2.setCurso("ME");
		aula1.setUnidadeCurricular("Teoria dos Jogos e dos Contratos");
		aula2.setUnidadeCurricular("Teoria dos Jogos e dos Contratos");
		aula1.setTurno("01789TP01");
		aula2.setTurno("01789TP01");
		aula1.setTurma("MEA1");
		aula2.setTurma("MEA1");
		aula1.setInscritosNoTurno(30);
		aula2.setInscritosNoTurno(30);
		aula1.setdiaDaSemana("Sexta");
		aula2.setdiaDaSemana("Sexta");
		aula1.setHoraInicio(LocalTime.of(13,0,0));
		aula2.setHoraInicio(LocalTime.of(13,0,0));
		aula1.setHoraFim(LocalTime.of(14,30,0));
		aula2.setHoraFim(LocalTime.of(14,30,0));
		aula1.setDataAula(LocalDate.of(2022,12,2));
		aula2.setDataAula(LocalDate.of(2022,11,23));
		aula1.setSala("AA2.25");
		aula2.setSala("AA2.25");
		aula1.setLotacaoDaSala(34);
		aula2.setLotacaoDaSala(34);
		aulas.add(aula1);
		aulas.add(aula2); 		
	}
	
    @Test
    public void testAddSelectedUC() {
        MainPanel.addSelectedUC("UC1");
        MainPanel.addSelectedUC("UC2");
        Assert.assertEquals(2, MainPanel.selectedUC.size());
        Assert.assertTrue(MainPanel.selectedUC.contains("UC1"));
        Assert.assertTrue(MainPanel.selectedUC.contains("UC2"));
    }
	
    @Test
    public void testRemoveSelectedUC() {
        MainPanel.addSelectedUC("UC1");
        MainPanel.addSelectedUC("UC2");
        MainPanel.removeSelectedUC("UC1");
        assertEquals(1, MainPanel.selectedUC.size());
        assertFalse(MainPanel.selectedUC.contains("UC1"));
        assertTrue(MainPanel.selectedUC.contains("UC2"));
    }


	@Test
	public void testSaveCsv() {
		String pathDestino = "C:/Users/Ricardo Agostinho/Desktop/ES/Testes/exemplo.csv";
		MainPanel.saveCsv(hc, pathDestino);

		try {
			BufferedReader br = new BufferedReader(new FileReader(pathDestino));
			String line;
			int lineNumber = 0;

			// Verifica os cabeçalhos
			line = br.readLine();
			lineNumber++;
			Assert.assertEquals("\"Curso\";\"Unidade Curricular\";\"Turno\";\"Turma\";\"Inscritos no turno\";\"Dia da semana\";\"Hora inicio da aula\";\"Hora fim da aula\";\"Data da aula\";\"Sala atribuida a aula\";\"Lotacao da sala\"", line);

			// Verifica os registros das aulas
			while ((line = br.readLine()) != null) {
				lineNumber++;
				String[] fields = line.split(";");
				Assert.assertEquals(11, fields.length); // Verifica se cada registro possui 11 campos

				// Verifica os valores dos campos
				Assert.assertEquals("\"" + hc.getAulas().get(lineNumber - 2).getCurso() + "\"", fields[0]);
				Assert.assertEquals("\"" + hc.getAulas().get(lineNumber - 2).getUnidadeCurricular() + "\"", fields[1]);
				Assert.assertEquals("\"" + hc.getAulas().get(lineNumber - 2).getTurno() + "\"", fields[2]);
				Assert.assertEquals("\"" + hc.getAulas().get(lineNumber - 2).getTurma() + "\"", fields[3]);
				Assert.assertEquals("\"" + String.valueOf(hc.getAulas().get(lineNumber - 2).getInscritosNoTurno()) + "\"", fields[4]);
				Assert.assertEquals("\"" + hc.getAulas().get(lineNumber - 2).getdiaDaSemana() + "\"", fields[5]);
				Assert.assertEquals("\"" + hc.getAulas().get(lineNumber - 2).getHoraInicio() + "\"", fields[6]);
				Assert.assertEquals("\"" + hc.getAulas().get(lineNumber - 2).getHoraFim() + "\"", fields[7]);
				Assert.assertEquals("\"" + hc.getAulas().get(lineNumber - 2).getData() + "\"", fields[8]);
				Assert.assertEquals("\"" + hc.getAulas().get(lineNumber - 2).getSala() + "\"", fields[9]);
				Assert.assertEquals("\"" + String.valueOf(hc.getAulas().get(lineNumber - 2).getLotacaoDaSala()) + "\"", fields[10]);
			}

			
			// Verifica se o número de registros no arquivo corresponde ao número de aulas no HorarioCarregado
			Assert.assertEquals(hc.getAulas().size(), lineNumber - 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


