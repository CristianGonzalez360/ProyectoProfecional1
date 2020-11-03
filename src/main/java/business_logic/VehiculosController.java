package business_logic;

import java.util.List;

import dto.FichaTecnicaVehiculoDTO;
import dto.VehiculoConOrdenDeTrabajoDTO;
import repositories.FichaTecnicaVehiculoDao;
import repositories.VehiculosConOrdenDeTrabajoDao;

public class VehiculosController {

	public VehiculosConOrdenDeTrabajoDao vehiculosDao;
	
	public FichaTecnicaVehiculoDao fichasDao;
	
	public VehiculosController(VehiculosConOrdenDeTrabajoDao vehiculosDao, FichaTecnicaVehiculoDao fichasDao) {
		assert vehiculosDao != null;
		assert fichasDao != null;
		this.vehiculosDao = vehiculosDao;
		this.fichasDao = fichasDao;
	}

	public List<VehiculoConOrdenDeTrabajoDTO> readByIdCliente(Integer idCliente) {
		assert idCliente != null;
		return vehiculosDao.readByClienteId(idCliente);
	}
	
	public FichaTecnicaVehiculoDTO readFichaTecnicaById(Integer idFichaTecnica) {
		assert idFichaTecnica != null;
		return fichasDao.readByID(idFichaTecnica);
	}
}
