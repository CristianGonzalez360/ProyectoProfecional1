package business_logic;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import business_logic.exceptions.ForbiddenException;

import dto.CaracteristicaVehiculoDTO;
import dto.ClienteDTO;
import dto.DatosPersonalesDTO;
import dto.SucursalDTO;
import dto.VehiculoDTO;
import dto.VentaVehiculoDTO;
import dto.temporal.ConsultaVehiculoParaVentaDTO;
import dto.temporal.ModalidadVentaVehiculoDTO;
import dto.temporal.OutputConsultaVehiculoEnVentaDTO;
import dto.temporal.VentaDTO;
import dto.temporal.VehiculoParaEntregar;
import presentacion.views.utils.FacturaVentaVehiculoReport;
import repositories.DaosFactory;
import services.SessionServiceImpl;

public class VentasVehiculosController {

	private DaosFactory daos;

	public VentasVehiculosController(DaosFactory daos) {
		assert daos != null;
		this.daos = daos;
	}

	public VehiculoDTO readByCodigo(Integer codigoVehiculo) {
		return daos.makeVehiculoDao().readByID(codigoVehiculo);
	}

	public List<VentaVehiculoDTO> readVentasVehiculosNoDisponibles() {
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

	public List<VentaVehiculoDTO> readFechas(Date desde, Date hasta) {
		return daos.makeVentaVehiculoDao().readFechas(desde, hasta);
	}

	public List<VentaVehiculoDTO> readByIdVendedor(Integer id, Date desde, Date hasta) {
		return daos.makeVentaVehiculoDao().readByIdVendedor(id, desde, hasta);
	}

	public List<VehiculoDTO> readAllVehiculoNuevos() {
		List<VehiculoDTO> vehiculos = daos.makeVehiculoDao().readAll();
		List<VehiculoDTO> vehiculosNuevos = new LinkedList<VehiculoDTO>();
		for (VehiculoDTO vehiculo : vehiculos) {
			if (vehiculo.isUsado() == false) {
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

	public void registrarVenta(Integer idCliente, OutputConsultaVehiculoEnVentaDTO vehiculo,
			ModalidadVentaVehiculoDTO modalidadVenta) {
		new RegistradorVentaVehiculosService(daos).registrarVenta(idCliente, vehiculo, modalidadVenta);
	}

	public CaracteristicaVehiculoDTO readCaracteristicaVehiculoByIdVehiculo(Integer codigoVehiculo) {
		VehiculoDTO vehiculo = daos.makeVehiculoDao().readByID(codigoVehiculo);
		return daos.makeCaracteristicasVehiculoDao().readByID(vehiculo.getIdCaracteristicas());
	}

	public List<String> readNombreSucursales() {
		List<SucursalDTO> sucursales = daos.makeSucursalesDao().readByPais(readPaisSucursal());
		List<String> nombresSucursales = new LinkedList<>();
		for (SucursalDTO suc : sucursales)
			nombresSucursales.add(suc.getLocalidad());
		return nombresSucursales;
	}

	private String readPaisSucursal() {
		Integer idSucursal = SessionServiceImpl.getInstance().getActiveSession().getIdSucursal();
		SucursalDTO sucursal = daos.makeSucursalesDao().readByID(idSucursal);
		return sucursal.getPais();
	}

	public void registrarEntrega(VehiculoParaEntregar ventaParaEntregar) throws ForbiddenException{
		if(ventaParaEntregar == null) throw new ForbiddenException("Para entregar una venta es necesario seleccionarla.");
		if(ventaParaEntregar.getVehiculo().getIdSucursal() == null) {
			if(!ventaParaEntregar.isPedido()) throw new ForbiddenException("El vehículo no fue pedido.");
			if(!ventaParaEntregar.isIngresado()) throw new ForbiddenException("El vehículo No fue ingresado a la consecionaria.");
		}
		else {
			if (ventaParaEntregar.getSucursal().getIdSucursal() != SessionServiceImpl.getInstance().getActiveSession().getIdSucursal()) 
				throw new ForbiddenException("El vehículo no esta en esta sucursal");
		}
		VentaVehiculoDTO venta = ventaParaEntregar.getVenta();
		venta.setFechaEntregaReal(new Date());
		daos.makeVentaVehiculoDao().update(venta);
		
	}

	public List<VehiculoParaEntregar> readVentasVehiculosParaEntregar() {
		List<VentaVehiculoDTO> ventas = daos.makeVentaVehiculoDao().readVentasVehiculosParaEntregar();
		List<VehiculoParaEntregar> ret = new LinkedList<>();
		for (VentaVehiculoDTO venta : ventas) {
			ret.add(makeVentaParaEntregar(venta));
		}
		return ret;
	}
	
	private VehiculoParaEntregar makeVentaParaEntregar(VentaVehiculoDTO venta) {
		VehiculoParaEntregar ret =  new VehiculoParaEntregar();
		ret.setPedido(daos.makePedidoVehiculoDao().estaPedido(venta.getIdVentaVehiculo()));
		ret.setIngresado(daos.makePedidoVehiculoDao().estaIngresado(venta.getIdVentaVehiculo()));
		VehiculoDTO vehiculo = daos.makeVehiculoDao().readByID(venta.getIdVehiculo());
		ret.setVehiculo(vehiculo);
		if(vehiculo.getIdSucursal() != null)
			ret.setSucursal(daos.makeSucursalesDao().readByID(vehiculo.getIdSucursal()));
		ret.setVenta(venta);
		return ret;
	}

	public FacturaVentaVehiculoReport makeFacturaVentaVehiculo(VentaVehiculoDTO venta) {
		FacturaVentaVehiculoReport ret = new FacturaVentaVehiculoReport();
		ret.setNumero(venta.getIdVentaVehiculo());
		ret.setSucursal(SessionServiceImpl.getInstance().getActiveSession().getSucursal());
		DatosPersonalesDTO datosCliente = daos.makeClienteDao().readByID(venta.getIdCliente()).getDatosPersonalesDTO();
		ret.setCliente(datosCliente);
		VehiculoDTO vehiculo = daos.makeVehiculoDao().readByID(venta.getIdVehiculo());
		ret.setVehiculo(vehiculo);
		ret.setCaracteristicaVehiculo(daos.makeCaracteristicasVehiculoDao().readByID(vehiculo.getIdCaracteristicas()));
		if(vehiculo.getIdFichaTecnica()!=null) {
			ret.setFichaTecnicaVehiculo(daos.makeFichaTecnicaVehiculoDao().readByID(vehiculo.getIdFichaTecnica()));
		}
		ret.setTotal(vehiculo.getPrecioVenta());
		ret.setFecha(venta.getFechaVentaVN());
		if (venta.getFinanciera() != null) {
			ret.setFormaPago(FacturaVentaVehiculoReport.FINANCIADO);
			ret.setNroCuotas(venta.getNroCuotas());
			ret.setMontoCuota(venta.getMontoCuota());
			ret.setFinanciera(venta.getFinanciera());
		} else {
			ret.setFormaPago(FacturaVentaVehiculoReport.EFECTIVO);
		}

		return ret;
	}

	public VentaVehiculoDTO readByIdVehiculo(Integer idVehiculo) {
		return daos.makeVentaVehiculoDao().readByIdVehiculo(idVehiculo);
	}

	public List<VentaDTO> readVentas(Integer idUsuario, Date desde, Date hasta) {
		List<VentaDTO> ventas = new LinkedList<>();

		for (VentaVehiculoDTO venta : readByIdVendedor(idUsuario, desde, hasta)) {
			if (venta != null)
				ventas.add(armarVenta(venta));
		}
		return ventas;
	}

	public List<VentaDTO> readVentas(Date desde, Date hasta) {
		List<VentaDTO> ventas = new LinkedList<>();

		for (VentaVehiculoDTO venta : readFechas(desde, hasta)) {
			if (venta != null)
				ventas.add(armarVenta(venta));
		}
		return ventas;
	}

	private VentaDTO armarVenta(VentaVehiculoDTO venta) {
		Integer idVenta = venta.getIdVentaVehiculo();
		Date fechaDeVenta = venta.getFechaVentaVN();
		Date fechaDeEntrega = venta.getFechaEntregaReal();
		Double precioVenta = venta.getPrecioVenta();
		Double comisionVenta = venta.getComisionCobrada();

		VehiculoDTO vehiculo = datosVehiculo(venta.getIdVehiculo());
		ClienteDTO cliente = datosCliente(venta.getIdCliente());
		SucursalDTO sucursal = datosSucursal(venta.getIdSucursalVenta());

		String marcaVehiculo = vehiculo.getMarca();
		String modeloVehiculo = vehiculo.getFamilia() + " - " + vehiculo.getLinea();
		String nombreCliente = cliente.getDatosPersonalesDTO().getNombreCompleto();
		String calleSucursal = sucursal.getCalle() + " " + sucursal.getAltura();

		return new VentaDTO(idVenta, fechaDeVenta, fechaDeEntrega, marcaVehiculo, modeloVehiculo, nombreCliente,
				precioVenta, comisionVenta, calleSucursal);
	}

	private SucursalDTO datosSucursal(Integer idSucursal) {
		return daos.makeSucursalesDao().readByID(idSucursal);
	}

	private ClienteDTO datosCliente(Integer idCliente) {
		return daos.makeClienteDao().readByID(idCliente);
	}

	private VehiculoDTO datosVehiculo(Integer idVehiculo) {
		return daos.makeVehiculoDao().readByID(idVehiculo);
	}

}
