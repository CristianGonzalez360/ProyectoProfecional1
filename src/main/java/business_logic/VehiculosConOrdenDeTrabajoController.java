package business_logic;

import java.util.LinkedList;
import java.util.List;

import business_logic.exceptions.ConflictException;
import dto.taller.FichaTecnicaVehiculoDTO;
import dto.taller.OrdenDeTrabajoDTO;
import dto.taller.IngresoOrdenDeTrabajoDTO;
import dto.temporal.AltaDeVehiculoDTO;
import repositories.FichaTecnicaVehiculoDao;
import repositories.OrdenesDeTrabajoDao;
import repositories.IngresoOrdenDeTrabajoDao;

public class VehiculosConOrdenDeTrabajoController {

	private IngresoOrdenDeTrabajoDao vehiculosDao;

	private FichaTecnicaVehiculoDao fichasDao;

	private OrdenesDeTrabajoDao otDao;

	public VehiculosConOrdenDeTrabajoController(IngresoOrdenDeTrabajoDao vehiculosDao, OrdenesDeTrabajoDao otDao,
			FichaTecnicaVehiculoDao fichasDao) {
		assert vehiculosDao != null;
		assert fichasDao != null;
		this.vehiculosDao = vehiculosDao;
		this.fichasDao = fichasDao;
		this.otDao = otDao;
	}

	public List<IngresoOrdenDeTrabajoDTO> readVehiculosConFichaTecnicaByIdCliente(Integer idCliente) {
		assert idCliente != null;
		List<IngresoOrdenDeTrabajoDTO> ret = new LinkedList<>();
		for(IngresoOrdenDeTrabajoDTO temp : vehiculosDao.readByClienteId(idCliente)) {
			if(temp.getIdFichaTecnica() != null) {
				ret.add(temp);
			}
		}
		return ret;
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
			IngresoOrdenDeTrabajoDTO target = new IngresoOrdenDeTrabajoDTO(vehiculoDeAlta);
			target.setIdCliente(idCliente);
			target.setIdFichaTecnica(fichasDao.readByNroMotor(Integer.parseInt(vehiculoDeAlta.getNroMotor())).getId());
			vehiculosDao.insert(target);
		} else {
			throw new ConflictException("Error al persistir el vehiculo del cliente");
		}
	}

	public List<IngresoOrdenDeTrabajoDTO> readVehicleWithClientIdWhereOtIsOpen(Integer idCliente) {
		List<IngresoOrdenDeTrabajoDTO> vCliente = vehiculosDao.readByClienteId(idCliente);
		List<IngresoOrdenDeTrabajoDTO> vClienteRet = new LinkedList<>();
		for (IngresoOrdenDeTrabajoDTO aux : vCliente) {
			for (OrdenDeTrabajoDTO temp : otDao.readByVehiculoId(aux.getId())) {
				if (temp != null) {
					if (temp.getFechaEntregado() == null) {
						String patente = fichasDao.readByID(aux.getIdFichaTecnica()).getPatente();
						aux.setPatente(patente);
						vClienteRet.add(aux);
					}
				}
			}
		}
		return vClienteRet;
	}

	public IngresoOrdenDeTrabajoDTO readById(Integer idVehiculo) {
		return vehiculosDao.readByID(idVehiculo);
	}
}