package business_logic;

import java.util.Date;
import java.util.List;
import java.util.Map;

import business_logic.exceptions.ForbiddenException;
import business_logic.exceptions.NotFoundException;
import dto.AltaOrdenDeTrabajoDTO;
import dto.EstadoPresupuesto;
import dto.FacturaDTO;
import dto.OrdenDeTrabajoDTO;
import dto.PresupuestoDTO;
import repositories.FacturasDao;
import repositories.OrdenesDeTrabajoDao;
import repositories.PresupuestosDao;
import services.SessionService;

public class OrdenesTrabajoController {

	private static final String FORBIDDEN_CAMBIO_ESTADO = "No puede editar el estado del presupuesto.";
	
	private static final String FORBIDDEN_ALTA_OT = "Operación no permitida. El vehículo tiene una orde de trabajo activa.";

	private OrdenesDeTrabajoDao dao;

	private FacturasDao facturaDao;

	private PresupuestosDao presDao;
	
	private final SessionService service;

	public OrdenesTrabajoController(OrdenesDeTrabajoDao dao, SessionService service, FacturasDao facturasDao, PresupuestosDao presupuestos) {
		this.dao = dao;
		this.service = service;
		this.facturaDao = facturasDao;
		this.presDao = presupuestos;
	}

	public List<OrdenDeTrabajoDTO> readAll() {
		return dao.readAll();
	}

	public void save(Integer idVehiculo, AltaOrdenDeTrabajoDTO ordenDeTrabajo) throws ForbiddenException {
		assert idVehiculo != null;
		assert ordenDeTrabajo != null;
		OrdenDeTrabajoDTO dto = new OrdenDeTrabajoDTO(ordenDeTrabajo);
		dto.setFechaDeAlta(new Date());
		dto.setIdVehiculoOt(idVehiculo);
		dto.setIdUsuarioAlta(service.getActiveSession().getIdUsuario());
		OrdenDeTrabajoDTO aux = dao.readByIdVehiculoConOtNoCerrada(idVehiculo);
		if (aux != null) {
			if (aux.getFechaEntregado() == null) {
				throw new ForbiddenException(FORBIDDEN_ALTA_OT);
			}
		}
		dao.insert(dto);
	}

	public OrdenDeTrabajoDTO readByDniCliente(String dni) {
		return dao.readByID(Integer.parseInt(dni));
	}

	public OrdenDeTrabajoDTO readByIdVehiculo(Integer idVehiculo) {
		assert idVehiculo != null;
		return dao.readByIdVehiculoConOtNoCerrada(idVehiculo);
	}

	public FacturaDTO getFactura(Integer id) {
		assert id != null;
		return facturaDao.readByOrdenDeTrabajoId(id);
	}
	
	public void generarFactura(Map<Integer, Boolean> presupuestos) throws ForbiddenException {
		assert presupuestos != null;
		assert !presupuestos.isEmpty();
		presupuestos.forEach((k, v) -> {
			PresupuestoDTO presupuesto = presDao.readByID(k);
			if(presupuesto.getEstado().equals(EstadoPresupuesto.PENDIENTE)) {
				if(v.booleanValue() == true) {
					presDao.updateStateById(k,new Date(), EstadoPresupuesto.APROBADO);	
				} else {
					presDao.updateStateById(k,new Date(), EstadoPresupuesto.RECHAZADO);	
				}
			} else {
				throw new ForbiddenException(FORBIDDEN_CAMBIO_ESTADO);
			}
		});
		Object [] keys = presupuestos.keySet().toArray();
		Integer ordenDeTrabajoId = (Integer) keys[0];
		FacturaDTO factura = new FacturaDTO();
		factura.setIdOrdenDeTrabajo(ordenDeTrabajoId);
		factura.setFechaDeAlta(new Date());
		facturaDao.insert(factura);
	}
	
	public void registrarPagoDeFacturaById(Integer id) throws NotFoundException {
		assert id != null;
		FacturaDTO factura = facturaDao.readByOrdenDeTrabajoId(id);
		if(factura == null) throw new NotFoundException("La orden de trabajo no tiene una factura.");
		if(factura.getFechaDeCierrePorPago() != null) throw new ForbiddenException("No se puede registrar dos pagos para la misma factura");
		facturaDao.updateFechaCierrePorPago(id, new Date());
		List<PresupuestoDTO> presupuestos = presDao.readByOrdenDeTrabajoId(id);
		presupuestos.forEach((k)-> {
			Integer idPresupuesto = k.getIdPresupuesto();
			if(k.getEstado().equals(EstadoPresupuesto.APROBADO)) {
				presDao.updateState(idPresupuesto, EstadoPresupuesto.PAGADO);
			}
		});
	}
}