package dto.taller;

public class TrabajoMantenimientoDTO {

	private Integer idTrabajoMantenimiento;
	private Integer idMantenimiento;
	private String descripcionTrabajo;
	private Double precioTrabajo;
	private Integer tiempoEstTrabajo;

	public TrabajoMantenimientoDTO() {

	}

	public Integer getIdTrabajoMantenimiento() {
		return idTrabajoMantenimiento;
	}

	public void setIdTrabajoMantenimiento(Integer idTrabajoPresu) {
		this.idTrabajoMantenimiento = idTrabajoPresu;
	}

	public Integer getidMantenimiento() {
		return idMantenimiento;
	}

	public void setidMantenimiento(Integer idPresupuesto) {
		this.idMantenimiento = idPresupuesto;
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
		return "TrabajoPlanificadoDTO [idTrabajoPresu=" + idTrabajoMantenimiento + ", idPresupuesto=" + idMantenimiento
				+ ", descripcionTrabajo=" + descripcionTrabajo + ", precioTrabajo=" + precioTrabajo
				+ ", tiempoEstTrabajo=" + tiempoEstTrabajo + "]";
	}

	@Override
	public boolean equals(Object obj) {
		boolean ret = false;
		if (obj instanceof TrabajoMantenimientoDTO) {
			TrabajoMantenimientoDTO trabajo = (TrabajoMantenimientoDTO) obj;
			ret = this.getIdTrabajoMantenimiento().equals(trabajo.getIdTrabajoMantenimiento());
		}
		return ret;
	}
}
