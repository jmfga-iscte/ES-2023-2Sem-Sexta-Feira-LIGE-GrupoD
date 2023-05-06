<%@ page language="java" %>
<%@ page import="horario.HorarioCarregado, horario.Aula" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Horario Semanal</title>
	<style>
		table {
			border-collapse: collapse;
		}



	th, td {
		border: 1px solid black;
		padding: 5px;
	}
</style>

</head>
<body>
	<h1>Horario Semanal</h1>



<form action="Horario" method="POST" enctype="multipart/form-data">
    <input type="file" name="arquivo" /><br />
    <input type="submit" value="Enviar" />
</form>

<%
	HorarioCarregado horarioCarregado = (HorarioCarregado) request.getAttribute("horarioCarregado");
	
	if (horarioCarregado != null) {
		// criar a tabela do horário
		%><table>
			<thead>
				<tr>
					<th></th>
					<th>Segunda</th>
					<th>Terca</th>
					<th>Quarta</th>
					<th>Quinta</th>
					<th>Sexta</th>
				</tr>
			</thead>
			<tbody>
				<% for (int i = 8; i < 22; i++) { // loop pelos horários de 8h às 22h %>
					<tr>
						<td><%= i + ":00" %></td>
						<% for (int j = 1; j <= 5; j++) { // loop pelos dias da semana (segunda a sexta) %>
							<td>
								<% // encontrar as aulas correspondentes a este horário e dia da semana
									List<Aula> aulas = new ArrayList<>();
									for (Aula a : horarioCarregado.getAulas()) {
										if (a.getdiaDaSemanaInt( a.getdiaDaSemana() ) == j && a.getHoraInt(a.getHoraInicio() ) == i) {
											aulas.add(a);
										}
									}
									if (!aulas.isEmpty()) { // se há aulas neste horário e dia da semana, exibir suas informações
										for (Aula aula : aulas) {
								%>
											<p><%= aula.getUnidadeCurricular() %></p>
											<p><%= aula.getTurma() %></p>
											<p><%= aula.getSala() %></p>
										<% }
									} else { // se não há aulas neste horário e dia da semana, exibir uma célula vazia
								%>
										<p></p>
									<% } %>
							</td>
						<% } %>
					</tr>
				<% } %>
			</tbody>
		</table>
	<% } %>

</body>
</html>