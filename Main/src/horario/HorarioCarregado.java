package horario;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * Classe responsável por armazenar um horário carregado a partir de ficheiros
 * JSON ou CSV.
 */
public class HorarioCarregado {
	
	private static String path="";
	private List<Aula> aulas;

	/**
	 * 
	 * Construtor da classe HorarioCarregado.
	 * 
	 * @param aulas Lista de aulas a serem armazenadas no horário.
	 */
	public HorarioCarregado(@JsonProperty("aulas") List<Aula> aulas) {
		this.aulas = aulas;
	}

	public HorarioCarregado() {

	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 
	 * Método para obter a lista de aulas armazenadas no horário.
	 * 
	 * @return Lista de aulas.
	 */
	public List<Aula> getAulas() {
		return aulas;
	}

	/**
	 * 
	 * Método para definir a lista de aulas a serem armazenadas no horário.
	 * 
	 * @param aulas Lista de aulas.
	 */
	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}
}
