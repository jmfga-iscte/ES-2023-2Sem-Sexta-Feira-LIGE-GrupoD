package horario;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.opencsv.CSVWriter;
import com.fasterxml.jackson.databind.MappingIterator;

/**
 * Representa um conversor de ficheiros, seja para converter de JSON para CSV e vice-versa.
 */
public class ConversorFicheiro {

	/**
	 * Este método converte um ficheiro JSON num ficheiro CSV e guarda o mesmo num local
	 * especificado pelo utilizador.
	 * 
	 * Para converter o ficheiro, o utilizador deve fornecer o caminho completo do
	 * ficheiro JSON que deseja converter e o caminho completo onde deseja guardar o
	 * ficheiro CSV resultante.
	 *
	 * O método usa a biblioteca JSON-java para analisar o ficheiro JSON e a
	 * biblioteca Apache Commons CSV para converter o objeto JSON numa string CSV.
	 *
	 * @throws IOException   se o ficheiro JSON não puder ser encontrado ou se houver
	 *                       um erro ao escrever o ficheiro CSV.
	 * @throws JSONException se ocorrer algum erro ao analisar o ficheiro JSON.
	 * 
	 */
	
	public static void convertFile(String pathOrigem, String pathDestino) throws IOException{
		String extensao = pathOrigem.substring(pathOrigem.lastIndexOf('.') + 1);
		if(extensao.equalsIgnoreCase("json")){
			convertJsonToCsv(pathOrigem, pathDestino);
		} else if (extensao.equalsIgnoreCase("csv")) {
			convertCsvToJson(pathOrigem, pathDestino);
		}
	}
	
	
	public static void convertJsonToCsv(String pathOrigem, String pathDestino) {
		String jsonString;
		JSONObject jsonObject;
		try {
		    jsonString = new String(Files.readAllBytes(Paths.get(pathOrigem)), StandardCharsets.UTF_8);
		    jsonObject = new JSONObject(jsonString);
		    JSONArray doc = jsonObject.getJSONArray("aulas");
		    File file = new File(pathDestino);

		    CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8), ';', CSVWriter.DEFAULT_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
		    
		    String[] headers = {"Curso", "Unidade Curricular", "Turno", "Turma", "Inscritos no turno", "Dia da semana", "Hora início da aula", "Hora fim da aula", "Data da aula", "Sala atribuída à aula", "Lotação da sala"};
		    csvWriter.writeNext(headers);

		    for (int i = 0; i < doc.length(); i++) {
		        JSONObject obj = doc.getJSONObject(i);
		        String curso = obj.getString("Curso");
		        String uC = obj.getString("Unidade Curricular");
		        String turno = obj.getString("Turno");
		        String turma = obj.getString("Turma");
		        int inscritos = obj.getInt("Inscritos no turno");
		        String dds = obj.getString("Dia da semana");
		        String hia = obj.getString("Hora início da aula");
		        String hfa = obj.getString("Hora fim da aula");
		        String data = obj.getString("Data da aula");
		        String sala = obj.getString("Sala atribuída à aula");
		        int lotacao = obj.getInt("Lotação da sala");
		        String[] record = {curso, uC, turno, turma, String.valueOf(inscritos), dds, hia, hfa, data, sala, String.valueOf(lotacao)};
		        csvWriter.writeNext(record);
		    }

		    csvWriter.close();

		} catch (IOException e) {
		    e.printStackTrace();
		    System.out.println("Ficheiro nao encontrado.");
		} catch (JSONException e) {
		    e.printStackTrace();
		    System.out.println("Algum erro a ler o JSON.");
		}
	}
	/**
	 * Converte um ficheiro CSV num ficheiro JSON. Este método lê um ficheiro CSV do
	 * caminho especificado pelo utilizador e converte o mesmo num ficheiro JSON, que é
	 * guardado no caminho especificado pelo utilizador.
	 * 
	 * @throws IOException se ocorrer um erro ao ler ou escrever um ficheiro
	 */
	public static void convertCsvToJson(String pathOrigem, String pathDestino) throws IOException {
		File input = new File(pathOrigem);

	    CsvSchema csvSchema = CsvSchema.builder().setColumnSeparator(';').setUseHeader(true).build();

	    CsvMapper csvMapper = new CsvMapper();


	    // Read data from CSV file
	    MappingIterator<Map<String, String>> mappingIterator = csvMapper.readerFor(Map.class).with(csvSchema).readValues(input);
	    List<Map<String, String>> readAll = mappingIterator.readAll();

	    // Convert List<Map<String, String>> to List<HashMap<String, String>>
	    List<HashMap<String, String>> dataList = new ArrayList<>();
	    for (Map<String, String> map : readAll) {
	        HashMap<String, String> hashMap = new HashMap<>();
	        for (String key : map.keySet()) {
	            hashMap.put(key.toLowerCase(), map.get(key));
	        }
	        dataList.add(hashMap);
	    }
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.enable(SerializationFeature.INDENT_OUTPUT);

	    // Write JSON formatted data to output.json file
	    Map<String, List<Map<String, String>>> jsonData = new HashMap<>();
	    jsonData.put("aulas", readAll);
	    File output = new File(pathDestino);
	    mapper.writerWithDefaultPrettyPrinter().writeValue(output, jsonData);
	}

	          


	public static void main(String[] args) throws IOException {
	}
}
