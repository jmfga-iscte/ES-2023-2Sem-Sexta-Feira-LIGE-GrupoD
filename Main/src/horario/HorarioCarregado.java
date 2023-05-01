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
	 * M�todo para obter a lista de aulas armazenadas no horario.
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
