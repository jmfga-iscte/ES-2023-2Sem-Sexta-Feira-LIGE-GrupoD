<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8' />
    <title>Programacao Semanal</title>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.2/fullcalendar.min.css' />
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.2/fullcalendar.min.js'></script>
    <script>
        $(document).ready(function() {
            // Inicializar o calendário
            $('#calendar').fullCalendar({
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,agendaWeek,agendaDay'
                },
                defaultView: 'agendaWeek',
                editable: true,
                events: [
                    // Aqui vão os eventos que você quer exibir na programação
                    {
                        title: 'Evento 1',
                        start: '2023-05-01T10:00:00',
                        end: '2023-05-01T12:00:00'
                    },
                    {
                        title: 'Evento 2',
                        start: '2023-05-03T14:00:00',
                        end: '2023-05-03T16:00:00'
                    }
                ]
            });

            // Adicionar botões para mudar de semana
            $('#prevButton').click(function() {
                $('#calendar').fullCalendar('prev');
            });

            $('#nextButton').click(function() {
                $('#calendar').fullCalendar('next');
            });
        });
    </script>
</head>
<body>
    <h1>Programacao Semanal</h1>
    <form action="UploadServlet" method="post" enctype="multipart/form-data">
        <input type="file" name="file"/>
        <input type="submit" value="Upload"/>
    </form>
    
    
    <div id='calendar'></div>
</body>
</html>