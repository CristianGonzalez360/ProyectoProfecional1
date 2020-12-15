package services;

import dto.taller.TurnoDTO;

public class Recordatorio {

	static String asunto = "Recordatorio de turno";
	
	public static String getMessage(TurnoDTO turno) {
		return "Hola, "+turno.getNombreCliente()+" enviamos este mail para recordarle del turno del service/taller a realizarse el dia "+turno.getFechaProgramada()+"\nSaludos"+"\nConsecionaria Larusso";
	}
}
