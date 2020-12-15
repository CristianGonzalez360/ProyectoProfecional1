package presentacion.views.utils;

import java.util.ArrayList;
import java.util.Date;

import dto.SucursalDTO;
import dto.taller.FacturaDTO;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import services.SessionServiceImpl;

public class TicketReport {

	private Integer numero;

	private Date fechaDeCierrePorPago;

	private Double total;

	private Integer dni;

	private SucursalDTO sucursal;

	private String medioDePago;

	public TicketReport(FacturaDTO factura) {
		this.numero = factura.getIdFactura();
		this.dni = factura.getDni();
		this.fechaDeCierrePorPago = factura.getFechaDeCierrePorPago();
		this.total = factura.getTotal();
		this.sucursal = SessionServiceImpl.getInstance().getActiveSession().getSucursal();
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer idFactura) {
		this.numero = idFactura;
	}

	public Date getFecha() {
		return fechaDeCierrePorPago;
	}

	public void setFecha(Date fechaDeCierrePorPago) {
		this.fechaDeCierrePorPago = fechaDeCierrePorPago;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public JRDataSource getSucursal() {
		ArrayList<SucursalDTO> ret = new ArrayList<>();
		ret.add(sucursal);
		return new JRBeanCollectionDataSource(ret);
	}

	public void setSucursal(SucursalDTO sucursal) {
		this.sucursal = sucursal;
	}

	public String getMedioDePago() {
		return medioDePago;
	}

	public void setMedioDePago(String medioDePago) {
		this.medioDePago = medioDePago;
	}
}
