package dto.temporal;

import dto.PedidoVehiculoDTO;
import dto.SucursalDTO;
import dto.VehiculoDTO;
import dto.VentaVehiculoDTO;

public class VehiculoParaEntregar {

	private VehiculoDTO vehiculo;
	private SucursalDTO sucursal;
	private boolean estaPedido;
	private boolean ingresado;
	private VentaVehiculoDTO venta;
	private PedidoVehiculoDTO pedido;
	
	public VehiculoParaEntregar() {
		// TODO Auto-generated constructor stub
	}
	
	public VehiculoDTO getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(VehiculoDTO vehiculo) {
		this.vehiculo = vehiculo;
	}
	public SucursalDTO getSucursal() {
		return sucursal;
	}
	public void setSucursal(SucursalDTO sucursal) {
		this.sucursal = sucursal;
	}
	public boolean isPedido() {
		return estaPedido;
	}
	public void setPedido(boolean pedido) {
		this.estaPedido = pedido;
	}
	public boolean isIngresado() {
		return ingresado;
	}
	public void setIngresado(boolean ingresado) {
		this.ingresado = ingresado;
	}
	public VentaVehiculoDTO getVenta() {
		return venta;
	}
	public void setVenta(VentaVehiculoDTO venta) {
		this.venta = venta;
	}

	public PedidoVehiculoDTO getPedido() {
		return this.pedido;
	}

	public void setPedido(PedidoVehiculoDTO pedido) {
		this.pedido = pedido;
	}
}
