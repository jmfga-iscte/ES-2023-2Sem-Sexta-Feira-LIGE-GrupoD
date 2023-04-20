import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
public class Horario {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static void carregar(String caminho) throws IOException {
        String extensao = caminho.substring(caminho.lastIndexOf('.') + 1);
        if (extensao.equalsIgnoreCase("json")) {
            carregarJson(caminho);
        } else if (extensao.equalsIgnoreCase("csv")) {
            carregarCsv(caminho);
        } else {
            throw new IllegalArgumentException("Formato de arquivo inválido: " + extensao);
        }
    }

	public static HorarioCarregado carregarCsv(String caminhoArquivo) throws IOException {
        List<String> linhas = Files.readAllLines(Paths.get(caminhoArquivo));
        if (linhas.isEmpty()) {
            throw new IllegalArgumentException("O arquivo está vazio.");
        }
        List<Aula> aulas = new ArrayList<>();
        HorarioCarregado horario = new HorarioCarregado(aulas);
    
        // Descarta a primeira linha (cabeçalho)
        linhas.remove(0);
    
        for (String linha : linhas) {
            String[] valores = linha.split(";");
            
            if (valores.length > 11) {
                throw new IllegalArgumentException("O arquivo está mal formatado.");
            }
            else if (valores.length < 11) {
                String[] valoresPreenchidos = new String[11];
                for (int i = 0; i < valores.length; i++) {
                    valoresPreenchidos[i] = valores[i].trim();
                }
                for (int i = valores.length; i < 11; i++) {
                    valoresPreenchidos[i] = "";
                }
                valores = valoresPreenchidos;
            }

            Aula aula = new Aula();
            aula.setCurso(valores[0]);
            aula.setUnidadeCurricular(valores[1]);
            aula.setTurno(valores[2]);
            aula.setTurma(valores[3]);
            aula.setInscritosNoTurno(Integer.parseInt(valores[4]));
            aula.setdiaDaSemana(valores[5]);
            aula.setHoraInicio(valores[6].isEmpty() ? null : LocalTime.parse(valores[6]));
            aula.setHoraFim(valores[7].isEmpty() ? null :LocalTime.parse(valores[7]));
            aula.setDataAula(valores[8].isEmpty() ? null : LocalDate.parse(valores[8], DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            aula.setSala(valores[9].isEmpty() ? null :valores[9]);
            aula.setLotacaoDaSala(valores[10].isEmpty() ? 0 : Integer.parseInt(valores[10]));

            aulas.add(aula);
        }
        horario.setAulas(aulas);
        return horario;
    }

    public static HorarioCarregado carregarJson(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
        	throw new FileNotFoundException("Arquivo não encontrado: " + filePath);
        }
        return objectMapper.readValue(file, HorarioCarregado.class); // ler objeto HorarioCarregado a partir do arquivo JSON
    }

//    private void gravarCsv(){
//
//    }
//
//    private void gravarJson(){
//
//    }
//    
//    private void converterFicheiro() {
//    	
//    }
    
    public static void main(String[] args) throws IOException {
//    	carregar("C:/users/Diogo/Desktop/a/horario_exemplo.csv");
//    	carregar("E:\\IGE\\3�Ano_2�Semestre\\EngenhariaSoftware\\Exemplos_Ficheiro\\horario_exemplo.csv");
    }
}
