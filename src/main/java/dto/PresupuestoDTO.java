package dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PresupuestoDTO {
	
	  private Integer idPresupuesto;
	
	  private Integer idOT;
	  
	  private Integer idUsuAltaPresu;
	  
	  private Integer idUsuCierrePresu;
	  
	  private Integer idUsuRegPago;
	  
	  private Integer idPago;
	  
	  private Date fechaAltaPresu;
	  
	  private String comentarioAltaPresu;
	  
	  private Date fechaCierrePresu;
	  
	  private String comentarioCierrePresu;
	  
	  private Date fechaAprobacion;
	  
	  private Date fechaRechazo;
	  
	  private List<TrabajoPlanificadoDTO> trabajos;
	  
	  private List<RepuestoPlanificadoDTO> repuestos;
	  
	  public PresupuestoDTO () {
		  this.trabajos = new ArrayList<>();
	  }

	public Integer getIdPresupuesto() {
		return idPresupuesto;
	}
	public void setIdPresupuesto(Integer idPresupuesto) {
		this.idPresupuesto = idPresupuesto;
	}
	public Integer getIdOT() {
		return idOT;
	}
	public void setIdOT(Integer idOT) {
		this.idOT = idOT;
	}
	public Integer getIdUsuAltaPresu() {
		return idUsuAltaPresu;
	}
	public void setIdUsuAltaPresu(Integer idUsuAltaPresu) {
		this.idUsuAltaPresu = idUsuAltaPresu;
	}
	public Integer getIdUsuCierrePresu() {
		return idUsuCierrePresu;
	}
	public void setIdUsuCierrePresu(Integer idUsuCierrePresu) {
		this.idUsuCierrePresu = idUsuCierrePresu;
	}
	public Integer getIdUsuRegPago() {
		return idUsuRegPago;
	}
	public void setIdUsuRegPago(Integer idUsuRegPago) {
		this.idUsuRegPago = idUsuRegPago;
	}
	public Integer getIdPago() {
		return idPago;
	}
	public void setIdPago(Integer idPago) {
		this.idPago = idPago;
	}
	public Date getFechaAltaPresu() {
		return fechaAltaPresu;
	}
	public void setFechaAltaPresu(Date fechaAltaPresu) {
		this.fechaAltaPresu = fechaAltaPresu;
	}
	public String getComentarioAltaPresu() {
		return comentarioAltaPresu;
	}
	public void setComentarioAltaPresu(String comentarioAltaPresu) {
		this.comentarioAltaPresu = comentarioAltaPresu;
	}
	public Date getFechaCierrePresu() {
		return fechaCierrePresu;
	}
	public void setFechaCierrePresu(Date fechaCierrePresu) {
		this.fechaCierrePresu = fechaCierrePresu;
	}
	public String getComentarioCierrePresu() {
		return comentarioCierrePresu;
	}
	public void setComentarioCierrePresu(String comentarioCierrePresu) {
		this.comentarioCierrePresu = comentarioCierrePresu;
	}
	public Date getFechaAprobacion() {
		return fechaAprobacion;
	}
	public void setFechaAprobacion(Date fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}
	public Date getFechaRechazo() {
		return fechaRechazo;
	}
	public void setFechaRechazo(Date fechaRechazo) {
		this.fechaRechazo = fechaRechazo;
	}
	
	@Override
	public String toString() {
		return "PresupuestoDTO [idPresupuesto=" + idPresupuesto + ", idOT=" + idOT + ", idUsuAltaPresu="
				+ idUsuAltaPresu + ", idUsuCierrePresu=" + idUsuCierrePresu + ", idUsuRegPago=" + idUsuRegPago
				+ ", idPago=" + idPago + ", fechaAltaPresu=" + fechaAltaPresu + ", comentarioAltaPresu="
				+ comentarioAltaPresu + ", fechaCierrePresu=" + fechaCierrePresu + ", comentarioCierrePresu="
				+ comentarioCierrePresu + ", fechaAprobacion=" + fechaAprobacion + ", fechaRechazo=" + fechaRechazo
				+ "]";
	}

	public void agregarTrabajo(TrabajoPlanificadoDTO trabajo) {
		this.trabajos.add(trabajo);
	}
	
	public void agregarRepuestos(RepuestoPlanificadoDTO repuesto) {
		this.repuestos.add(repuesto);
	}

	public List<TrabajoPlanificadoDTO> getTrabajos() {
		return trabajos;
	}

	public void setTrabajos(List<TrabajoPlanificadoDTO> trabajos) {
		this.trabajos = trabajos;
	}

	public List<RepuestoPlanificadoDTO> getRepuestos() {
		return repuestos;
	}

	public void setRepuestos(List<RepuestoPlanificadoDTO> repuestos) {
		this.repuestos = repuestos;
	}
}
