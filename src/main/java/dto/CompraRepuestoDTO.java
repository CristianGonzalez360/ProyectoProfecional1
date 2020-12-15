package dto;

import java.util.Date;

public class CompraRepuestoDTO {

	private Integer idCompraRepuesto;
	private Integer idRepuesto;
	private Double precioCompra;
	private Date fechaCompra;
	
	public CompraRepuestoDTO() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdCompraRepuesto() {
		return idCompraRepuesto;
	}

	public void setIdCompraRepuesto(Integer idCompraRepuesto) {
		this.idCompraRepuesto = idCompraRepuesto;
	}

	public Integer getIdRepuesto() {
		return idRepuesto;
	}

	public void setIdRepuesto(Integer idRepuesto) {
		this.idRepuesto = idRepuesto;
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
}
