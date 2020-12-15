package services;

public class Encuesta {

	static String asunto = "¡Ayudanos a mejorar completando esta breve encuesta!";
	static String encuestaHYML = "<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "<head>\r\n"
			+ "    <meta charset=\"UTF-8\">\r\n"
			+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
			+ "    <title>Document</title>\r\n" + "    <style>\r\n" + "        body { width: 100%;}\r\n"
			+ "        a:link, a:visited, a:active { text-decoration: none; color:black;}\r\n"
			+ "        .contenedor {\r\n" + "            background-color: rgb(49,49,49);\r\n"
			+ "            text-align: center;\r\n" + "            width: 800px;\r\n" + "            height: 400px;\r\n"
			+ "            margin: auto;\r\n" + "        }\r\n" + "        .contenido1 { display: inline-block; }\r\n"
			+ "        .button {\r\n" + "            border: none;\r\n" + "            color: white;\r\n"
			+ "            padding: 16px 32px;\r\n" + "            text-align: center;\r\n"
			+ "            text-decoration: none;\r\n" + "            display: inline-block;\r\n"
			+ "            font-size: 16px;\r\n" + "            margin: 4px 2px;\r\n"
			+ "            transition-duration: 0.4s;\r\n" + "            cursor: pointer;\r\n" + "        }\r\n"
			+ "        .block{\r\n" + "            background-color: rgb(235,236,231);\r\n"
			+ "            margin: 15px;\r\n" + "            padding: 25px;\r\n"
			+ "            border: 1px solid rgb(0, 0, 0);\r\n" + "            text-align: center;\r\n"
			+ "        }\r\n" + "        .button1 {\r\n" + "            background-color: white; \r\n"
			+ "            color: black; \r\n" + "            border: 2px solid #4CAF50;\r\n" + "        }\r\n"
			+ "        .button1:hover { background-color: #4CAF50; color: white; }\r\n"
			+ "        .inferior { background-color:#4CAF50; padding: 5px;}\r\n" + "        .saludo{ color: white;}\r\n"
			+ "    </style>\r\n" + "</head>\r\n" + "<body>\r\n" + "    <div class=\"contenedor\">\r\n"
			+ "        <div class=\"contenido1\">\r\n" + "           <div class=\"block\">\r\n"
			+ "            <h1>¡Ayudanos a mejorar!</h1>\r\n" + "            <div>\r\n"
			+ "                <p style=\"text-align: center;\">Hola,</p>\r\n"
			+ "                <p style=\"text-align: center;\">Por favor, invierte unos pocos minutos de tu tiempo para rellenar el siguiente cuestionario.</p>\r\n"
			+ "            </div>\r\n" + "            <div>\r\n"
			+ "                <button class=\"button button1\"><a href=\"https://www.survio.com/survey/d/P2I4T5A5J9K2N9Q1M\">Iniciar Encuesta Ahora</a></button>\r\n"
			+ "            </div>\r\n" + "        </div>\r\n" + "        <div class=\"inferior\">\r\n"
			+ "            <h2 class=\"saludo\">¡Muchas Gracias!</h2>\r\n" + "        </div>\r\n" + "    </div>\r\n"
			+ "</body>\r\n" + "</html>";
}
