package horario;
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

/**
 * Representa um conversor de ficheiros, seja para converter de JSON para CSV e vice-versa.
 */
public class ConversorFicheiro {

	/**
	 * Este metodo converte um ficheiro JSON num ficheiro CSV e guarda o mesmo num local
	 * especificado pelo utilizador.
	 * 
	 * Para converter o ficheiro, o utilizador deve fornecer o caminho completo do
	 * ficheiro JSON que deseja converter e o caminho completo onde deseja guardar o
	 * ficheiro CSV resultante.
	 *
	 * O m�todo usa a biblioteca JSON-java para analisar o ficheiro JSON e a
	 * biblioteca Apache Commons CSV para converter o objeto JSON numa string CSV.
	 *
	 * @throws IOException   se o ficheiro JSON nao puder ser encontrado ou se houver
	 *                       um erro ao escrever o ficheiro CSV.
	 * @throws JSONException se ocorrer algum erro ao analisar o ficheiro JSON.
	 */
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

	/**
	 * Converte um ficheiro CSV num ficheiro JSON. Este metodo le um ficheiro CSV do
	 * caminho especificado pelo utilizador e converte o mesmo num ficheiro JSON, que e
	 * guardado no caminho especificado pelo utilizador.
	 * 
	 * @throws IOException se ocorrer um erro ao ler ou escrever um ficheiro
	 */
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
			jsonMapper.writerWithDefaultPrettyPrinter().writeValue(new File(JSONPath), data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Arquivo JSON criado com sucesso!");
	}

	public static void main(String[] args) {
		convertJsonToCsv();
	}
}
