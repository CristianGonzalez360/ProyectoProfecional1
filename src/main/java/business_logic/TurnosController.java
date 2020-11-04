package business_logic;

import java.util.List;

import dto.TurnoDTO;
import repositories.TurnosDao;

public class TurnosController {

	private TurnosDao dao;

	public TurnosController(TurnosDao dao) {
		this.dao = dao;
	}

	public List<TurnoDTO> readAll() {
		return dao.readAll();
	}

	public TurnoDTO readByDniCliente(String dni) {
		return dao.readByDni(Integer.parseInt(dni));
	}

	/*
	 * DUDA: Hay que verificar si hay un idCliente para no dejar NULL?
	 * POR AHORA: TurnosDaoImpl.java : param(entity.getIdCliente() == null ? new NullObject() : entity.getIdCliente())
	 */
	public void save(TurnoDTO turnoDTO) {
		assert turnoDTO != null;
//		TurnoDTO target = dao.readByDni(turnoDTO.getDniCliente());
//
//		if (target != null) {
//			Integer idCliente = target.getIdCliente();
//			String nombreCliente = target.getNombreCliente();
//
//			turnoDTO.setIdCliente(idCliente);
//			turnoDTO.setNombreCliente(nombreCliente);
//		}
		dao.insert(turnoDTO);
	}

}
