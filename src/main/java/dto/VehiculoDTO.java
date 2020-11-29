package dto;

import java.util.Date;

public class VehiculoDTO {

	private Integer idVehiculo;
	
	private Double precioVenta;
	
	private Integer  idCaracteristicas;
	
	private Date fechaIngreso;
	
	private boolean disponible;
	
	private Boolean  usado;
	
	private Integer idCompra;
	
	private Integer	idSucursal;
	
	private CaracteristicasVehiculoDTO caracteristicasVehiculo;
	
	public VehiculoDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(Integer idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	public Double getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}
	public Integer getIdCaracteristicas() {
		return idCaracteristicas;
	}
	public void setIdCaracteristicas(Integer IdCaracteristicas) {
		this.idCaracteristicas = IdCaracteristicas;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public Boolean getUsado() {
		return usado;
	}
	public void setUsado(Boolean usado) {
		this.usado = usado;
	}
	public Integer getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(Integer idCompra) {
		this.idCompra = idCompra;
	}

	public CaracteristicasVehiculoDTO getCaracteristicasVehiculo() {
		return caracteristicasVehiculo;
	}

	public void setCaracteristicasVehiculo(CaracteristicasVehiculoDTO caracteristicasVehiculo) {
		this.caracteristicasVehiculo = caracteristicasVehiculo;
	}

	public Integer getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}
}
