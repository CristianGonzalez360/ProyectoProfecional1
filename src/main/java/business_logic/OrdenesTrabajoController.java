package business_logic;

import java.util.Date;

import dto.AltaOrdenDeTrabajoDTO;
import dto.OrdenDeTrabajoDTO;
import repositories.OrdenesDeTrabajoDao;
import services.SessionService;

public class OrdenesTrabajoController {

	private OrdenesDeTrabajoDao dao;

	private final SessionService service;

	public OrdenesTrabajoController(OrdenesDeTrabajoDao dao, SessionService service) {
		this.dao = dao;
		this.service = service;
	}

	public void save(Integer idVehiculo, AltaOrdenDeTrabajoDTO ordenDeTrabajo) {
		OrdenDeTrabajoDTO dto = new OrdenDeTrabajoDTO(ordenDeTrabajo);
		dto.setFechaDeAlta(new Date());
		dto.setIdVehiculoOt(idVehiculo);
		dto.setIdUsuarioAlta(service.getActiveSession().getIdUsuario());
		dao.insert(dto);
	}
}
