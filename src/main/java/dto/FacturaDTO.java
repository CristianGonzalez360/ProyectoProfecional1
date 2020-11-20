package dto;

import java.util.Date;
import java.util.List;

public class FacturaDTO {
	
	private Integer idFactura;
	
	private Integer idOrdenDeTrabajo;
	
	private Date fechaDeAlta;
	
	private Date fechaDeCierrePorPago;
	
	private Double total;
	
	private List<PresupuestoDTO> presupuestosFacturados;
			
	private String estado;
		
	private ClienteDTO cliente;
//	**jere**
//	private List<RepuestosCarritoDTO> repuestos;
	public FacturaDTO() {
		this.estado = "IMPAGA";
	}
		
	public Integer getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}

	public Integer getIdOrdenDeTrabajo() {
		return idOrdenDeTrabajo;
	}

	public void setIdOrdenDeTrabajo(Integer idOrdenDeTrabajo) {
		this.idOrdenDeTrabajo = idOrdenDeTrabajo;
	}

	public Date getFechaDeAlta() {
		return fechaDeAlta;
	}

	public void setFechaDeAlta(Date fechaDeAlta) {
		this.fechaDeAlta = fechaDeAlta;
	}

	public Date getFechaDeCierrePorPago() {
		return fechaDeCierrePorPago;
	}

	public void setFechaDeCierrePorPago(Date fechaDeCierrePorPago) {
		this.fechaDeCierrePorPago = fechaDeCierrePorPago;
	}

	public boolean estaPagada() {
		return fechaDeAlta == null? false: true;
	}
	@Override
	public String toString() {
		return "FacturaDTO [idFactura=" + idFactura + ", idOrdenDeTrabajo=" + idOrdenDeTrabajo + ", fechaDeAlta="
				+ fechaDeAlta + ", fechaDeCierrePorPago=" + fechaDeCierrePorPago + ", total=" + total
				+ ", presupuestosFacturados=" + presupuestosFacturados + ", estado=" + estado + "]";
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<PresupuestoDTO> getPresupuestosFacturados() {
		return presupuestosFacturados;
	}

	public void setPresupuestosFacturados(List<PresupuestoDTO> presupuestosFacturados) {
		this.presupuestosFacturados = presupuestosFacturados;
	}
}
