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
	public List<RepuestoDTO> readByMarca(String marca) {
		return dao.readByMarca(marca);
	}
	public List<RepuestoDTO> readByDescripcion(String descripción) {
		return dao.readByDescripcion(descripción);
	}
	public List<RepuestoDTO> readbyMarcaYDescripcion(String marca, String descripcion) {
		return dao.readByMarcaYDescripcion(marca, descripcion);
	}
	public List<String> readMarcas() {
		return dao.readMarcas();
	}
	public RepuestoDTO readByCodigo(Integer codigo) {
		return dao.readByCodigo(codigo);
	}
	
	public void saveByCodigo(RepuestoDTO nuevoRepuesto) {
		
		if(dao.readByCodigo(nuevoRepuesto.getCodigoRepuesto()) != null) {
			dao.updateByCodigo(nuevoRepuesto);
		}else {
			dao.insert(nuevoRepuesto);
		}
	}
	
	public void updateByCodigo(RepuestoDTO repuesto) {
		dao.updateByCodigo(repuesto);
	}
}
