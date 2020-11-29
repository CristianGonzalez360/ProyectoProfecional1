package business_logic;

import java.util.LinkedList;
import java.util.List;

import business_logic.exceptions.ConflictException;
import dto.VehiculoDTO;
import dto.VehiculoParaVentaDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
import dto.taller.OrdenDeTrabajoDTO;
import dto.taller.VehiculoConOrdenDeTrabajoDTO;
import dto.temporal.AltaDeVehiculoDTO;
import dto.temporal.ConsultaVehiculoParaVentaDTO;
import dto.temporal.OutputConsultaVehiculoEnVentaDTO;
import repositories.FichaTecnicaVehiculoDao;
import repositories.OrdenesDeTrabajoDao;
import repositories.VehiculoDao;
import repositories.VehiculosConOrdenDeTrabajoDao;
import repositories.VentaVehiculoDao;

public class VehiculosController {

	private VehiculosConOrdenDeTrabajoDao vehiculosDao;

	private FichaTecnicaVehiculoDao fichasDao;

	private OrdenesDeTrabajoDao otDao;
	
	private VehiculoDao vehiculoDao;
	
	private VentaVehiculoDao ventaVehiculoDao;
	
	public VehiculosController(VehiculosConOrdenDeTrabajoDao vehiculosDao, OrdenesDeTrabajoDao otDao,
			FichaTecnicaVehiculoDao fichasDao, VehiculoDao vehiculoDao) {
		assert vehiculosDao != null;
		assert fichasDao != null;
		this.vehiculosDao = vehiculosDao;
		this.fichasDao = fichasDao;
		this.otDao = otDao;
		this.vehiculoDao = vehiculoDao;
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
		for (VehiculoDTO vehiculo : ret) {
			FichaTecnicaVehiculoDTO ficha = fichasDao.readByID(vehiculo.getIdFichaTecnica());
			vehiculo.setFichaTecnica(ficha);
		}
		return ret;
	}

	public List<OutputConsultaVehiculoEnVentaDTO> readByCriteria(ConsultaVehiculoParaVentaDTO consulta) {
		List<OutputConsultaVehiculoEnVentaDTO> vehiculos = new LinkedList<>();
		OutputConsultaVehiculoEnVentaDTO dto = new OutputConsultaVehiculoEnVentaDTO();
		VehiculoParaVentaDTO temp = new VehiculoParaVentaDTO().makeTestDTO();
		dto.setMarca(temp.getMarca());
		dto.setCodigo("11123");
		dto.setFamilia(temp.getFamilia());
		dto.setLinea(temp.getLinea());
		dto.setPrecio(temp.getCaracteristicas().getPrecio());
		dto.setCilindrada(temp.getCaracteristicas().getCilindrada());
		dto.setColor(temp.getColorVehiculo());
		vehiculos.add(dto);
		return vehiculos;
	}

	public VehiculoParaVentaDTO readByCodigo(Integer codigoVehiculo) {
		return new VehiculoParaVentaDTO().makeTestDTO();
	}
}