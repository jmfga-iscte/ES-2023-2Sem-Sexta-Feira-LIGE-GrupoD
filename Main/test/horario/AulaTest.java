package horario;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import horario.Aula;

import static org.junit.Assert.assertEquals;


class AulaTest {
	       
	        @Test
	        public void testAulaSettersAndGetters() {
	        	Aula aula = new Aula();
                aula.setCurso("ME");
                assertEquals("ME", aula.getCurso());
                aula.setUnidadeCurricular("Teoria dos Jogos e dos Contratos");
                assertEquals("Teoria dos Jogos e dos Contratos", aula.getUnidadeCurricular());
                aula.setTurno("01789TP01");
                assertEquals("01789TP01", aula.getTurno());
                aula.setTurma("MEA1");
                assertEquals("MEA1", aula.getTurma());
                aula.setInscritosNoTurno(30);
                assertEquals(30, aula.getInscritosNoTurno());
                aula.setdiaDaSemana("Sexta");
                assertEquals("Sexta", aula.getdiaDaSemana());
                aula.setHoraInicio(LocalTime.of(13,0,0));
                assertEquals(LocalTime.of(13,0,0), aula.getHoraInicio());
                aula.setHoraFim(LocalTime.of(14,30,0));
                assertEquals("14:30", aula.getHoraFim());
                aula.setDataAula(LocalDate.of(2022, 12, 2));
                assertEquals(LocalDate.of(2022, 12, 2), aula.getData());
                aula.setSala("AA2.25");
                assertEquals("AA2.25", aula.getSala());
                aula.setLotacaoDaSala(34);
                assertEquals(34, aula.getLotacaoDaSala());
                assertEquals("13:00", aula.getHoraInicioString());
                assertEquals("2022-12-02", aula.getDataString());
            }
	        
	        @Test
	        public void testDiaDaSemanaInt() {
	            Aula aula = new Aula();
	            assertEquals(1, aula.getdiaDaSemanaInt("Seg"));
	            assertEquals(2, aula.getdiaDaSemanaInt("Ter"));
	            assertEquals(3, aula.getdiaDaSemanaInt("Qua"));
	            assertEquals(4, aula.getdiaDaSemanaInt("Qui"));
	            assertEquals(5, aula.getdiaDaSemanaInt("Sex"));
	            assertEquals(0, aula.getdiaDaSemanaInt("Dom"));
	        }

}
