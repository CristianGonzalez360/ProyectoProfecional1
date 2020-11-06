package business_logic;

import java.util.List;

import dto.RepuestoDTO;
import repositories.RepuestosDao;

public class RepuestosController {
	
	private RepuestosDao dao;

	public RepuestosController(RepuestosDao dao) {
		this.dao = dao;
	}
	public RepuestoDTO readById(int idRepuesto) {
		return dao.readByID(idRepuesto);
	}

	public List<RepuestoDTO> readAll() {
		return dao.readAll();
	}

}
