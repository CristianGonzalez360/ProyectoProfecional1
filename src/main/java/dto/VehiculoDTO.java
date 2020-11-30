package dto;

import java.util.Date;

import dto.taller.FichaTecnicaVehiculoDTO;

public class VehiculoDTO {

	private Integer idVehiculo;
	
	private Double precioVenta;
	
	private Integer  idFichaTecnica;

	private String marca;
	
	private String familia;
	
	private String linea;
	
	private String color;
	
	private Date fechaIngreso;
	
	private boolean disponible;
	
	private boolean  usado;
	
	private Integer idCompra;
		
	private Integer idCaracteristicas;
	
	private Integer idSucursal;
	
	private FichaTecnicaVehiculoDTO fichaTecnica;
	
	public VehiculoDTO() {
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

	public Integer getIdCaracteristicas() {
		return idCaracteristicas;
	}

	public void setIdCaracteristicas(Integer idCaracteristicas) {
		this.idCaracteristicas = idCaracteristicas;
	}

	public Integer getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public boolean isUsado() {
		return usado;
	}

	public void setUsado(boolean usado) {
		this.usado = usado;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "VehiculoDTO [idVehiculo=" + idVehiculo + ", precioVenta=" + precioVenta + ", idFichaTecnica="
				+ idFichaTecnica + ", marca=" + marca + ", familia=" + familia + ", linea=" + linea + ", color=" + color
				+ ", fechaIngreso=" + fechaIngreso + ", disponible=" + disponible + ", usado=" + usado + ", idCompra="
				+ idCompra + ", idCaracteristicas=" + idCaracteristicas + ", idSucursal=" + idSucursal
				+ ", fichaTecnica=" + fichaTecnica + "]";
	}
}
