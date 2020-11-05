package business_logic;

import java.util.Date;
import java.util.List;

import business_logic.exceptions.ForbiddenException;
import dto.AltaOrdenDeTrabajoDTO;
import dto.OrdenDeTrabajoDTO;
import repositories.OrdenesDeTrabajoDao;
import services.SessionService;

public class OrdenesTrabajoController {

	private static final String FORBIDDEN_ALTA_OT = "Operación no permitida. El vehículo tiene una orde de trabajo activa.";
	
	private OrdenesDeTrabajoDao dao;

	private final SessionService service;

	public OrdenesTrabajoController(OrdenesDeTrabajoDao dao, SessionService service) {
		this.dao = dao;
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
		dto.setIdUsuarioAlta(service.getActiveSession().getIdUsuario());
		OrdenDeTrabajoDTO aux = dao.readByIdVehiculo(idVehiculo);
		if(aux != null) {
			if(aux.getFechaEntregado() == null) {
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
		return dao.readByIdVehiculo(idVehiculo);
	}
}
