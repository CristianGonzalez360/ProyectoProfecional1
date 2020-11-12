package business_logic;

import java.util.List;

import dto.DatosPersonalesDTO;
import dto.EntregaDeVehiculoDTO;
import repositories.DatosPersonalesDao;
import repositories.OrdenesDeTrabajoDao;

public class EntregaDeVehiculoController {

	private DatosPersonalesDao datosClienteDao;
	private OrdenesDeTrabajoDao ordenesDeTrabajoDao;
	
	public DatosPersonalesDTO readDatosClienteByDni(Integer dni) {
		return datosClienteDao.readByDni(dni);
	}

	public List<EntregaDeVehiculoDTO> readAll() {
		return null;
	}

	public List<EntregaDeVehiculoDTO> readByDniCliente(Integer dniCliente) {
		// TODO Auto-generated method stub
		return null;
	}

	public void registrarEntregaById(Integer idEntrega) {
		// TODO Auto-generated method stub
		
	}
	
	
}
