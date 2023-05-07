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
	@JsonProperty("Hora in�cio da aula")
	private LocalTime HoraInicio;
	@JsonProperty("Hora fim da aula")
	private LocalTime HoraFim;
	@JsonProperty("Data da aula")
	@JsonFormat(pattern = "dd/MM/yyyy")
	public LocalDate Data;
	@JsonProperty("Sala atribu�da � aula")
	private String Sala;
	@JsonProperty("Lota��o da sala")
	private int LotacaoDaSala;

	/**
	 * Cria uma nova inst�ncia de Aula com valores padr�o. Os valores padr�o devem
	 * ser definidos posteriormente usando os m�todos setters.
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
	 * Retorna o n�mero de alunos inscritos no turno.
	 *
	 * @return o n�mero de alunos inscritos no turno
	 */
	public int getInscritosNoTurno() {
		return InscritosNoTurno;
	}

	/**
	 * Define o n�mero de alunos inscritos no turno.
	 * 
	 * @param inscritosNoTurno o n�mero de alunos inscritos no turno
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

	public int getdiaDaSemanaInt(String DiaDaSemana){
		switch(DiaDaSemana) {
			case "Seg":
			  return 1;
			case "Ter":
			  return 2;
			case "Qua":
			  return 3;
			case "Qui":
			  return 4;
			case "Sex":
			  return 5;
			default:
			  return 0;
		  }
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
	 * Retorna a hora de in�cio da aula.
	 * 
	 * @return A hora de in�cio da aula.
	 */
	public LocalTime getHoraInicio() {
		return HoraInicio;
	}

	public String getHoraInicioString() {
		return HoraInicio.toString();
	}

	/**
	 * Define a hora de in�cio da aula.
	 * 
	 * @param horaInicio A hora de in�cio da aula.
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
	
	public LocalDate getData() {
		return Data;
	}

	public String getDataString() {
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
	 * Retorna o nome da sala em que a aula ser� realizada.
	 * 
	 * @return O nome da sala em que a aula ser� realizada.
	 */
	public String getSala() {
		return Sala;
	}

	/**
	 * Define o nome da sala em que a aula ser� realizada.
	 * 
	 * @param sala O nome da sala em que a aula ser� realizada.
	 */
	public void setSala(String sala) {
		this.Sala = sala;
	}

	/**
	 * Retorna a lota��o da sala em que a aula ser� realizada.
	 * 
	 * @return A lota��o da sala em que a aula ser� realizada.
	 */
	public int getLotacaoDaSala() {
		return LotacaoDaSala;
	}

	/**
	 * Define a lota��o da sala em que a aula ser� realizada.
	 * 
	 * @param lotacaoDaSala A lota��o da sala em que a aula ser� realizada.
	 */
	public void setLotacaoDaSala(int lotacaoDaSala) {
		this.LotacaoDaSala = lotacaoDaSala;
	}
}
