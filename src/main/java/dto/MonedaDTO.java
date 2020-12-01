package dto;

public class MonedaDTO {
	
	private Integer idMoneda;
	
	private String nombre;
	
	private String simbolo;
	
	private Double cotizacionDolar;
	
	public MonedaDTO() {
	}
	
	public Integer getIdMoneda() {
		return idMoneda;
	}
	
	public void setIdMoneda(Integer idMoneda) {
		this.idMoneda = idMoneda;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getSimbolo() {
		return simbolo;
	}
	
	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}
	
	public Double getCotizacionDolar() {
		return cotizacionDolar;
	}
	
	public void setCotizacionDolar(Double cotizacionDolar) {
		this.cotizacionDolar = cotizacionDolar;
	}

	@Override
	public String toString() {
		return "MonedaDTO [idMoneda=" + idMoneda + ", nombre=" + nombre + ", simbolo=" + simbolo + ", cotizacionDolar="
				+ cotizacionDolar + "]";
	}
}
