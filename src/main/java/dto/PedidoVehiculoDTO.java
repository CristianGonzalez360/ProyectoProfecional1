package dto;

import java.util.Date;

public class PedidoVehiculoDTO {

	private Integer idPedidoVehiculo;
	private Integer  idVehiculoReservado;
	private Date fechaPedido;
	private Date fechaIngreso;
	private Integer  idUsuPedido;
	private Integer  idUsuIngreso;
	private Integer idCliente;
	
	public PedidoVehiculoDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getIdPedidoVehiculo() {
		return idPedidoVehiculo;
	}
	public void setIdPedidoVehiculo(Integer idPedidoVehiculo) {
		this.idPedidoVehiculo = idPedidoVehiculo;
	}
	public Integer getIdVehiculoReservado() {
		return idVehiculoReservado;
	}
	public void setIdVehiculoReservado(Integer idVehiculoReservado) {
		this.idVehiculoReservado = idVehiculoReservado;
	}
	public Date getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public Integer getIdUsuPedido() {
		return idUsuPedido;
	}
	public void setIdUsuPedido(Integer idUsuPedido) {
		this.idUsuPedido = idUsuPedido;
	}
	public Integer getIdUsuIngreso() {
		return idUsuIngreso;
	}
	public void setIdUsuIngreso(Integer idUsuIngreso) {
		this.idUsuIngreso = idUsuIngreso;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
}
