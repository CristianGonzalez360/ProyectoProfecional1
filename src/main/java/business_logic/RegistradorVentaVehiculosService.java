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
	
	@SuppressWarnings("deprecation")
	public void registrarVenta(Integer idCliente, OutputConsultaVehiculoEnVentaDTO vehiculo, ModalidadVentaVehiculoDTO modalidadVenta) {
		if(idCliente == null) throw new ForbiddenException(FORBIDDEN_CLIENTE);
		if(vehiculo == null) throw new ForbiddenException(FORBIDDEN_VEHICULO);
		VentaVehiculoDTO venta = makeVentaDTO(idCliente, vehiculo, modalidadVenta);
		if(requiereRegistrarPrimero(vehiculo)) {
			VehiculoDTO target = makeVehiculoNuevoParaRegistrar(vehiculo);
			vehiculo.setCodigo(target.getIdVehiculo()+"");
			venta.setIdVehiculo(target.getIdVehiculo());
		}
		daos.makeVentaVehiculoDao().insert(venta);
		daos.makeVehiculoDao().updateDisponibilidadVehiculo(venta.getIdVehiculo(), new Boolean(false));
		makeVehiculoConOrdenDeTrabajo(venta.getIdVehiculo(), idCliente);
	}
	
	private VehiculoDTO makeVehiculoNuevoParaRegistrar(OutputConsultaVehiculoEnVentaDTO vehiculo) {
		VehiculoDTO nuevo = daos.makeVehiculoDao().readByID(Integer.parseInt(vehiculo.getCodigo()));
		nuevo.setIdVehiculo(null);
		nuevo.setDisponible(true);
		nuevo.setFechaIngreso(null);			
		daos.makeVehiculoDao().insert(nuevo);
		int last = daos.makeVehiculoDao().readAll().size() -1;
		VehiculoDTO target = daos.makeVehiculoDao().readAll().get(last);
		return target;
	}
	
	private boolean requiereRegistrarPrimero(OutputConsultaVehiculoEnVentaDTO vehiculo) {
		return vehiculo.getSucursal().equals(FABRICANTE);
	}

	private VentaVehiculoDTO makeVentaDTO(Integer idCliente, OutputConsultaVehiculoEnVentaDTO vehiculo, ModalidadVentaVehiculoDTO modalidadVenta) {
		VentaVehiculoDTO venta = new VentaVehiculoDTO();
		venta.setFechaVentaVN(new Date());
		venta.setIdCliente(idCliente);
		venta.setIdVehiculo(Integer.parseInt(vehiculo.getCodigo()));
		venta.setIdUsuVentaVN(SessionServiceImpl.getInstance().getActiveSession().getIdUsuario());
		venta.setIdSucursalVenta(SessionServiceImpl.getInstance().getActiveSession().getIdSucursal());
		if(!modalidadVenta.isEfectivo()) {
			venta.setFinanciera(modalidadVenta.getFinanciera());
			venta.setNroCuotas(Integer.parseInt(modalidadVenta.getNroCuotas()));
			venta.setMontoCuota(Double.parseDouble(modalidadVenta.getMontoCuota()));
		}
		venta.setFabricante(vehiculo.getMarca());
		venta.setComisionCobrada(Double.parseDouble(modalidadVenta.getComision()));
		venta.setPrecioVenta(new CalculadoraMontoFinalVentaService(modalidadVenta).getPrecioFinalVenta());
		return venta;
	}
	
	private void makeVehiculoConOrdenDeTrabajo(Integer idVehiculo, Integer idCliente) {
		VehiculoDTO vehiculo = daos.makeVehiculoDao().readByID(idVehiculo);
		VehiculoConOrdenDeTrabajoDTO v = new VehiculoConOrdenDeTrabajoDTO();
		v.setIdFichaTecnica(vehiculo.getIdFichaTecnica());
		v.setIdCliente(idCliente);
		if(vehiculo.isUsado()) {
			FichaTecnicaVehiculoDTO ficha = daos.makeFichaTecnicaVehiculoDao().readByID(vehiculo.getIdFichaTecnica());
			v.setPatente(ficha.getPatente());
		}
		//GarantiaVehiculoDTO garantia = daos.makeGarantiasVehiculosDao().readByIdVehiculo(vehiculo.getIdVehiculo());
		//v.setKilometrajeGarantia(garantia.getKilometrajeGarantizado());
		v.setIdVehiculo(idVehiculo);
		daos.makeVehiculoConOrdeDeTrabajoDao().insert(v);
	}
}
