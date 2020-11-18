package dto;

import java.util.Date;

public class FacturaDTO {
	
	private Integer idFactura;
	
	private Integer idOrdenDeTrabajo;
	
	private Date fechaDeAlta;
	
	private Date fechaDeCierrePorPago;
	
	private Double total;
	
	private String estado;
	
	private Integer dni;
	
		
	public FacturaDTO() {
		super();
	}
	
	public boolean isAprobada() {
		return fechaDeCierrePorPago == null;
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

	@Override
	public String toString() {
		return "FacturaDTO [idFactura=" + idFactura + ", idOrdenDeTrabajo=" + idOrdenDeTrabajo + ", fechaDeAlta="
				+ fechaDeAlta + ", fechaDeCierrePorPago=" + fechaDeCierrePorPago + ", total=" + total + "]";
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

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}
}
