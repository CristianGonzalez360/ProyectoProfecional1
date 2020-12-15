package business_logic;

import java.util.Date;
import business_logic.exceptions.ForbiddenException;
import dto.VehiculoDTO;
import dto.VentaVehiculoDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
import dto.taller.VehiculoConOrdenDeTrabajoDTO;
import dto.temporal.ModalidadVentaVehiculoDTO;
import dto.temporal.OutputConsultaVehiculoEnVentaDTO;
import repositories.DaosFactory;
import services.SessionServiceImpl;

public class RegistradorVentaVehiculosService {

	private static final String FORBIDDEN_VEHICULO = "Para cerrar una venta es necesario indicar un vehículo.";

	private static final String FORBIDDEN_CLIENTE = "Para cerrar una venta es necesario indicar el cliente.";

	private static final String FABRICANTE = "Fabricante";

	private DaosFactory daos;

	public RegistradorVentaVehiculosService(DaosFactory daos) {
		assert daos != null;
		this.daos = daos;
	}

	public void registrarVenta(Integer idCliente, OutputConsultaVehiculoEnVentaDTO vehiculo, ModalidadVentaVehiculoDTO modalidadVenta) {
		if (idCliente == null)throw new ForbiddenException(FORBIDDEN_CLIENTE);
		if (vehiculo == null) throw new ForbiddenException(FORBIDDEN_VEHICULO);
		VentaVehiculoDTO venta = makeVentaDTO(idCliente, vehiculo, modalidadVenta);
		VehiculoDTO vehiculoNuevoEnLaFabrica = null;
		final boolean REQUIERE_REGISTRAR = vehiculo.getSucursal().equals(FABRICANTE);
		if (REQUIERE_REGISTRAR) {
			vehiculoNuevoEnLaFabrica = makeVehiculoNuevoParaRegistrar(vehiculo);
			vehiculo.setCodigo(vehiculoNuevoEnLaFabrica.getIdVehiculo()+"");
			venta.setIdVehiculo(vehiculoNuevoEnLaFabrica.getIdVehiculo());
		}
		else {
			venta.setIdVehiculo(Integer.parseInt(vehiculo.getCodigo()));
		}
		daos.makeVentaVehiculoDao().insert(venta);
		new ActualizadorDeGarantiaService(daos).updateGarantiaVehiculo(venta.getIdVehiculo(), modalidadVenta);
		makeVehiculoConOrdenDeTrabajo(venta.getIdVehiculo(), idCliente);
	}
	
	private void makeVehiculoConOrdenDeTrabajo(Integer idVehiculo, Integer idCliente) {
		VehiculoConOrdenDeTrabajoDTO vehiConOT = new VehiculoConOrdenDeTrabajoDTO();
		VehiculoDTO vehiculo = daos.makeVehiculoDao().readByID(idVehiculo);		
		
		/**
		 * @SI EL VEHICULO ES NUEVO Y NO ESTÁ EN UNA SUCURSAL NO TIENE QUE TENER FICHA TECNICA
		 */
		vehiConOT.setIdFichaTecnica(vehiculo.getIdFichaTecnica());
		vehiConOT.setIdCliente(idCliente);
		if (vehiculo.isUsado()) {
			FichaTecnicaVehiculoDTO ficha = daos.makeFichaTecnicaVehiculoDao().readByID(vehiculo.getIdFichaTecnica());
			vehiConOT.setPatente(ficha.getPatente());
		}
		vehiConOT.setIdVehiculo(idVehiculo);
		daos.makeVehiculoConOrdeDeTrabajoDao().insert(vehiConOT);
	}

	private VehiculoDTO makeVehiculoNuevoParaRegistrar(OutputConsultaVehiculoEnVentaDTO vehiculo) {
		VehiculoDTO nuevo = daos.makeVehiculoDao().readByID(Integer.parseInt(vehiculo.getCodigo()));
		nuevo.setIdVehiculo(null);
		nuevo.setDisponible(false);
		nuevo.setFechaIngreso(null);
		daos.makeVehiculoDao().insert(nuevo);
		int last = daos.makeVehiculoDao().readAll().size() - 1;
		VehiculoDTO target = daos.makeVehiculoDao().readAll().get(last);
		return target;
	}

	private VentaVehiculoDTO makeVentaDTO(Integer idCliente, OutputConsultaVehiculoEnVentaDTO vehiculo,
			ModalidadVentaVehiculoDTO modalidadVenta) {
		VentaVehiculoDTO venta = new VentaVehiculoDTO();
		venta.setFechaVentaVN(new Date());
		venta.setIdCliente(idCliente);
		venta.setIdVehiculo(Integer.parseInt(vehiculo.getCodigo()));
		venta.setIdUsuVentaVN(SessionServiceImpl.getInstance().getActiveSession().getIdUsuario());
		venta.setIdSucursalVenta(SessionServiceImpl.getInstance().getActiveSession().getIdSucursal());
		if (!modalidadVenta.isEfectivo()) {
			venta.setFinanciera(modalidadVenta.getFinanciera());
			venta.setNroCuotas(Integer.parseInt(modalidadVenta.getNroCuotas()));
			venta.setMontoCuota(Double.parseDouble(modalidadVenta.getMontoCuota()));
		}
		venta.setFabricante(vehiculo.getMarca());
		venta.setComisionCobrada(Double.parseDouble(modalidadVenta.getComision()));
		venta.setPrecioVenta(new CalculadoraMontoFinalVentaService(modalidadVenta).getPrecioFinalVenta());
		return venta;
	}
}
