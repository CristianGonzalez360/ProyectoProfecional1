package business_logic;

import java.util.ArrayList;
import java.util.List;

import dto.RepuestoDTO;
import repositories.RepuestosDao;

public class RepuestosController {
	
	private RepuestosDao dao;

	public RepuestosController(RepuestosDao dao) {
		this.dao = dao;
	}
	public RepuestoDTO readById(int parseInt) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RepuestoDTO> readAll() {
		return dao.readAll();
	}

}
