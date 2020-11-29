package business_logic;

import java.util.LinkedList;
import java.util.List;

import business_logic.exceptions.ConflictException;
import dto.AltaDeVehiculoDTO;
import dto.CaracteristicasVehiculoDTO;
import dto.ConsultaVehiculoDTO;
import dto.FichaTecnicaVehiculoDTO;
import dto.OrdenDeTrabajoDTO;
import dto.SucursalDTO;
import dto.VehiculoConOrdenDeTrabajoDTO;
import dto.VehiculoDTO;
import repositories.CaracteristicasVehiculoDao;
import repositories.FichaTecnicaVehiculoDao;
import repositories.OrdenesDeTrabajoDao;
import repositories.SucursalDao;
import repositories.VehiculoDao;
import repositories.VehiculosConOrdenDeTrabajoDao;

public class VehiculosController {

	private VehiculosConOrdenDeTrabajoDao vehiculosDao;

	private FichaTecnicaVehiculoDao fichasDao;

	private OrdenesDeTrabajoDao otDao;
	
	private VehiculoDao vehiculoDao;
	
	private CaracteristicasVehiculoDao caracteristicasVehiculoDao;
	
	public VehiculosController(VehiculosConOrdenDeTrabajoDao vehiculosDao, OrdenesDeTrabajoDao otDao,
			FichaTecnicaVehiculoDao fichasDao, VehiculoDao vehiculoDao, CaracteristicasVehiculoDao caractDao) {
		assert vehiculosDao != null;
		assert fichasDao != null;
		this.vehiculosDao = vehiculosDao;
		this.fichasDao = fichasDao;
		this.otDao = otDao;
		this.vehiculoDao = vehiculoDao;
		this.caracteristicasVehiculoDao = caractDao;
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
						String patente = fichasDao.readByID(aux.getIdFichaTecnica()).getPatente();
						aux.setPatente(patente);
						vClienteRet.add(aux);
					}
				}
			}
		}
		return vClienteRet;
	}
	
	public List<VehiculoDTO> readVehiculosNuevosDisponibles() {
		List<VehiculoDTO> ret = vehiculoDao.readNuevosDisponibles();
		
		return ret;
	}

	public List<VehiculoDTO> readByCriteria(ConsultaVehiculoDTO consulta) {
		List<VehiculoDTO> vehiculos = vehiculoDao.readByCriteria(consulta);
		for (VehiculoDTO vehiculo : vehiculos) {
			CaracteristicasVehiculoDTO caracteristicas = caracteristicasVehiculoDao.readByID(vehiculo.getIdCaracteristicas());
			vehiculo.setCaracteristicasVehiculo(caracteristicas);
		}
		return vehiculos;
	}
}