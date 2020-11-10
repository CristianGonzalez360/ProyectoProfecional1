package business_logic;

import java.util.LinkedList;
import java.util.List;

import business_logic.exceptions.ConflictException;
import dto.AltaDeVehiculoDTO;
import dto.FichaTecnicaVehiculoDTO;
import dto.OrdenDeTrabajoDTO;
import dto.VehiculoConOrdenDeTrabajoDTO;
import repositories.FichaTecnicaVehiculoDao;
import repositories.OrdenesDeTrabajoDao;
import repositories.VehiculosConOrdenDeTrabajoDao;

public class VehiculosController {

	private VehiculosConOrdenDeTrabajoDao vehiculosDao;

	private FichaTecnicaVehiculoDao fichasDao;

	private OrdenesDeTrabajoDao otDao;

	public VehiculosController(VehiculosConOrdenDeTrabajoDao vehiculosDao, OrdenesDeTrabajoDao otDao,
			FichaTecnicaVehiculoDao fichasDao) {
		assert vehiculosDao != null;
		assert fichasDao != null;
		this.vehiculosDao = vehiculosDao;
		this.fichasDao = fichasDao;
		this.otDao = otDao;
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
		FichaTecnicaVehiculoDTO ficha = new FichaTecnicaVehiculoDTO(vehiculoDeAlta);
		if (fichasDao.readByNroMotor(Integer.parseInt(vehiculoDeAlta.getNroMotor())) != null)
			throw new ConflictException("El nro. de motor está en uso.");
		if (vehiculosDao.readByPatente(vehiculoDeAlta.getPatente()) != null)
			throw new ConflictException("La patente ya está registrada");
		if (fichasDao.insert(ficha)) {
			VehiculoConOrdenDeTrabajoDTO target = new VehiculoConOrdenDeTrabajoDTO(vehiculoDeAlta);
			target.setIdCliente(idCliente);
			target.setIdFichaTecnica(fichasDao.readByNroMotor(Integer.parseInt(vehiculoDeAlta.getNroMotor())).getId());
			vehiculosDao.insert(target);
		} else {
			throw new ConflictException("Error al persistir el vehiculo del cliente");
		}
	}

	public List<VehiculoConOrdenDeTrabajoDTO> readVehicleWithClientIdWhereOtIsOpen(Integer idCliente) {
		List<VehiculoConOrdenDeTrabajoDTO> vCliente = vehiculosDao.readByClienteId(idCliente);
		List<VehiculoConOrdenDeTrabajoDTO> vClienteRet = new LinkedList<>();
		for (VehiculoConOrdenDeTrabajoDTO aux : vCliente) {
			for (OrdenDeTrabajoDTO temp : otDao.readByVehiculoId(aux.getId())) {
				if (temp != null) {
					if (temp.getFechaEntregado() == null) {
						vClienteRet.add(aux);
					}
				}
			}
		}
		return vClienteRet;
	}
}