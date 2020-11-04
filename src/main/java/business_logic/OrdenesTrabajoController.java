package business_logic;

import java.util.Date;
import java.util.List;

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

	public List<OrdenDeTrabajoDTO> readAll() {
		return dao.readAll();
	}

	public void save(Integer idVehiculo, AltaOrdenDeTrabajoDTO ordenDeTrabajo) {
		OrdenDeTrabajoDTO dto = new OrdenDeTrabajoDTO(ordenDeTrabajo);
		dto.setFechaDeAlta(new Date());
		dto.setIdVehiculoOt(idVehiculo);
		dto.setIdUsuarioAlta(service.getActiveSession().getIdUsuario());
		dao.insert(dto);
	}

	public OrdenDeTrabajoDTO readByDniCliente(String dni) {
		return dao.readByID(Integer.parseInt(dni));
	}
}
