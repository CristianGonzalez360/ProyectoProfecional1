package dto;

import java.util.Date;

public class CompraVehiculoDTO {

	private Integer idCompraVehiculo;
	private Integer idVehiculo ;
	private Double PrecioCompra;
	private Date fechaCompra;
	private Integer idUsuCompra;
	
	public CompraVehiculoDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getIdCompraVehiculo() {
		return idCompraVehiculo;
	}
	public void setIdCompraVehiculo(Integer idCompraVehiculo) {
		this.idCompraVehiculo = idCompraVehiculo;
	}
	public Integer getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(Integer idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	public Double getPrecioCompra() {
		return PrecioCompra;
	}
	public void setPrecioCompra(Double precioCompra) {
		PrecioCompra = precioCompra;
	}
	public Date getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	public Integer getIdUsuCompra() {
		return idUsuCompra;
	}
	public void setIdUsuCompra(Integer idUsuCompra) {
		this.idUsuCompra = idUsuCompra;
	}
}
