package dto;

import java.util.Date;

public class PedidoVehiculoDTO {

	private Integer idPedidoVehiculo;
	private Date fechaPedido;
	private Date fechaIngreso;
	private Integer idUsuPedido;
	private Integer idUsuIngreso;
	private Integer idVentaVehiculo;

	public PedidoVehiculoDTO() {
	}

	public PedidoVehiculoDTO(Date fechaPedido, Date fechaIngreso, Integer idUsuPedido, Integer idUsuIngreso,
			Integer idVentaVehiculo) {
		super();
		this.fechaPedido = fechaPedido;
		this.fechaIngreso = fechaIngreso;
		this.idUsuPedido = idUsuPedido;
		this.idUsuIngreso = idUsuIngreso;
		this.idVentaVehiculo = idVentaVehiculo;
	}

	public PedidoVehiculoDTO makeTestDTO() {
		PedidoVehiculoDTO ret = new PedidoVehiculoDTO();
		ret.setFechaPedido(new Date());
		ret.setFechaIngreso(new Date());
		return ret;
	}

	public Integer getIdPedidoVehiculo() {
		return idPedidoVehiculo;
	}

	public void setIdPedidoVehiculo(Integer idPedidoVehiculo) {
		this.idPedidoVehiculo = idPedidoVehiculo;
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

	public Integer getIdVentaVehiculo() {
		return idVentaVehiculo;
	}

	public void setIdVentaVehiculo(Integer idVentaVehiculo) {
		this.idVentaVehiculo = idVentaVehiculo;
	}

	@Override
	public String toString() {
		return "PedidoVehiculoDTO [idPedidoVehiculo=" + idPedidoVehiculo + ", fechaPedido=" + fechaPedido
				+ ", fechaIngreso=" + fechaIngreso + ", idUsuPedido=" + idUsuPedido + ", idUsuIngreso=" + idUsuIngreso
				+ ", idVentaVehiculo=" + idVentaVehiculo + "]";
	}

}
