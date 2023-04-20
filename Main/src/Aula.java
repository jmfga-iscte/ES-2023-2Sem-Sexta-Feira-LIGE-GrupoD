import java.time.LocalDate;
import java.time.LocalTime;

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


    public Aula(){
      // TODO document why this constructor is empty - "Usamos o setter para definir os valores, seria também um erro usar um construtor com 11 parâmetros
    }
    

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getUnidadeCurricular() {
        return unidadeCurricular;
    }

    public void setUnidadeCurricular(String unidadeCurricular) {
        this.unidadeCurricular = unidadeCurricular;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public int getInscritosNoTurno() {
        return inscritosNoTurno;
    }

    public void setInscritosNoTurno(int inscritosNoTurno) {
        this.inscritosNoTurno = inscritosNoTurno;
    }

    public String getdiaDaSemana() {
        return diaDaSemana;
    }

    public void setdiaDaSemana(String dia) {
        this.diaDaSemana = dia;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public LocalDate getdData() {
        return data;
    }

    public void setDataAula(LocalDate data) {
        this.data = data;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public int getLotacaoDaSala() {
        return lotacaoDaSala;
    }

    public void setLotacaoDaSala(int lotacaoDaSala) {
        this.lotacaoDaSala = lotacaoDaSala;
    }

    
}