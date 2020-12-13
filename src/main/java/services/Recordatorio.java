package services;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Recordatorio {

	static String asunto = "Recordatorio de turno";
	static String fechaHoy = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
	static String recordatorioHYML = "Hola, enviamos este mail para recordarle del turno del service/taller el dia "+fechaHoy+"\nSaludos"+"\nConsecionaria Larusso";

}
