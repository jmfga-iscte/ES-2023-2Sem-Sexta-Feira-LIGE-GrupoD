package horario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class HorarioCarregadoTest {

	private Aula aula1 = new Aula();
	private Aula aula2 = new Aula();;
	private List<Aula> aulas = new ArrayList<Aula>();
	
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
    public void testGetAulas() {
        HorarioCarregado horario = new HorarioCarregado(aulas);
        List<Aula> resultado = horario.getAulas();
        Assert.assertEquals(2, resultado.size());
        Assert.assertEquals(aula1, resultado.get(0));
        Assert.assertEquals(aula2, resultado.get(1));
    }

    @Test
    public void testSetAulas() {
        HorarioCarregado horario = new HorarioCarregado(new ArrayList<>());
        horario.setAulas(aulas);
        List<Aula> resultado = horario.getAulas();
        Assert.assertEquals(2, resultado.size());
        Assert.assertEquals(aula1, resultado.get(0));
        Assert.assertEquals(aula2, resultado.get(1));
    }
    
    @Test
    public void testGetPath() {
        // Cria um objeto HorarioCarregado
        HorarioCarregado horario = new HorarioCarregado();

        // Define um caminho de exemplo
        String path = "/path/to/file.csv";

        // Define o caminho no objeto HorarioCarregado
        horario.setPath(path);

        // Verifica se o caminho retornado é igual ao caminho definido
        Assert.assertEquals(path, horario.getPath());
    }
    

    @Test
    public void testSetPath() {
        // Cria um objeto HorarioCarregado
        HorarioCarregado horario = new HorarioCarregado();

        // Define um caminho de exemplo
        String path = "/path/to/file.json";

        // Define o caminho no objeto HorarioCarregado
        horario.setPath(path);

        // Verifica se o caminho definido é igual ao caminho retornado
        Assert.assertEquals(path, horario.getPath());
    }


}
