package business_logic;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import dto.CaracteristicaVehiculoDTO;
import dto.PedidoVehiculoDTO;
import dto.ClienteDTO;
import dto.DatosPersonalesDTO;
import business_logic.exceptions.ForbiddenException;
import dto.CaracteristicaVehiculoDTO;
import dto.SucursalDTO;
import dto.VehiculoDTO;
import dto.VentaVehiculoDTO;
import dto.temporal.ConsultaVehiculoParaVentaDTO;
import dto.temporal.ModalidadVentaVehiculoDTO;
import dto.temporal.OutputConsultaVehiculoEnVentaDTO;
import presentacion.views.utils.FacturaVentaVehiculoReport;
import repositories.DaosFactory;
import services.SessionServiceImpl;

public class VentasVehiculosController {
	
	private static final String FORBIDDEN_MODALIDAD = "Para cerrar una venta es necesario escoger una modalidad de venta.";

	private static final String FORBIDDEN_VEHICULO = "Para cerrar una venta es necesario indicar un vehículo.";

	private static final String FORBIDDEN_CLIENTE = "Para cerrar una venta es necesario indicar el cliente.";

	private static final int PORCENTAJE_COMISION = 3;
	
	private static final int IVA = 21;
	
	private static final String PAIS_SUCURSALES = "Argentina";
	
	private DaosFactory daos;
	
	public VentasVehiculosController(DaosFactory daos) {
		assert daos != null;
		this.daos = daos;
	}

	public VehiculoDTO readByCodigo(Integer codigoVehiculo) {
		return daos.makeVehiculoDao().readByID(codigoVehiculo);
	}

	public List<String> readNombreSucursales() {
		List<SucursalDTO> sucursales = daos.makeSucursalesDao().readByPais(PAIS_SUCURSALES);
		List<String> nombresSucursales = new LinkedList<>();
		for(SucursalDTO suc: sucursales) nombresSucursales.add(suc.getLocalidad());
		return nombresSucursales;
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
	
	public List<OutputConsultaVehiculoEnVentaDTO> readDisponiblesByCriteria(ConsultaVehiculoParaVentaDTO consulta) {
		List<VehiculoDTO> temp = daos.makeVehiculoDao().readDisponiblesByCriteria(consulta.getMarca(), new Boolean(consulta.getTipo().equals("Usado")));	
		List<OutputConsultaVehiculoEnVentaDTO> ret = new LinkedList<>();
		for(VehiculoDTO dto : temp) {
			if(dto.isDisponible()) {
				OutputConsultaVehiculoEnVentaDTO aux = new OutputConsultaVehiculoEnVentaDTO();
				aux.setMarca(dto.getMarca());
				aux.setFamilia(dto.getFamilia());
				aux.setLinea(dto.getLinea());
				aux.setPrecio(dto.getPrecioVenta().toString());
				aux.setCodigo(dto.getIdVehiculo().toString());
				aux.setColor(dto.getColor());
				aux.setSucursal(dto.getIdSucursal() == null ? "NONE" : dto.getIdSucursal().toString());
				ret.add(aux);	
			}
		}
		return ret;
	}

	public CaracteristicaVehiculoDTO readCaracteristicaVehiculoByIdVehiculo(Integer codigoVehiculo) {
		VehiculoDTO vehiculo = daos.makeVehiculoDao().readByID(codigoVehiculo);
		return daos.makeCaracteristicasVehiculoDao().readByID(vehiculo.getIdCaracteristicas());
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

	public void registrarVenta(Integer idCliente, OutputConsultaVehiculoEnVentaDTO vehiculo,
			ModalidadVentaVehiculoDTO modalidadVenta, String fabricante) {
		if(idCliente == null) throw new ForbiddenException(FORBIDDEN_CLIENTE);
		if(vehiculo == null) throw new ForbiddenException(FORBIDDEN_VEHICULO);
		if(modalidadVenta == null) throw new ForbiddenException(FORBIDDEN_MODALIDAD);

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
		venta.setComisionCobrada(Double.parseDouble(modalidadVenta.getComision()));
		venta.setPrecioVenta(getPrecioFinalVenta(modalidadVenta.getMontoFinanciado()));
		venta.setFabricante(fabricante);
		daos.makeVentaVehiculoDao().insert(venta);
		daos.makeVehiculoDao().updateDisponibilidadVehiculo(Integer.parseInt(vehiculo.getCodigo()), new Boolean(false));
	}

	public Double calcularComision(String precio) {
		return Double.parseDouble(precio)*PORCENTAJE_COMISION / 100;
	}

	public Double getPrecioFinalVenta(String precio) {
		Double precioFinal = Double.parseDouble(precio);
		Double impuesto = (precioFinal * IVA)/100;
		return precioFinal + impuesto;
	}

	public String calcularMontoCuota(String montoFinanciado, String nroDeCuotas) {
		Double montoCuota = Double.parseDouble(montoFinanciado) / Integer.parseInt(nroDeCuotas);
		return montoCuota.toString();
	}
	
	public FacturaVentaVehiculoReport makeFacturaVentaVehiculo(VentaVehiculoDTO venta) {
		FacturaVentaVehiculoReport ret = new FacturaVentaVehiculoReport();
		ret.setSucursal(SessionServiceImpl.getInstance().getActiveSession().getSucursal());
		DatosPersonalesDTO datosCliente = daos.makeClienteDao().readByID(venta.getIdCliente()).getDatosPersonalesDTO();
		ret.setCliente(datosCliente);
		VehiculoDTO vehiculo = daos.makeVehiculoDao().readByID(venta.getIdVehiculo());
		ret.setVehiculo(vehiculo);
		ret.setCaracteristicaVehiculo(daos.makeCaracteristicasVehiculoDao().readByID(vehiculo.getIdCaracteristicas()));
		ret.setFichaTecnicaVehiculo(daos.makeFichaTecnicaVehiculoDao().readByID(vehiculo.getIdFichaTecnica()));
		ret.setTotal(vehiculo.getPrecioVenta());
		ret.setFecha(venta.getFechaVentaVN());
		if(venta.getFinanciera() != null) {
			ret.setFormaPago(FacturaVentaVehiculoReport.FINANCIADO);
			ret.setNroCuotas(venta.getNroCuotas());
			ret.setMontoCuota(venta.getMontoCuota());
			ret.setFinanciera(venta.getFinanciera());
		}
		else {
			ret.setFormaPago(FacturaVentaVehiculoReport.EFECTIVO);
		}
		
		return ret;
	}
}
