package horario;
import static org.junit.jupiter.api.Assertions.*;

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
                assertEquals(LocalTime.of(14,30,0), aula.getHoraFim());
                aula.setDataAula("2/12/2022");
                assertEquals("2/12/2022", aula.getdData());
                aula.setSala("AA2.25");
                assertEquals("AA2.25", aula.getSala());
                aula.setLotacaoDaSala(34);
                assertEquals(34, aula.getLotacaoDaSala());
            }
}
