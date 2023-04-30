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

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Representa um horario carregado a partir de um ficheiro JSON ou CSV.
 */

 @MultipartConfig(
  fileSizeThreshold = 1024 * 1024 * 10,  // 10 MB
  maxFileSize = 1024 * 1024 * 50,        // 50 MB
  maxRequestSize = 1024 * 1024 * 100     // 100 MB
)

public class Horario extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Part filePart = request.getPart("arquivo");
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		String caminhoArquivo = "C:/Users/Diogo/Desktop/a/" + fileName;

		if(fileName.isEmpty()) {
			request.setAttribute("mensagem", "Caminho do arquivo inválido.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
		try {
			HorarioCarregado horario = carregar(caminhoArquivo);
			request.setAttribute("horario", horario);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch(IOException e) {
			request.setAttribute("mensagem", "Erro ao carregar arquivo.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
	



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
			return carregarJson(caminho);
		} else if (extensao.equalsIgnoreCase("csv")) {
			return carregarCsv(caminho);
		} else {
			throw new IllegalArgumentException("Formato de arquivo invalido: " + extensao);
		}
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
		System.out.println(horario.getAulas().get(0).getdiaDaSemana());
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