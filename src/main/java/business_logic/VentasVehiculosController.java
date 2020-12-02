package business_logic;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import business_logic.exceptions.ForbiddenException;

import dto.CaracteristicaVehiculoDTO;
import dto.SucursalDTO;
import dto.VehiculoDTO;
import dto.VentaVehiculoDTO;
import dto.temporal.ConsultaVehiculoParaVentaDTO;
import dto.temporal.ModalidadVentaVehiculoDTO;
import dto.temporal.OutputConsultaVehiculoEnVentaDTO;
import repositories.DaosFactory;
import services.SessionServiceImpl;

public class VentasVehiculosController {
	
	private static final String FORBIDDEN_MODALIDAD = "Para cerrar una venta es necesario escoger una modalidad de venta.";

	private static final String FORBIDDEN_VEHICULO = "Para cerrar una venta es necesario indicar un vehículo.";

	private static final String FORBIDDEN_CLIENTE = "Para cerrar una venta es necesario indicar el cliente.";

	private static final int PORCENTAJE_COMISION = 3;
	
	private static final int IVA = 21;
	
	private static final String PAIS_SUCURSALES = "Argentina";
	
	private static final String FORBIDDEN_VENTA = "Para entregar una venta es necesario seleccionarla.";
	
	private DaosFactory daos;
	
	public VentasVehiculosController(DaosFactory daos) {
		assert daos != null;
		this.daos = daos;
	}
	
	public VehiculoDTO readByCodigo(Integer codigoVehiculo) {
		return daos.makeVehiculoDao().readByID(codigoVehiculo);
	}

	public List<VentaVehiculoDTO> readVentasVehiculosNoDisponibles(){
		List<VentaVehiculoDTO> ret = daos.makeVentaVehiculoDao().readVentasVehiculosNoDisponibles();
		for (VentaVehiculoDTO venta : ret) {
			venta.setPedido(daos.makePedidoVehiculoDao().estaPedido(venta.getIdVentaVehiculo()));
		}
		return ret;
	}

	public List<String> readNombreMarcasVehiculos() {
		return daos.makeVehiculoDao().readAllMarcasVehiculos();
	}
	
	public List<VentaVehiculoDTO> readAll() {
		return daos.makeVentaVehiculoDao().readAll();
	}
	
	public List<VentaVehiculoDTO> readFechas(Date desde,Date hasta) {
		return daos.makeVentaVehiculoDao().readFechas(desde, hasta);
	}
	
	public List<VentaVehiculoDTO> readByIdVendedor(Integer id,Date desde, Date hasta) {
		return daos.makeVentaVehiculoDao().readByIdVendedor(id,desde,hasta);
	}
		
	public List<VehiculoDTO> readAllVehiculoNuevos() {
		List<VehiculoDTO> vehiculos = daos.makeVehiculoDao().readAll();
		List<VehiculoDTO> vehiculosNuevos = new LinkedList<VehiculoDTO>();
		for (VehiculoDTO vehiculo : vehiculos) {
			if(vehiculo.isUsado()==false) {
				vehiculosNuevos.add(vehiculo);
			}
		}
		return vehiculosNuevos;
	}
	
	public void saveVehiculosNuevos(List<VehiculoDTO> vehiculosNuevos) {
		for (VehiculoDTO vehiculo : vehiculosNuevos) {
			daos.makeVehiculoDao().insert(vehiculo);
		}
	}

	public List<OutputConsultaVehiculoEnVentaDTO> readDisponiblesByCriteria(ConsultaVehiculoParaVentaDTO consulta) {		
		return new ConsultadorVehiculosEnVentaService().read(daos, consulta);
	}
	
	public void registrarVenta(Integer idCliente, OutputConsultaVehiculoEnVentaDTO vehiculo,ModalidadVentaVehiculoDTO modalidadVenta) {
		new RegistradorVentaVehiculosService(daos).registrarVenta(idCliente, vehiculo, modalidadVenta);
	}
	
	public CaracteristicaVehiculoDTO readCaracteristicaVehiculoByIdVehiculo(Integer codigoVehiculo) {
		VehiculoDTO vehiculo = daos.makeVehiculoDao().readByID(codigoVehiculo);
		return daos.makeCaracteristicasVehiculoDao().readByID(vehiculo.getIdCaracteristicas());
	}
	
	public List<String> readNombreSucursales() {
		List<SucursalDTO> sucursales = daos.makeSucursalesDao().readByPais(readPaisSucursal());
		List<String> nombresSucursales = new LinkedList<>();
		for(SucursalDTO suc: sucursales) nombresSucursales.add(suc.getLocalidad());
		return nombresSucursales;
	}
	
	private String readPaisSucursal() {
		Integer idSucursal = SessionServiceImpl.getInstance().getActiveSession().getIdSucursal();
		SucursalDTO sucursal = daos.makeSucursalesDao().readByID(idSucursal);
		return sucursal.getPais();
	}
	//TODO validacion no del todo necesaria porque no se da el caso donde ventaVehiculo llegue null
	public void registrarEntrega(Integer idVentaVehiculo) {
		if(idVentaVehiculo == null) {
			throw new ForbiddenException(FORBIDDEN_VENTA);
		}else{
		VentaVehiculoDTO venta = daos.makeVentaVehiculoDao().readByID(idVentaVehiculo);
		venta.setFechaEntregaReal(new Date());//JERE TODO verificar si aca trae la fecha actual y la setea.
		daos.makeVentaVehiculoDao().update(venta);}//TODO consultar nuevamente si estoy seteando el parametro correcto con cristian
	}
	
	public List<VentaVehiculoDTO> readVentasVehiculosParaEntregar(){
		List<VentaVehiculoDTO> ret = daos.makeVentaVehiculoDao().readVentasVehiculosParaEntregar();
		for (VentaVehiculoDTO venta : ret) {
			//Cambiar por llamado a la BD que traiga VentasVehyiculo con fechaEntregaReal = null
//			venta.setPedido(daos.makePedidoVehiculoDao().estaPedido(venta.getIdVentaVehiculo()));
			venta.setIngresado(daos.makePedidoVehiculoDao().estaIngresado(venta.getIdVentaVehiculo()));
		}
		return ret;
	}
	
//	public List<VentaVehiculoDTO> readVentasVehiculosNoDisponibles(){
//		List<VentaVehiculoDTO> ret = daos.makeVentaVehiculoDao().readVentasVehiculosNoDisponibles();
//		for (VentaVehiculoDTO venta : ret) {
//			venta.setPedido(daos.makePedidoVehiculoDao().estaPedido(venta.getIdVentaVehiculo()));
//		}
//		return ret;
//	}
	
}
