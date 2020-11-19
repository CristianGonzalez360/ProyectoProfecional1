package business_logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.ClienteDTO;
import dto.DatosPersonalesDTO;
import dto.EntregaDeVehiculoDTO;
import dto.EstadoPresupuesto;
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

public class EntregaDeVehiculoController {

	private ClientesDao clientesDao;
	private DatosPersonalesDao datosPersonalesDao;
	private OrdenesDeTrabajoDao ordenesDeTrabajoDao;
	private PresupuestosDao presupuestosDao;
	private VehiculosConOrdenDeTrabajoDao vehiculosDao;
	private FichaTecnicaVehiculoDao fichaTecnicaDao;
	private FacturasDao facturaDao;

	private List<OrdenDeTrabajoDTO> ordenesRealizadas;

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
	}

	public DatosPersonalesDTO readDatosClienteByDni(Integer dni) {
		return datosPersonalesDao.readByDni(dni);
	}

	public List<EntregaDeVehiculoDTO> readAllOrdenesRealizadas() {
		List<EntregaDeVehiculoDTO> entregas = new ArrayList<>();

		// ordenesRealizadas = ordenesDeTrabajoDao.readAllOrdenesRealizadas();
		ordenesRealizadas = ordenesDeTrabajoDao.readAll();

		for (OrdenDeTrabajoDTO orden : ordenesRealizadas) {// Ordenes de trabajo con presupuesto finalizado
			Integer idOT = orden.getIdOrdenTrabajo();
			Integer idVehiculo = orden.getIdVehiculoOt();

			VehiculoConOrdenDeTrabajoDTO vehiculoConOT = vehiculosDao.readByID(idVehiculo);
			Integer idFichaTecnica = vehiculoConOT.getIdFichaTecnica();
			Integer idCliente = vehiculoConOT.getIdCliente();

			FichaTecnicaVehiculoDTO fichaTecnica = fichaTecnicaDao.readByID(idFichaTecnica);

			ClienteDTO cliente = clientesDao.readByID(idCliente);
			Integer idDatos = cliente.getIdDatosPersonales();

			DatosPersonalesDTO datosPersonales = datosPersonalesDao.readByID(idDatos);

			Integer dniCliente = datosPersonales.getDni();
			String nombreCompleto = String.format("%s %s", datosPersonales.getNombreCompleto(),
					datosPersonales.getApellido());
			String marcaAuto = fichaTecnica.getMarca();
			String modeloAuto = String.valueOf(fichaTecnica.getModelo());
			String colorAuto = fichaTecnica.getColor();
			String patenteAuto = vehiculoConOT.getPatente();

			List<FacturaDTO> facturas = facturaDao.readByOrdenDeTrabajoId(idOT);
			List<PresupuestoDTO> presupuestos = presupuestosDao.readByOrdenDeTrabajoId(idOT);

			System.out.println(orden.toString());

			if (facturasPagas(facturas)) {
				for (FacturaDTO factura : facturas) {
					System.out.println(factura.toString());
				}

				EntregaDeVehiculoDTO nuevaEntrega = new EntregaDeVehiculoDTO(dniCliente, nombreCompleto, marcaAuto,
						modeloAuto, colorAuto, patenteAuto, idOT);

				System.out.println(nuevaEntrega);
				System.out.println();
//				entregas.add(nuevaEntrega);
			}

			for (PresupuestoDTO presupuesto : presupuestos) {
				System.out.println(presupuesto.toString());
			}

		}
		return entregas;
	}

	private boolean facturasPagas(List<FacturaDTO> facturas) {
		if (facturas.isEmpty())
			return false;

		for (FacturaDTO factura : facturas) {
			if (!factura.getEstado().equals("PAGA"))
				return false;
		}
		return true;
	}

//	private boolean presupuetosPagos(FacturaDTO factura) {
//		for(PresupuestoDTO presupuesto : factura.getPresupuestosFacturados()){
//			if(!presupuesto.getEstado().equals(EstadoPresupuesto.REALIZADO))
//				return false;
//		}
//		return true;
//	}

	public List<EntregaDeVehiculoDTO> readByDniCliente(Integer dniCliente) {
		List<EntregaDeVehiculoDTO> ordenesParaEntregar = readAllOrdenesRealizadas();
		List<EntregaDeVehiculoDTO> entregas = new ArrayList<>();

		for (EntregaDeVehiculoDTO entrega : ordenesParaEntregar) {
			if (entrega.getDniCliente() != null && entrega.getDniCliente().equals(dniCliente)) {
				entregas.add(entrega);
			}
		}

		return entregas;
	}

	public void registrarEntregaById(Integer idOt) {
		assert idOt != null;
		OrdenDeTrabajoDTO orden = ordenesDeTrabajoDao.readByID(idOt);
		orden.setFechaEntregado(new Date());
		ordenesDeTrabajoDao.update(orden);
	}

	public OrdenDeTrabajoDTO readByID(Integer idOt) {
		return ordenesDeTrabajoDao.readByID(idOt);
	}

}
