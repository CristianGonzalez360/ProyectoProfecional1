package dto;

import java.util.Date;

public class VentaVehiculoDTO {
	
	private Integer idVentaVehiculo;
	
	private Integer idUsuVentaVN ;
	
	private Integer idUsuPedido;
	
	private Integer idUsuLlegada;
	
	private Integer idPagoVentaVN;
	
	private Date fechaVentaVN;
	
	private Date fechaEntregaReal;
	
	private String fabricante;
	
	private Double comisionCobrada;
	
	private Double precioVenta;
	
	private String financiera;
	
	private Integer nroCuotas;
	
	private Double montoCuota;
	
	private Integer idVehiculo;
	
	private Integer idCliente;
	
	private Integer idUsuEntrega;
	
	private Integer idSucursalVenta;
	
	public VentaVehiculoDTO() {
	}
	
	public Integer getIdVentaVehiculo() {
		return idVentaVehiculo;
	}
	public void setIdVentaVehiculo(Integer idVentaVehiculo) {
		this.idVentaVehiculo = idVentaVehiculo;
	}
	public Integer getIdUsuVentaVN() {
		return idUsuVentaVN;
	}
	public void setIdUsuVentaVN(Integer idUsuVentaVN) {
		this.idUsuVentaVN = idUsuVentaVN;
	}
	public Integer getIdUsuPedido() {
		return idUsuPedido;
	}
	public void setIdUsuPedido(Integer idUsuPedido) {
		this.idUsuPedido = idUsuPedido;
	}
	public Integer getIdUsuLlegada() {
		return idUsuLlegada;
	}
	public void setIdUsuLlegada(Integer idUsuLlegada) {
		this.idUsuLlegada = idUsuLlegada;
	}
	public Integer getIdPagoVentaVN() {
		return idPagoVentaVN;
	}
	public void setIdPagoVentaVN(Integer idPagoVentaVN) {
		this.idPagoVentaVN = idPagoVentaVN;
	}
	public Date getFechaVentaVN() {
		return fechaVentaVN;
	}
	public void setFechaVentaVN(Date fechaVentaVN) {
		this.fechaVentaVN = fechaVentaVN;
	}
	public Date getFechaEntregaReal() {
		return fechaEntregaReal;
	}
	public void setFechaEntregaReal(Date fechaEntregaReal) {
		this.fechaEntregaReal = fechaEntregaReal;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public Double getComisionCobrada() {
		return comisionCobrada;
	}
	public void setComisionCobrada(Double comisionCobrada) {
		this.comisionCobrada = comisionCobrada;
	}
	public Double getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}
	public Integer getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(Integer idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public Integer getIdUsuEntrega() {
		return idUsuEntrega;
	}
	public void setIdUsuEntrega(Integer idUsuEntrega) {
		this.idUsuEntrega = idUsuEntrega;
	}

	public String getFinanciera() {
		return financiera;
	}

	public void setFinanciera(String financiera) {
		this.financiera = financiera;
	}

	public Integer getNroCuotas() {
		return nroCuotas;
	}

	public void setNroCuotas(Integer nroCuotas) {
		this.nroCuotas = nroCuotas;
	}

	public Double getMontoCuota() {
		return montoCuota;
	}

	public void setMontoCuota(Double montoCuota) {
		this.montoCuota = montoCuota;
	}

	public Integer getIdSucursalVenta() {
		return idSucursalVenta;
	}

	public void setIdSucursalVenta(Integer idSucursalVenta) {
		this.idSucursalVenta = idSucursalVenta;
	}

	@Override
	public String toString() {
		return "VentaVehiculoDTO [idVentaVehiculo=" + idVentaVehiculo + ", idUsuVentaVN=" + idUsuVentaVN
				+ ", idUsuPedido=" + idUsuPedido + ", idUsuLlegada=" + idUsuLlegada + ", idPagoVentaVN=" + idPagoVentaVN
				+ ", fechaVentaVN=" + fechaVentaVN + ", fechaEntregaReal=" + fechaEntregaReal + ", fabricante="
				+ fabricante + ", comisionCobrada=" + comisionCobrada + ", precioVenta=" + precioVenta + ", financiera="
				+ financiera + ", nroCuotas=" + nroCuotas + ", montoCuota=" + montoCuota + ", idVehiculo=" + idVehiculo
				+ ", idCliente=" + idCliente + ", idUsuEntrega=" + idUsuEntrega + ", idSucursalVenta=" + idSucursalVenta
				+ "]";
	}
}
