package horario;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import horario.Horario;
import horario.HorarioCarregado;

class HorarioTest {

	    @Test
	    public void testCarregarCsv() throws IOException {
	        HorarioCarregado horario = Horario.carregar("C:/Users/Ricardo Agostinho/Desktop/ES/Testes/horario_exemplo_3aulas.csv");
	        assertNotNull(horario);
	        assertEquals(26019, horario.getAulas().size());
	    }

	    @Test
	    public void testCarregarJson() throws IOException {
	        HorarioCarregado horario = Horario.carregar("C:/Users/Ricardo Agostinho/Desktop/ES/Testes/horario_exemplo.json");
	        assertNotNull(horario);
	        assertEquals(3, horario.getAulas().size());
	    }
}
