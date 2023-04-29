package horario;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Representa um horario carregado a partir de um ficheiro JSON ou CSV.
 */

public class Horario {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * Carrega um ficheiro de dados JSON ou CSV a partir do caminho especificado.
	 * Verifica a extensao do ficheiro e invoca o metodo correspondente para
	 * carregar o ficheiro.
	 * 
	 * @param caminho O caminho do ficheiro a ser carregado.
	 * @return 
	 * @throws IOException              se ocorrer um erro ao ler o ficheiro.
	 * @throws IllegalArgumentException se a extensao do ficheiro for invalida.
	 */
	public static HorarioCarregado carregar(String caminho) throws IOException {
		String extensao = caminho.substring(caminho.lastIndexOf('.') + 1);
		if (extensao.equalsIgnoreCase("json")) {
			carregarJson(caminho);
		} else if (extensao.equalsIgnoreCase("csv")) {
			carregarCsv(caminho);
		} else {
			throw new IllegalArgumentException("Formato de arquivo invalido: " + extensao);
		}
		return null;
	}

	/**
	 * 
	 * Carrega um ficheiro CSV com informacoes relativas  as aulas e retorna um
	 * objeto do tipo HorarioCarregado com as aulas carregadas.
	 * 
	 * @param caminhoFicheiro O caminho do ficheiro CSV a ser carregado.
	 * @return Um objeto do tipo HorarioCarregado com as aulas carregadas.
	 * @throws IOException              Se ocorrer um erro de I/O durante a leitura
	 *                                  do ficheiro.
	 * @throws IllegalArgumentException Se o ficheiro estiver vazio.
	 */
	public static HorarioCarregado carregarCsv(String caminhoFicheiro) throws IOException {
		List<String> linhas = Files.readAllLines(Paths.get(caminhoFicheiro));
		if (linhas.isEmpty()) {
			throw new IllegalArgumentException("O arquivo esta vazio.");
		}
		List<Aula> aulas = new ArrayList<>();
		HorarioCarregado horario = new HorarioCarregado(aulas);

		linhas.remove(0);

		for (String linha : linhas) {
			String[] valores = preencherValores(linha.split(";"));

			Aula aula = new Aula();
			aula.setCurso(valores[0]);
			aula.setUnidadeCurricular(valores[1]);
			aula.setTurno(valores[2]);
			aula.setTurma(valores[3]);
			aula.setInscritosNoTurno(Integer.parseInt(valores[4]));
			aula.setdiaDaSemana(valores[5]);
			aula.setHoraInicio(valores[6].isEmpty() ? null : LocalTime.parse(valores[6]));
			aula.setHoraFim(valores[7].isEmpty() ? null : LocalTime.parse(valores[7]));
			aula.setDataAula(valores[8]);
			aula.setSala(valores[9].isEmpty() ? null : valores[9]);
			aula.setLotacaoDaSala(valores[10].isEmpty() ? 0 : Integer.parseInt(valores[10]));

			aulas.add(aula);
		}
		horario.setAulas(aulas);
		return horario;
	}

	/**
	 * 
	 * Preenche os valores de uma linha CSV com valores vazios caso o n mero de
	 * valores n o seja igual a 11. Se o n mero de valores for maior que 11, lan a
	 * uma exce  o.
	 * 
	 * @param valores os valores da linha CSV a serem preenchidos
	 * @return um array com os valores da linha preenchidos com valores vazios se
	 *         necess rio
	 * @throws IllegalArgumentException se o n mero de valores for maior que 11
	 */
	public static String[] preencherValores(String[] valores) {
		if (valores.length > 11) {

			throw new IllegalArgumentException("O arquivo esta mal formatado.");

		} else if (valores.length < 11) {
			String[] valoresPreenchidos = new String[11];
			for (int i = 0; i < valores.length; i++) {
				valoresPreenchidos[i] = valores[i];
			}
			for (int i = valores.length; i < 11; i++) {
				valoresPreenchidos[i] = "";
			}
			valores = valoresPreenchidos;
		}
		return valores;
	}

	/**
	 * 
	 * Carrega um objeto HorarioCarregado a partir de um ficheiro JSON.
	 * 
	 * @param filePath O caminho do ficheiro JSON a ser carregado.
	 * @return Um objeto HorarioCarregado carregado a partir do ficheiro JSON.
	 * @throws IOException           Se houver um problema ao ler o ficheiro JSON.
	 * @throws FileNotFoundException Se o ficheiro n o for encontrado.
	 */
	public static HorarioCarregado carregarJson(String filePath) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			throw new FileNotFoundException("Arquivo nao encontrado: " + filePath);
		}
		objectMapper.registerModule(new JavaTimeModule());
		return objectMapper.readValue(file, HorarioCarregado.class); // ler objeto HorarioCarregado a partir do ficheiro
																		// // JSON
	}


	

	public static void main(String[] args) throws IOException {
	}
}