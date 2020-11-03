package business_logic;

import java.util.List;

import business_logic.exceptions.ConflictException;
import dto.AltaDeVehiculoDTO;
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

	public void save(Integer idCliente, AltaDeVehiculoDTO vehiculoDeAlta) {
		FichaTecnicaVehiculoDTO ficha =  new FichaTecnicaVehiculoDTO(vehiculoDeAlta);
		if(fichasDao.readByNroMotor(Integer.parseInt(vehiculoDeAlta.getNroMotor())) != null) throw new ConflictException("El nro. de motor está en uso.");
		if(vehiculosDao.readByPatente(vehiculoDeAlta.getPatente()) != null) throw new ConflictException("La patente ya está registrada");
		if(fichasDao.insert(ficha)) {
			VehiculoConOrdenDeTrabajoDTO target = new VehiculoConOrdenDeTrabajoDTO(vehiculoDeAlta);
			target.setIdCliente(idCliente);
			target.setIdFichaTecnica(fichasDao.readByNroMotor(Integer.parseInt(vehiculoDeAlta.getNroMotor())).getId());
			vehiculosDao.insert(target);
		} else {
			throw new ConflictException("Error al persistir el vehiculo del cliente");
		}
	}
}
