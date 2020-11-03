package business_logic;

import java.util.List;

import dto.FichaTecnicaVehiculoDTO;
import dto.VehiculoConOrdenDeTrabajoDTO;
import repositories.FichaTecnicaVehiculoDao;
import repositories.VehiculosConOrdenDeTrabajoDao;

public class VehiculosController {

	public VehiculosConOrdenDeTrabajoDao dao;
	
	public FichaTecnicaVehiculoDao fichasDao;
	
	public VehiculosController(VehiculosConOrdenDeTrabajoDao vehiculosConOrdenDeTrabajoDao) {
		super();
		this.dao = vehiculosConOrdenDeTrabajoDao;
	}

	public List<VehiculoConOrdenDeTrabajoDTO> readByClienteId(Integer idCliente) {
		assert idCliente != null;
		return dao.readByClienteId(idCliente);
	}
	
	public FichaTecnicaVehiculoDTO readFichaTecnicaById(Integer idFichaTecnica) {
		assert idFichaTecnica != null;
		return fichasDao.readByID(idFichaTecnica);
	}
}
