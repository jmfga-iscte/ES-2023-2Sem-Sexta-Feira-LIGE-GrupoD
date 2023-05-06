package horario;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representa uma aula associada a um determinado curso, unidade curricular e turno.
 */
public class Aula {
	@JsonProperty("Curso")
	private String Curso;
	@JsonProperty("Unidade Curricular")
	private String UnidadeCurricular;
	@JsonProperty("Turno")
	private String Turno;
	@JsonProperty("Turma")
	private String Turma;
	@JsonProperty("Inscritos no turno")
	private int InscritosNoTurno;
	@JsonProperty("Dia da semana")
	private String DiaDaSemana;
	@JsonProperty("Hora início da aula")
	private LocalTime HoraInicio;
	@JsonProperty("Hora fim da aula")
	private LocalTime HoraFim;
	@JsonProperty("Data da aula")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate Data;
	@JsonProperty("Sala atribuída à aula")
	private String Sala;
	@JsonProperty("Lotação da sala")
	private int LotacaoDaSala;

	/**
	 * Cria uma nova instância de Aula com valores padrão. Os valores padrão devem
	 * ser definidos posteriormente usando os métodos setters.
	 */
	public Aula() {

		// "Usamos o setter para definir os valores, seria tambem um erro usar um
		// construtor com 11 parametros

	}

	/**
	 * Retorna o nome do curso.
	 *
	 * @return o nome do curso
	 */
	public String getCurso() {
		return Curso;
	}

	/**
	 * Define o nome do curso.
	 *
	 * @param curso o nome do curso
	 */
	public void setCurso(String curso) {
		this.Curso = curso;
	}

	/**
	 * Retorna o nome da unidade curricular.
	 *
	 * @return o nome da unidade curricular
	 */
	public String getUnidadeCurricular() {
		return UnidadeCurricular;
	}

	/**
	 * Define o nome da unidade curricular.
	 *
	 * @param unidadeCurricular o nome da unidade curricular
	 */
	public void setUnidadeCurricular(String unidadeCurricular) {
		this.UnidadeCurricular = unidadeCurricular;
	}

	/**
	 * Retorna o nome do turno.
	 *
	 * @return o nome do turno
	 */
	public String getTurno() {
		return Turno;
	}

	/**
	 * Define o nome do turno.
	 *
	 * @param turno o nome do turno
	 */
	public void setTurno(String turno) {
		this.Turno = turno;
	}

	/**
	 * Retorna o nome da turma.
	 *
	 * @return o nome da turma
	 */
	public String getTurma() {
		return Turma;
	}

	/**
	 * Define o nome da turma.
	 *
	 * @param turma o nome da turma
	 */
	public void setTurma(String turma) {
		this.Turma = turma;
	}

	/**
	 * Retorna o número de alunos inscritos no turno.
	 *
	 * @return o número de alunos inscritos no turno
	 */
	public int getInscritosNoTurno() {
		return InscritosNoTurno;
	}

	/**
	 * Define o número de alunos inscritos no turno.
	 * 
	 * @param inscritosNoTurno o número de alunos inscritos no turno
	 */
	public void setInscritosNoTurno(int inscritosNoTurno) {
		this.InscritosNoTurno = inscritosNoTurno;
	}

	/**
	 * Retorna o dia da semana da aula.
	 *
	 * @return o dia da semana da aula
	 */
	public String getdiaDaSemana() {
		return DiaDaSemana;
	}

	/**
	 * Define o dia da semana da aula.
	 * 
	 * @param dia O dia da semana da aula.
	 */
	public void setdiaDaSemana(String dia) {
		this.DiaDaSemana = dia;
	}

	/**
	 * Retorna a hora de início da aula.
	 * 
	 * @return A hora de início da aula.
	 */
	public String getHoraInicio() {
		return HoraInicio.toString();
	}

	/**
	 * Define a hora de início da aula.
	 * 
	 * @param horaInicio A hora de início da aula.
	 */
	public void setHoraInicio(LocalTime horaInicio) {
		this.HoraInicio = horaInicio;
	}

	/**
	 * Retorna a hora de fim da aula.
	 * 
	 * @return A hora de fim da aula.
	 */
	public String getHoraFim() {
		return HoraFim.toString();
	}

	/**
	 * Define a hora de fim da aula.
	 * 
	 * @param horaFim A hora de fim da aula.
	 */
	public void setHoraFim(LocalTime horaFim) {
		this.HoraFim = horaFim;
	}

	/**
	 * Retorna a data da aula.
	 * 
	 * @return A data da aula.
	 */
	public String getData() {
		return Data.toString();
	}

	/**
	 * Define a data da aula.
	 * 
	 * @param data A data da aula.
	 */
	public void setDataAula(LocalDate data) {
		this.Data = data;
	}

	/**
	 * Retorna o nome da sala em que a aula será realizada.
	 * 
	 * @return O nome da sala em que a aula será realizada.
	 */
	public String getSala() {
		return Sala;
	}

	/**
	 * Define o nome da sala em que a aula será realizada.
	 * 
	 * @param sala O nome da sala em que a aula será realizada.
	 */
	public void setSala(String sala) {
		this.Sala = sala;
	}

	/**
	 * Retorna a lotação da sala em que a aula será realizada.
	 * 
	 * @return A lotação da sala em que a aula será realizada.
	 */
	public int getLotacaoDaSala() {
		return LotacaoDaSala;
	}

	/**
	 * Define a lotação da sala em que a aula será realizada.
	 * 
	 * @param lotacaoDaSala A lotação da sala em que a aula será realizada.
	 */
	public void setLotacaoDaSala(int lotacaoDaSala) {
		this.LotacaoDaSala = lotacaoDaSala;
	}
}
