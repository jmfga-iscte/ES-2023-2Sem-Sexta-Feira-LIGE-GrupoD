import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class ConversorFicheiro {
	
	private static String jsonString;
	private static JSONObject jsonObject;
	
	public static void convertJsonToCsv(String JSONPath) {
		try {
			jsonString = new String(Files.readAllBytes(Paths.get(JSONPath)));
			jsonObject = new JSONObject(jsonString);
			JSONArray doc = jsonObject.getJSONArray("test");
			File file = new File("E:\\IGE\\3ºAno_2ºSemestre\\EngenhariaSoftware\\Exemplos_Ficheiro\\teste.csv");
			String csvString = CDL.toString(doc);
//			String csvString2 = csvString.replace(',', ';');
//			System.out.println(csvString2);
//			FileUtils.writeStringToFile(file, csvString2);
			FileUtils.writeStringToFile(file, csvString);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Ficheiro nao encontrado.");
		} catch (JSONException e) {
			e.printStackTrace();
			System.out.println("Algum erro a ler o JSON.");
		}
	}
	
	public static void convertCsvToJson(String CSVPath) throws IOException {
		// Defina o caminho para o arquivo CSV que deseja converter
	    String csvFilePath = CSVPath;
	    // Crie um objeto CsvMapper para ler o arquivo CSV
	    CsvMapper csvMapper = new CsvMapper();
	    // Defina o esquema CSV
	    CsvSchema schema = CsvSchema.emptySchema().withHeader();
	    // Leia o arquivo CSV como uma lista de mapas
	    List<Object> data;
		try {
			data = csvMapper.readerFor(Map.class).with(schema).readValues(new File(csvFilePath)).readAll();
			// Crie um objeto ObjectMapper para escrever o arquivo JSON
		    ObjectMapper jsonMapper = new ObjectMapper();
		    // Escreva a lista de mapas como um arquivo JSON
		    try {
				jsonMapper.writerWithDefaultPrettyPrinter().writeValue(new File("E:\\IGE\\3ºAno_2ºSemestre\\EngenhariaSoftware\\Exemplos_Ficheiro\\teste.json"), data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	    System.out.println("Arquivo JSON criado com sucesso!");
	}

	public static void main(String[] args) {
//		convertJsonToCsv("E:\\IGE\\3ºAno_2ºSemestre\\EngenhariaSoftware\\Exemplos_Ficheiro\\jsonvalidator.json");
//		try {
//			convertCsvToJson("E:\\IGE\\3ºAno_2ºSemestre\\EngenhariaSoftware\\Exemplos_Ficheiro\\teste.csv");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		try {
//			curlyBracketsJson("E:\\IGE\\3ºAno_2ºSemestre\\EngenhariaSoftware\\Exemplos_Ficheiro\\teste.json");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}
