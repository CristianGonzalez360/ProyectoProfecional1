package services;

import java.util.Date;

import dto.taller.TurnoDTO;

public class Recordatorio {

	static String asunto = "Recordatorio de turno";

	public static String getMessage(TurnoDTO turno) {
		return "Hola, " + turno.getNombreCliente()
				+ " enviamos este mail para recordarle del turno del service/taller a realizarse el dia "
				+ turno.getFechaProgramada() + "\nSaludos" + "\nConsecionaria Larusso";
	}

	public static String getMensajeRecordatorio(TurnoDTO turno) {
		String nombreCompletoCliente = String.format("%s %s", turno.getNombreCliente(), turno.getApellidoCliente());
		Date fechaTurno = turno.getFechaProgramada();
		return "<!DOCTYPE html><html lang=\"en\">\r\n" + "<head>\r\n" + "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Document</title>  \r\n" + "    <style>\r\n" + "        body { width: 100%;}\r\n"
				+ "        a:link, a:visited, a:active { text-decoration: none; color:black;}\r\n"
				+ "        .contenedor {\r\n" + "            background-color: rgb(49,49,49);\r\n"
				+ "            text-align: center;\r\n" + "            width: 800px;\r\n"
				+ "            height: 400px;\r\n" + "            margin: auto; }\r\n"
				+ "        .contenido1 { display: inline-block; }\r\n" + "        .button {\r\n"
				+ "            border: none;\r\n" + "            color: white;\r\n"
				+ "            padding: 16px 32px;\r\n" + "            text-align: center;\r\n"
				+ "            text-decoration: none;\r\n" + "            display: inline-block;\r\n"
				+ "            font-size: 16px;\r\n" + "            margin: 4px 2px;\r\n"
				+ "            transition-duration: 0.4s;\r\n" + "            cursor: pointer; }\r\n"
				+ "        .block{\r\n" + "            background-color: rgb(235,236,231);\r\n"
				+ "            margin: 15px;\r\n" + "            padding: 25px;\r\n"
				+ "            border: 1px solid rgb(0, 0, 0);\r\n" + "            text-align: center;\r\n"
				+ "        }\r\n"
				+ "        .button1 {background-color: white;color: black;border: 2px solid #4CAF50;\r\n"
				+ "        }\r\n" + "        .button1:hover {background-color: #4CAF50;color: white;\r\n"
				+ "        }\r\n" + "        .inferior {background-color:#4CAF50;padding: 5px;}\r\n"
				+ "        .saludo{ color: white;}\r\n" + "    </style>\r\n" + "</head>\r\n" + "<body>\r\n"
				+ "    <div class=\"contenedor\">\r\n" + "        <div class=\"contenido1\">\r\n"
				+ "            <div class=\"block\">\r\n" + "            <h1>¡Recordatio de Turno!</h1>\r\n"
				+ "            <div>\r\n" + "                <p style=\"text-align: center;\">Hola, "
				+ nombreCompletoCliente + " </p>\r\n"
				+ "                <P style=\"text-align: center;\">Este es un mail automático para recordarle el turno del service/taller a realizarse el dia "
				+ fechaTurno + "</P>\r\n" + "                <P style=\"text-align: center;\">Saludos</P>\r\n"
				+ "                <P style=\"text-align: center;\">Concesionario Larusso</P>\r\n"
				+ "            </div>\r\n" + "            <div>\r\n" + "            </div> \r\n" + "        </div>\r\n"
				+ "        <div class=\"inferior\">\r\n" + "            <h2 class=\"saludo\">¡Muchas Gracias!</h2> \r\n"
				+ "        </div>\r\n" + "    </div>\r\n" + "</body></html>";
	}
}
