package dto.temporal;

import java.util.LinkedList;
import java.util.List;

public class IngresoStockDTO {

	private String cantidad;
	private String precioCompra;
	private String precioVenta;

	public IngresoStockDTO() {

	}

	public List<String> validate() {
		List<String> ret = new LinkedList<>();
		// TODO
		return ret;
	}

	public String getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(String precioCompra) {
		this.precioCompra = precioCompra;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(String precioVenta) {
		this.precioVenta = precioVenta;
	}

}
