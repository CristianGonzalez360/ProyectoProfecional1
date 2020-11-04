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

}
