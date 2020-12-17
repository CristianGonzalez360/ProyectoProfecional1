package dto;

import java.util.Date;

public class CompraRepuestoDTO {

	private Integer idCompraRepuesto;
	private Integer codigoRepuesto;
	private Double precioCompra;
	private Date fechaCompra;
	private Integer cantidad;
	
	public CompraRepuestoDTO() {
		this.fechaCompra = new Date();
	}

	public Integer getIdCompraRepuesto() {
		return idCompraRepuesto;
	}

	public void setIdCompraRepuesto(Integer idCompraRepuesto) {
		this.idCompraRepuesto = idCompraRepuesto;
	}

	public Integer getCodigoRepuesto() {
		return codigoRepuesto;
	}

	public void setCodigoRepuesto(Integer idRepuesto) {
		this.codigoRepuesto = idRepuesto;
	}

	public Double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(Double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
}
