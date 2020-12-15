package presentacion.views.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.CaracteristicaVehiculoDTO;
import dto.DatosPersonalesDTO;
import dto.SucursalDTO;
import dto.VehiculoDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class FacturaVentaVehiculoReport {

	public static String FINANCIADO = "FINANCIADO";
	public static String EFECTIVO = "EFECTIVO";

	private Integer numero;
	private SucursalDTO sucursal;
	private DatosPersonalesDTO cliente;
	private VehiculoDTO vehiculo;
	private CaracteristicaVehiculoDTO caracteristicaVehiculo;
	private FichaTecnicaVehiculoDTO fichaTecnicaVehiculo;
	private Double total;
	private Date fecha;
	private String formaPago;
	private String financiera;
	private Integer nroCuotas;
	private Double montoCuota;

	public FacturaVentaVehiculoReport() {
		// TODO Auto-generated constructor stub
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getFinanciera() {
		return financiera;
	}

	public void setFinanciera(String financiera) {
		this.financiera = financiera;
	}

	public Integer getNroCuotas() {
		return nroCuotas;
	}

	public void setNroCuotas(Integer nroCuotas) {
		this.nroCuotas = nroCuotas;
	}

	public Double getMontoCuota() {
		return montoCuota;
	}

	public void setMontoCuota(Double montoCuota) {
		this.montoCuota = montoCuota;
	}

	public JRDataSource getSucursal() {
		List<SucursalDTO> sucursal = new ArrayList<>();
		sucursal.add(this.sucursal);
		return new JRBeanCollectionDataSource(sucursal);
	}

	public void setSucursal(SucursalDTO sucursal) {
		this.sucursal = sucursal;
	}

	public JRDataSource getCliente() {
		List<DatosPersonalesDTO> cliente = new ArrayList<>();
		cliente.add(this.cliente);
		return new JRBeanCollectionDataSource(cliente);
	}

	public void setCliente(DatosPersonalesDTO cliente) {
		this.cliente = cliente;
	}

	public JRDataSource getVehiculo() {
		List<VehiculoDTO> vehiculo = new ArrayList<>();
		vehiculo.add(this.vehiculo);
		return new JRBeanCollectionDataSource(vehiculo);
	}

	public void setVehiculo(VehiculoDTO vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public JRDataSource getCaracteristicaVehiculo() {
		List<CaracteristicaVehiculoDTO> caracteristicaVehiculo = new ArrayList<>();
		caracteristicaVehiculo.add(this.caracteristicaVehiculo);
		return new JRBeanCollectionDataSource(caracteristicaVehiculo);
	}

	public void setCaracteristicaVehiculo(CaracteristicaVehiculoDTO caracteristicaVehiculo) {
		this.caracteristicaVehiculo = caracteristicaVehiculo;
	}

	public JRDataSource getFichaTecnicaVehiculo() {
		List<FichaTecnicaVehiculoDTO> fichaTecnicaVehiculo = new ArrayList<>();
		if (this.fichaTecnicaVehiculo != null)
			fichaTecnicaVehiculo.add(this.fichaTecnicaVehiculo);
		return new JRBeanCollectionDataSource(fichaTecnicaVehiculo);
	}

	public void setFichaTecnicaVehiculo(FichaTecnicaVehiculoDTO fichaTecnicaVehiculo) {
		this.fichaTecnicaVehiculo = fichaTecnicaVehiculo;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
}