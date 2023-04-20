import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HorarioCarregado {
   private List<Aula> aulas;
   
   public HorarioCarregado(@JsonProperty("aulas") List<Aula> aulas) {
      this.aulas = aulas;
   }
   
  
   public List<Aula> getAulas() {
      return aulas;
   }
   
   public void setAulas(List<Aula> aulas) {
      this.aulas = aulas;
   }

}