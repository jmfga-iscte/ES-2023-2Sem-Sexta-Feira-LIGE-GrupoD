package horario;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

class ConversorFicheiroTest {
	
	@Test
	void convertCsvToJson() throws IOException, InterruptedException {
	    String csvPath = "C:/Users/Ricardo Agostinho/Desktop/ES/Testes/csvtojson/horario.csv";
	    String jsonPath = "C:/Users/Ricardo Agostinho/Desktop/ES/Testes/csvtojson/horario.json";
	    ConversorFicheiro.convertFile(csvPath, jsonPath);
	    File jsonFile = new File(jsonPath);
	    assertTrue(jsonFile.exists());
	    assertTrue(jsonFile.isFile());
	    ObjectMapper jsonMapper = new ObjectMapper();
	    jsonMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
	    List<Map<String, Object>> jsonData = jsonMapper.readValue(jsonFile, new TypeReference<List<Map<String, Object>>>() {});
	    System.out.println(jsonData.get(0));
	    
	    assertEquals(1, jsonData.size());
	}
	
	@Test
    void convertJsonToCsvTest() throws IOException {
		String csvPath = "C:/Users/Ricardo Agostinho/Desktop/ES/Testes/jsontocsv/jsonvalidator.csv";
		String jsonPath = "C:/Users/Ricardo Agostinho/Desktop/ES/Testes/jsontocsv/horario_exemplo.json";
        ConversorFicheiro.convertFile(jsonPath,csvPath);
        File csvFile = new File(csvPath);
        assertTrue(csvFile.exists());
        assertTrue(csvFile.isFile());
        List<String> csvLines = Files.readAllLines(Paths.get(csvPath));
        assertEquals(6, csvLines.size());
        assertEquals("\"Curso\";\"Unidade Curricular\";\"Turno\";\"Turma\";\"Inscritos no turno\";\"Dia da semana\";\"Hora inicio da aula\";\"Hora fim da aula\";\"Data da aula\";\"Sala atribuida a aula\";\"Lotacao da sala\"", csvLines.get(0));
//        assertEquals("Developer,Test User1,22", csvLines.get(1));
//        assertEquals("Analyst,Test User2,40", csvLines.get(2));
//        assertEquals("Intern,Test User3,20", csvLines.get(3));
    }
	


}
