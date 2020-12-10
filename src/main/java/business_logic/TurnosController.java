package business_logic;

import java.util.Date;
import java.util.List;

import dto.taller.TurnoDTO;
import dto.temporal.AltaDeTurnoDTO;
import repositories.TurnosDao;

public class TurnosController {

	private TurnosDao dao;

	public TurnosController(TurnosDao dao) {
		assert dao != null;
		this.dao = dao;
	}

	public List<TurnoDTO> readAll() {
		return dao.readAllTurnosDisponibles();
	}

	public List<TurnoDTO> readByDniCliente(Integer dni) {
		return dao.readAllByDNI(dni);
	}

	public void save(AltaDeTurnoDTO turno) {
		Integer dniCliente = Integer.parseInt(turno.getDniCliente());
		TurnoDTO nuevoTurno = new TurnoDTO(dniCliente, turno.getFechaAlta(), turno.getFechaCancelado(),
				turno.getFechaProgramada(), turno.getNombreCliente(), turno.getApellidoCliente(),
				turno.getTelefonoCliente(), turno.getEmailCliente());
		dao.insert(nuevoTurno);
	}

	public void cancelById(Integer idTurno) {
		assert idTurno != null;
		TurnoDTO turnoSeleccionado = dao.readByID(idTurno);
		turnoSeleccionado.setFechaCancelado(new Date());
		dao.update(turnoSeleccionado);
	}

	public  List<TurnoDTO> readCantidadDeTurnos(Date fechaProgramada) {
		return dao.readAllByFechaProgramada(fechaProgramada);
	}
}
