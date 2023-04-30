<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Horario</title>
</head>
<body>
	<h1>Horario Semanal</h1>
	<table>
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
			<c:forEach items="${horario.aulas}" var="aula">
				<tr>
					<th>${aula.horaInicio} - ${aula.horaFim}</th>
					<c:forEach var="dia" items="${aula.dias}">
						<td>${dia}</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</tbody>
	</table>
    <form action="Horario" method="POST" enctype="multipart/form-data">
        <input type="file" name="arquivo" /><br />
        <input type="submit" value="Enviar" />
      </form>
      
</body>
</html>