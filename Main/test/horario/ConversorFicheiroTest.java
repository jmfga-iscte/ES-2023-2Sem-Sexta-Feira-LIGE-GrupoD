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
import com.fasterxml.jackson.databind.ObjectMapper;

import horario.ConversorFicheiro;

class ConversorFicheiroTest {
	
    @Test
    void convertCsvToJson() throws IOException, InterruptedException {
    	String JSONPath = "C:/Users/Ricardo Agostinho/Desktop/A/csvtojson/horario.json";
        ConversorFicheiro.convertCsvToJson();
        File jsonFile = new File(JSONPath);
        assertTrue(jsonFile.exists());
        assertTrue(jsonFile.isFile());
        ObjectMapper jsonMapper = new ObjectMapper();
        List<Map<String, String>> jsonData = jsonMapper.readValue(jsonFile, new TypeReference<>() {});
        assertEquals(3, jsonData.size());
        assertEquals("Developer", jsonData.get(0).get("role"));
        assertEquals("Test User1", jsonData.get(0).get("name"));
        assertEquals("22", jsonData.get(0).get("age")); 
        assertEquals("Analyst", jsonData.get(1).get("role"));
        assertEquals("Test User2", jsonData.get(1).get("name"));
        assertEquals("40", jsonData.get(1).get("age"));
        assertEquals("Intern", jsonData.get(2).get("role"));
        assertEquals("Test User3", jsonData.get(2).get("name"));
        assertEquals("20", jsonData.get(2).get("age"));
    }
	
	
//	@Test
//    void convertJsonToCsvTest() throws IOException {
//		String CSVPath = "C:/Users/Ricardo Agostinho/Desktop/A/jsontocsv/jsonvalidator.csv";
//        ConversorFicheiro.convertJsonToCsv();
//        File csvFile = new File(CSVPath);
//        assertTrue(csvFile.exists());
//        assertTrue(csvFile.isFile());
//        List<String> csvLines = Files.readAllLines(Paths.get(CSVPath));
//        assertEquals(4, csvLines.size());
//        assertEquals("role,name,age", csvLines.get(0));
//        assertEquals("Developer,Test User1,22", csvLines.get(1));
//        assertEquals("Analyst,Test User2,40", csvLines.get(2));
//        assertEquals("Intern,Test User3,20", csvLines.get(3));
//    }
	


}
