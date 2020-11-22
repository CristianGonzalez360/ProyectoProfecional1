package business_logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.ClienteDTO;
import dto.DatosPersonalesDTO;
import dto.EntregaDeVehiculoDTO;
import dto.FacturaDTO;
import dto.FichaTecnicaVehiculoDTO;
import dto.OrdenDeTrabajoDTO;
import dto.PresupuestoDTO;
import dto.VehiculoConOrdenDeTrabajoDTO;
import repositories.ClientesDao;
import repositories.DatosPersonalesDao;
import repositories.FacturasDao;
import repositories.FichaTecnicaVehiculoDao;
import repositories.OrdenesDeTrabajoDao;
import repositories.PresupuestosDao;
import repositories.VehiculosConOrdenDeTrabajoDao;
import services.EmailSenderService;

public class EntregaDeVehiculoController {

	private ClientesDao clientesDao;
	private DatosPersonalesDao datosPersonalesDao;
	private OrdenesDeTrabajoDao ordenesDeTrabajoDao;
	private PresupuestosDao presupuestosDao;
	private VehiculosConOrdenDeTrabajoDao vehiculosDao;
	private FichaTecnicaVehiculoDao fichaTecnicaDao;
	private FacturasDao facturaDao;

	private EmailSenderService servicio;

	public EntregaDeVehiculoController(ClientesDao clienteDao, DatosPersonalesDao datosPersonalesDao,
			OrdenesDeTrabajoDao ordenDeTrabajoDao, PresupuestosDao presupuestoDao,
			VehiculosConOrdenDeTrabajoDao vehiculoConOrdeDeTrabajoDao, FichaTecnicaVehiculoDao fichaTecnicaDao,
			FacturasDao facturaDao) {
		this.clientesDao = clienteDao;
		this.datosPersonalesDao = datosPersonalesDao;
		this.ordenesDeTrabajoDao = ordenDeTrabajoDao;
		this.presupuestosDao = presupuestoDao;
		this.vehiculosDao = vehiculoConOrdeDeTrabajoDao;
		this.fichaTecnicaDao = fichaTecnicaDao;
		this.facturaDao = facturaDao;

		this.servicio = new EmailSenderService();
	}

	public List<EntregaDeVehiculoDTO> readByDniCliente(Integer dniCliente) {
		List<EntregaDeVehiculoDTO> ordenesParaEntregar = readAllOrdenesRealizadas();
		List<EntregaDeVehiculoDTO> entregas = new ArrayList<>();

		for (EntregaDeVehiculoDTO entrega : ordenesParaEntregar)
			if (entrega.getDniCliente() != null && entrega.getDniCliente().equals(dniCliente))
				entregas.add(entrega);
		return entregas;
	}

	public List<EntregaDeVehiculoDTO> readAllOrdenesRealizadas() {
		List<EntregaDeVehiculoDTO> entregas = new ArrayList<>();

		for (OrdenDeTrabajoDTO orden : ordenesDeTrabajoDao.readAllOrdenesSinEntregar())
			if (disponibleParaEntrega(orden))
				entregas.add(generarEntrega(orden));
		return entregas;
	}

	private boolean disponibleParaEntrega(OrdenDeTrabajoDTO orden) {
		Integer idOT = orden.getIdOrdenTrabajo();
		List<FacturaDTO> facturas = facturaDao.readByOrdenDeTrabajoId(idOT);
		List<PresupuestoDTO> presupuestos = presupuestosDao.readByOrdenDeTrabajoId(idOT);

		return (facturasPagas(facturas) && presupuetosRealizados(presupuestos));
	}

	private boolean facturasPagas(List<FacturaDTO> facturas) {
		if (facturas.isEmpty())
			return true;

		for (FacturaDTO factura : facturas)
			if (!factura.estaPaga())
				return false;
		return true;
	}

	private boolean presupuetosRealizados(List<PresupuestoDTO> presupuestos) {
		if (presupuestos.isEmpty())
			return true;

		for (PresupuestoDTO presupuesto : presupuestos)
			if (presupuesto.estaPendiente() || (!presupuesto.estaRealizado() && !presupuesto.estaRechazado()))
				return false;
		return true;
	}

	private EntregaDeVehiculoDTO generarEntrega(OrdenDeTrabajoDTO orden) {
		VehiculoConOrdenDeTrabajoDTO vehiculoConOT = vehiculosDao.readByID(orden.getIdVehiculoOt());
		FichaTecnicaVehiculoDTO fichaTecnica = fichaTecnicaDao.readByID(vehiculoConOT.getIdFichaTecnica());
		ClienteDTO cliente = clientesDao.readByID(vehiculoConOT.getIdCliente());
		DatosPersonalesDTO datosPersonales = datosPersonalesDao.readByID(cliente.getIdDatosPersonales());

		Integer idOrdenDeTrabajo = orden.getIdOrdenTrabajo();
		Integer dniCliente = datosPersonales.getDni();
		String nombreCompleto = String.format("%s %s", datosPersonales.getNombreCompleto(),
				datosPersonales.getApellido());
		String marcaAuto = fichaTecnica.getMarca();
		String modeloAuto = String.valueOf(fichaTecnica.getModelo());
		String colorAuto = fichaTecnica.getColor();
		String patenteAuto = vehiculoConOT.getPatente();

		return new EntregaDeVehiculoDTO(dniCliente, nombreCompleto, marcaAuto, modeloAuto, colorAuto, patenteAuto,
				idOrdenDeTrabajo);
	}

	public void registrarEntregaById(Integer idOt) {
		if (idOt == null)
			return;

		OrdenDeTrabajoDTO orden = ordenesDeTrabajoDao.readByID(idOt);
		orden.setFechaEntregado(new Date());
		ordenesDeTrabajoDao.update(orden);
	}

	public boolean enviarCorreoDeSatisfaccion(String correoDestinatario) {
		return this.servicio.enviarMailDeSatisfaccion(correoDestinatario);
	}
}
