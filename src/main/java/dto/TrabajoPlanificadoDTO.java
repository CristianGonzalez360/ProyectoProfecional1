package dto;

public class TrabajoPlanificadoDTO {
	
	private Integer idTrabajoPresu;
	
	private Integer idPresupuesto;
	
	private String descripcionTrabajo;
	
	private Double precioTrabajo;
	
	private Integer tiempoEstTrabajo;
	
	public TrabajoPlanificadoDTO () {
		
	}
	
	public Integer getIdTrabajoPresu() {
		return idTrabajoPresu;
	}
	public void setIdTrabajoPresu(Integer idTrabajoPresu) {
		this.idTrabajoPresu = idTrabajoPresu;
	}
	public Integer getIdPresupuesto() {
		return idPresupuesto;
	}
	public void setIdPresupuesto(Integer idPresupuesto) {
		this.idPresupuesto = idPresupuesto;
	}
	public String getDescripcionTrabajo() {
		return descripcionTrabajo;
	}
	public void setDescripcionTrabajo(String descripcionTrabajo) {
		this.descripcionTrabajo = descripcionTrabajo;
	}
	public Double getPrecioTrabajo() {
		return precioTrabajo;
	}
	public void setPrecioTrabajo(Double precioTrabajo) {
		this.precioTrabajo = precioTrabajo;
	}
	public Integer getTiempoEstTrabajo() {
		return tiempoEstTrabajo;
	}
	public void setTiempoEstTrabajo(Integer tiempoEstTrabajo) {
		this.tiempoEstTrabajo = tiempoEstTrabajo;
	}

	@Override
	public String toString() {
		return "TrabajoPlanificadoDTO [idTrabajoPresu=" + idTrabajoPresu + ", idPresupuesto=" + idPresupuesto
				+ ", descripcionTrabajo=" + descripcionTrabajo + ", precioTrabajo=" + precioTrabajo
				+ ", tiempoEstTrabajo=" + tiempoEstTrabajo + "]";
	}
	
}
