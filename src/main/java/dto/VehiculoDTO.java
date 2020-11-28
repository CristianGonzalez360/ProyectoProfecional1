package dto;

import java.util.Date;

public class VehiculoDTO {

	private Integer idVehiculo;
	
	private Double precioVenta;
	
	private Integer  idFichaTecnica;
	
	private Date fechaIngreso;
	
	private boolean disponible;
	
	private Boolean  usado;
	
	private Integer idCompra;
	
	private FichaTecnicaVehiculoDTO fichaTecnica;
	
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
	public Integer getIdFichaTecnica() {
		return idFichaTecnica;
	}
	public void setIdFichaTecnica(Integer idFichaTecnica) {
		this.idFichaTecnica = idFichaTecnica;
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

	public FichaTecnicaVehiculoDTO getFichaTecnica() {
		return fichaTecnica;
	}

	public void setFichaTecnica(FichaTecnicaVehiculoDTO fichaTecnica) {
		this.fichaTecnica = fichaTecnica;
	}
}
