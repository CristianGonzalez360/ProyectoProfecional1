package business_logic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import dto.taller.TurnoDTO;
import services.EmailSenderService;

public class RecordadorDeTurnos {
	private static TurnosController turnosController;
	private static EmailSenderService servicio;
	
	public RecordadorDeTurnos(TurnosController turnosController) {
		RecordadorDeTurnos.turnosController = turnosController;
		RecordadorDeTurnos.servicio = new EmailSenderService();
		
		enviarRecordatorios();
	}
	
	public static void enviarRecordatorios() {
		List<TurnoDTO> turnos = turnosController.readAll();
		Calendar hoy = Calendar.getInstance();
		hoy.add(Calendar.DATE, 2);//sumo 2 dias
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fechaHoy = sdf.format(hoy.getTime());
		
		for (TurnoDTO turno : turnos) {
			if(turno.getFechaProgramada().toString().equals(fechaHoy)) {
				String correoDestinatario = turno.getEmailCliente();
				if(correoDestinatario!=null)
					servicio.enviarMailRecordatorio(correoDestinatario);
			}
		}
	}
}
