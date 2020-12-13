package dto.taller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PresupuestoDTO {

	private Integer idPresupuesto;

	private Integer idOT;

	private Integer idFactura;

	private Integer idUsuAltaPresu;

	private Integer idUsuCierrePresu;

	private Integer idUsuRegPago;

	private Integer idPago;

	private Date fechaAltaPresu;

	private String comentarioAltaPresu;

	private Date fechaCierrePresu;

	private String comentarioRechazo;

	private Date fechaAprobacion;

	private EstadoPresupuesto estado;

	private List<TrabajoPresupuestadoDTO> trabajos;

	private List<RepuestoPlanificadoDTO> repuestos;

	public PresupuestoDTO() {
		estado = EstadoPresupuesto.PENDIENTE;
		this.trabajos = new ArrayList<>();
		this.repuestos = new ArrayList<>();
		this.fechaAltaPresu = new Date();
	}

	public PresupuestoDTO(MantenimientoDTO mantenimiento) {
		estado = EstadoPresupuesto.PENDIENTE;
		this.comentarioAltaPresu = mantenimiento.getComentario();
		this.fechaAltaPresu = new Date();
		this.trabajos = new ArrayList<>();
		this.repuestos = new ArrayList<>();
		for(RepuestoMantenimientoDTO repuesto : mantenimiento.getRepuestos()) {
			agregarRepuestos(new RepuestoPlanificadoDTO(repuesto));
		}
		for(TrabajoMantenimientoDTO trabajo: mantenimiento.getTrabajos()) {
			agregarTrabajo(new TrabajoPresupuestadoDTO(trabajo));
		}
		
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

	public String getComentarioRechazo() {
		return comentarioRechazo;
	}

	public void setComentarioRechazo(String comentarioCierrePresu) {
		this.comentarioRechazo = comentarioCierrePresu;
	}

	public Date getFechaAprobacion() {
		return fechaAprobacion;
	}

	public void agregarTrabajo(TrabajoPresupuestadoDTO trabajo) {
		this.trabajos.add(trabajo);
	}

	public void agregarRepuestos(RepuestoPlanificadoDTO repuesto) {
		this.repuestos.add(repuesto);
	}

	public List<TrabajoPresupuestadoDTO> getTrabajos() {
		return trabajos;
	}

	public void setTrabajos(List<TrabajoPresupuestadoDTO> trabajos) {
		this.trabajos = trabajos;
	}

	public List<RepuestoPlanificadoDTO> getRepuestos() {
		return repuestos;
	}

	public void setRepuestos(List<RepuestoPlanificadoDTO> repuestos) {
		this.repuestos = repuestos;
	}

	public void borrarRepuestosPlanificados() {
		this.repuestos.clear();
	}

	public Double getPrecio() {
		Double ret = 0.0;
		for (RepuestoPlanificadoDTO dto : repuestos) {
			ret += dto.getRepuesto().getPrecioRepuesto() * dto.getCantRequerida();
		}
		for (TrabajoPresupuestadoDTO dto : trabajos) {
			ret += dto.getPrecioTrabajo();
		}
		return ret;
	}

	public void quitarTrabajo(int fila) {
		this.trabajos.remove(fila);
	}

	public void quitarRepuesto(int fila) {
		this.repuestos.remove(fila);
	}

	public EstadoPresupuesto getEstado() {
		return estado;
	}

	public void setEstado(EstadoPresupuesto estado) {
		this.estado = estado;
	}

	public void setFechaAprobacion(Date fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}

	@Override
	public String toString() {
		return "PresupuestoDTO [idPresupuesto=" + idPresupuesto + ", idOT=" + idOT + ", idFactura=" + idFactura
				+ ", idUsuAltaPresu=" + idUsuAltaPresu + ", idUsuCierrePresu=" + idUsuCierrePresu + ", idUsuRegPago="
				+ idUsuRegPago + ", idPago=" + idPago + ", fechaAltaPresu=" + fechaAltaPresu + ", comentarioAltaPresu="
				+ comentarioAltaPresu + ", fechaCierrePresu=" + fechaCierrePresu + ", comentarioCierrePresu="
				+ comentarioRechazo + ", fechaAprobacion=" + fechaAprobacion + ", estado=" + estado + ", trabajos="
				+ trabajos + ", repuestos=" + repuestos + "]";
	}

	public Integer getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}

	public boolean estaRealizado() {
		return estado.equals(EstadoPresupuesto.REALIZADO);
	}

	public boolean estaRechazado() {
		return estado.equals(EstadoPresupuesto.RECHAZADO);
	}

	public boolean estaPendiente() {
		return estado.equals(EstadoPresupuesto.PENDIENTE);
	}
}
