package dto;

import java.util.Date;

public class OrdenDeTrabajoDTO {
	
	private Integer idOrdenTrabajo;
	
	private String tipoOrdeTrabajo;
	
	private UsuarioDTO idUsuarioAlta;
	
	private	Date fechaDeAlta;
	
	private String trabajoSolicitado;
	
	private String trabajoSujerido;
	
	private VehiculoConOrdenDeTrabajoDTO vehiculo;

	private Date fechaEntregado;

	public OrdenDeTrabajoDTO() {
		
	}
	
	public Integer getIdOrdenTrabajo() {
		return idOrdenTrabajo;
	}

	public void setIdOrdenTrabajo(Integer idOrdenTrabajo) {
		this.idOrdenTrabajo = idOrdenTrabajo;
	}

	public String getTipoOrdeTrabajo() {
		return tipoOrdeTrabajo;
	}

	public void setTipoOrdeTrabajo(String tipoOrdeTrabajo) {
		this.tipoOrdeTrabajo = tipoOrdeTrabajo;
	}

	public UsuarioDTO getIdUsuarioAlta() {
		return idUsuarioAlta;
	}

	public void setIdUsuarioAlta(UsuarioDTO idUsuarioAlta) {
		this.idUsuarioAlta = idUsuarioAlta;
	}

	public Date getFechaDeAlta() {
		return fechaDeAlta;
	}

	public void setFechaDeAlta(Date fechaDeAlta) {
		this.fechaDeAlta = fechaDeAlta;
	}

	public String getTrabajoSolicitado() {
		return trabajoSolicitado;
	}

	public void setTrabajoSolicitado(String trabajoSolicitado) {
		this.trabajoSolicitado = trabajoSolicitado;
	}

	public String getTrabajoSujerido() {
		return trabajoSujerido;
	}

	public void setTrabajoSujerido(String trabajoSujerido) {
		this.trabajoSujerido = trabajoSujerido;
	}

	public VehiculoConOrdenDeTrabajoDTO getIdVehiculoConOrdenTrabajo() {
		return vehiculo;
	}

	public void setFechaEntregado(Date fecha) {
		this.fechaEntregado = fecha;
	}	
	
	public Date getFechaEntregado() {
		return this.fechaEntregado;
	}

	public void setVehiculo(VehiculoConOrdenDeTrabajoDTO vehiculo) {
		this.vehiculo = vehiculo;
	}
	
	@Override
	public String toString() {
		return "OrdenDeTrabajoDTO [idOrdenTrabajo=" + idOrdenTrabajo + ", tipoOrdeTrabajo=" + tipoOrdeTrabajo
				+ ", idUsuarioAlta=" + idUsuarioAlta + ", fechaDeAlta=" + fechaDeAlta + ", trabajoSolicitado="
				+ trabajoSolicitado + ", trabajoSujerido=" + trabajoSujerido + ", idVehiculoConOrdenTrabajo="
				+ vehiculo + "]";
	}
}
