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

	public void save(TurnoDTO turnoDTO) {
		assert turnoDTO != null;
		dao.insert(turnoDTO);
	}

	public void update(TurnoDTO turnoDTO) {
		assert turnoDTO != null;
		dao.update(turnoDTO);
	}

	public TurnoDTO readByIdTurno(Integer id) {
		return dao.readByIdTurno(id);
	}

	public List<TurnoDTO> readAllDisponibles() {
		return dao.readAllTurnosDisponibles();
	}

}
