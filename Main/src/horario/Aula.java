package horario;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Representa uma aula associada a um determinado curso, unidade curricular e turno.
 */
public class Aula {
	private String curso;
	private String unidadeCurricular;
	private String turno;
	private String turma;
	private int inscritosNoTurno;
	private String diaDaSemana;
	private LocalTime horaInicio;
	private LocalTime horaFim;
	private LocalDate data;
	private String sala;
	private int lotacaoDaSala;

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
		return curso;
	}

	/**
	 * Define o nome do curso.
	 *
	 * @param curso o nome do curso
	 */
	public void setCurso(String curso) {
		this.curso = curso;
	}

	/**
	 * Retorna o nome da unidade curricular.
	 *
	 * @return o nome da unidade curricular
	 */
	public String getUnidadeCurricular() {
		return unidadeCurricular;
	}

	/**
	 * Define o nome da unidade curricular.
	 *
	 * @param unidadeCurricular o nome da unidade curricular
	 */
	public void setUnidadeCurricular(String unidadeCurricular) {
		this.unidadeCurricular = unidadeCurricular;
	}

	/**
	 * Retorna o nome do turno.
	 *
	 * @return o nome do turno
	 */
	public String getTurno() {
		return turno;
	}

	/**
	 * Define o nome do turno.
	 *
	 * @param turno o nome do turno
	 */
	public void setTurno(String turno) {
		this.turno = turno;
	}

	/**
	 * Retorna o nome da turma.
	 *
	 * @return o nome da turma
	 */
	public String getTurma() {
		return turma;
	}

	/**
	 * Define o nome da turma.
	 *
	 * @param turma o nome da turma
	 */
	public void setTurma(String turma) {
		this.turma = turma;
	}

	/**
	 * Retorna o número de alunos inscritos no turno.
	 *
	 * @return o número de alunos inscritos no turno
	 */
	public int getInscritosNoTurno() {
		return inscritosNoTurno;
	}

	/**
	 * Define o número de alunos inscritos no turno.
	 * 
	 * @param inscritosNoTurno o número de alunos inscritos no turno
	 */
	public void setInscritosNoTurno(int inscritosNoTurno) {
		this.inscritosNoTurno = inscritosNoTurno;
	}

	/**
	 * Retorna o dia da semana da aula.
	 *
	 * @return o dia da semana da aula
	 */
	public String getdiaDaSemana() {
		return diaDaSemana;
	}

	/**
	 * Define o dia da semana da aula.
	 * 
	 * @param dia O dia da semana da aula.
	 */
	public void setdiaDaSemana(String dia) {
		this.diaDaSemana = dia;
	}

	/**
	 * Retorna a hora de início da aula.
	 * 
	 * @return A hora de início da aula.
	 */
	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	/**
	 * Define a hora de início da aula.
	 * 
	 * @param horaInicio A hora de início da aula.
	 */
	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	/**
	 * Retorna a hora de fim da aula.
	 * 
	 * @return A hora de fim da aula.
	 */
	public LocalTime getHoraFim() {
		return horaFim;
	}

	/**
	 * Define a hora de fim da aula.
	 * 
	 * @param horaFim A hora de fim da aula.
	 */
	public void setHoraFim(LocalTime horaFim) {
		this.horaFim = horaFim;
	}

	/**
	 * Retorna a data da aula.
	 * 
	 * @return A data da aula.
	 */
	public LocalDate getdData() {
		return data;
	}

	/**
	 * Define a data da aula.
	 * 
	 * @param data A data da aula.
	 */
	public void setDataAula(LocalDate data) {
		this.data = data;
	}

	/**
	 * Retorna o nome da sala em que a aula será realizada.
	 * 
	 * @return O nome da sala em que a aula será realizada.
	 */
	public String getSala() {
		return sala;
	}

	/**
	 * Define o nome da sala em que a aula será realizada.
	 * 
	 * @param sala O nome da sala em que a aula será realizada.
	 */
	public void setSala(String sala) {
		this.sala = sala;
	}

	/**
	 * Retorna a lotação da sala em que a aula será realizada.
	 * 
	 * @return A lotação da sala em que a aula será realizada.
	 */
	public int getLotacaoDaSala() {
		return lotacaoDaSala;
	}

	/**
	 * Define a lotação da sala em que a aula será realizada.
	 * 
	 * @param lotacaoDaSala A lotação da sala em que a aula será realizada.
	 */
	public void setLotacaoDaSala(int lotacaoDaSala) {
		this.lotacaoDaSala = lotacaoDaSala;
	}
}
