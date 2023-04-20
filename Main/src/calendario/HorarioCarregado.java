package calendario;
import java.util.List;

public class HorarioCarregado {
   private List<Aula> aulas;
   
   public HorarioCarregado(List<Aula> aulas) {
      this.aulas = aulas;
   }
   
   public List<Aula> getAulas() {
      return aulas;
   }
   
   public void setAulas(List<Aula> aulas) {
      this.aulas = aulas;
   }

}