package presentacion.views.utils;

import java.util.Date;

public class IngresosReport {

	//factura + total y ventaDevehiculo idPagoVentaVN Pagos   precioVEnta venta de vcehiculo,
	private Double montoTotal;
	private Date fechaDePago;
	private String descripcion;
	public Double getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}
	public Date getFechaDePago() {
		return fechaDePago;
	}
	public void setFechaDePago(Date fechaDePago) {
		this.fechaDePago = fechaDePago;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		return "IngresosReport [montoTotal=" + montoTotal + ", fechaDePago=" + fechaDePago + ", descripcion="
				+ descripcion + "]";
	}

}
