package horario;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * Classe responsavel por armazenar um horario carregado a partir de ficheiros
 * JSON ou CSV.
 */
public class HorarioCarregado {
	
	private static String path="";
	private List<Aula> aulas;

	/**
	 * 
	 * Construtor da classe HorarioCarregado.
	 * 
	 * @param aulas Lista de aulas a serem armazenadas no horario.
	 */
	public HorarioCarregado(@JsonProperty("aulas") List<Aula> aulas) {
		this.aulas = aulas;
	}

	/**
	 * Cria uma inst�ncia da classe HorarioCarregado.
	 * Este construtor � vazio e n�o recebe nenhum argumento.
	 */
	
	public HorarioCarregado() {

	}
	
	/**
	 * 
	 * M�todo que retorna o caminho do ficheiro.
	 * @return Uma String que representa o caminho do arquivo.
	 */
	
	public String getPath() {
		return path;
	}
	
	/**
	 * 
	 * M�todo que atualiza o caminho do ficheiro.
	 * @param path Uma String que representa o novo caminho do arquivo.
	 */

	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 
	 * Metodo para obter a lista de aulas armazenadas no horario.
	 * 
	 * @return Lista de aulas.
	 */
	public List<Aula> getAulas() {
		return aulas;
	}

	/**
	 * 
	 * Metodo para definir a lista de aulas a serem armazenadas no horario.
	 * 
	 * @param aulas Lista de aulas.
	 */
	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}
}
