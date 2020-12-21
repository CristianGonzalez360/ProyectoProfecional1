package business_logic;

import java.util.Date;
import java.util.List;
import business_logic.exceptions.ForbiddenException;
import dto.taller.EstadoPresupuesto;
import dto.taller.OrdenDeTrabajoDTO;
import dto.taller.PresupuestoDTO;
import dto.temporal.AltaOrdenDeTrabajoDTO;
import repositories.FacturasDao;
import repositories.OrdenesDeTrabajoDao;
import repositories.PresupuestosDao;
import services.SessionService;

public class OrdenesTrabajoController {

	private static final String FORBIDDEN_ALTA_OT = "Operación no permitida. El vehículo tiene una orde de trabajo activa.";

	private OrdenesDeTrabajoDao dao;
	private PresupuestosDao presDao;

	private final SessionService service;

	public OrdenesTrabajoController(OrdenesDeTrabajoDao dao, SessionService service, FacturasDao facturasDao,
			PresupuestosDao presupuestos) {
		this.dao = dao;
		this.presDao = presupuestos;
		this.service = service;
	}

	public List<OrdenDeTrabajoDTO> readAll() {
		return dao.readAll();
	}

	public void save(Integer idVehiculo, AltaOrdenDeTrabajoDTO ordenDeTrabajo) throws ForbiddenException {
		assert idVehiculo != null;
		assert ordenDeTrabajo != null;
		OrdenDeTrabajoDTO dto = new OrdenDeTrabajoDTO(ordenDeTrabajo);
		dto.setFechaDeAlta(new Date());
		dto.setIdVehiculoOt(idVehiculo);
		dto.setTipoOrdeTrabajo(ordenDeTrabajo.getTipoDeTrabajo());
		dto.setIdUsuarioAlta(service.getActiveSession().getIdUsuario());
		OrdenDeTrabajoDTO aux = dao.readByIdVehiculoConOtNoCerrada(idVehiculo);
		if (aux != null) {
			if (aux.getFechaEntregado() == null) {
				throw new ForbiddenException(FORBIDDEN_ALTA_OT);
			}
		}
		
		dao.insert(dto);
	}

	public OrdenDeTrabajoDTO readByDniCliente(String dni) {
		return dao.readByID(Integer.parseInt(dni));
	}

	public OrdenDeTrabajoDTO readByIdVehiculo(Integer idVehiculo) {
		assert idVehiculo != null;
		return dao.readByIdVehiculoConOtNoCerrada(idVehiculo);
	}

	public boolean esRechazada(Integer ordenDeTrabajoId) {
		List<PresupuestoDTO> presu = presDao.readByOrdenDeTrabajoId(ordenDeTrabajoId);
		int cantPresupuestos = presu.size();
		int cantRechazadas = 0;
		for (PresupuestoDTO temp : presu) {
			if (temp.getEstado().equals(EstadoPresupuesto.RECHAZADO)) {
				cantRechazadas++;
			}
		}
		return cantRechazadas == cantPresupuestos;
	}
	 
	public OrdenDeTrabajoDTO readById(Integer id) {
		return dao.readByID(id);
	}

	public List<OrdenDeTrabajoDTO> readAllByIdVehiculo(Integer idVehiculo) {
		return dao.readAllByIdVehiculo(idVehiculo);
	}
}