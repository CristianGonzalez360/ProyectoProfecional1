package dto.taller;

import java.util.Date;

public class TrabajoPresupuestadoDTO {

	private Integer idTrabajoPresu;

	private Integer idPresupuesto;

	private String descripcionTrabajo;

	private Double precioTrabajo;

	private Date fechaAlta;

	private Date fechaCierre;

	private Integer tiempoEstTrabajo;

	public TrabajoPresupuestadoDTO() {

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

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	@Override
	public String toString() {
		return "TrabajoPlanificadoDTO [idTrabajoPresu=" + idTrabajoPresu + ", idPresupuesto=" + idPresupuesto
				+ ", descripcionTrabajo=" + descripcionTrabajo + ", precioTrabajo=" + precioTrabajo
				+ ", tiempoEstTrabajo=" + tiempoEstTrabajo + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean ret = false;
		if(obj instanceof TrabajoPresupuestadoDTO) {
			ret = this.getIdTrabajoPresu().equals(((TrabajoPresupuestadoDTO) obj).getIdTrabajoPresu());
		}
		return ret;
	}
}
