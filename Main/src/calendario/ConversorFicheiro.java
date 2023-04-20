package calendario;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class ConversorFicheiro {
	
	public static void convertJsonToCsv() {
		String jsonString;
		JSONObject jsonObject;
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Insira aqui o path do ficheiro que quer converter: ");
			String JSONPath = scanner.nextLine();
			System.out.print("Insira aqui o destino onde quer guardar o ficheiro que converteu: ");
			String CSVPath = scanner.nextLine();
			jsonString = new String(Files.readAllBytes(Paths.get(JSONPath)));
			jsonObject = new JSONObject(jsonString);
			JSONArray doc = jsonObject.getJSONArray("test");
			File file = new File(CSVPath);
			String csvString = CDL.toString(doc);
//			String csvString2 = csvString.replace(',', ';');
//			System.out.println(csvString2);
//			FileUtils.writeStringToFile(file, csvString2);
			FileUtils.writeStringToFile(file, csvString);
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Ficheiro nao encontrado.");
		} catch (JSONException e) {
			e.printStackTrace();
			System.out.println("Algum erro a ler o JSON.");
		}
	}
	
	public static void convertCsvToJson() throws IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Insira aqui o path do ficheiro que quer converter: ");
		String CSVPath = scanner.nextLine();
		System.out.print("Insira aqui o destino onde quer guardar o ficheiro que converteu: ");
		String JSONPath = scanner.nextLine();
		// Crie um objeto CsvMapper para ler o arquivo CSV
		CsvMapper csvMapper = new CsvMapper();
		// Defina o esquema CSV
		CsvSchema schema = CsvSchema.emptySchema().withHeader();
		// Leia o arquivo CSV como uma lista de mapas
		List<Object> data;
		scanner.close();
		try {
			data = csvMapper.readerFor(Map.class).with(schema).readValues(new File(CSVPath)).readAll();
			// Crie um objeto ObjectMapper para escrever o arquivo JSON
			ObjectMapper jsonMapper = new ObjectMapper();
			jsonMapper.enable(SerializationFeature.INDENT_OUTPUT); 
			// Escreva a lista de mapas como um arquivo JSON
			try {
				jsonMapper.writerWithDefaultPrettyPrinter().writeValue(new File(JSONPath), data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Arquivo JSON criado com sucesso!");
	}

	public static void main(String[] args) {
//		convertJsonToCsv();
		try {
			convertCsvToJson();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
