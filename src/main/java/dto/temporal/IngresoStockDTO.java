package dto.temporal;

import java.util.LinkedList;
import java.util.List;

import dto.validators.StringValidator;

public class IngresoStockDTO {

	private String cantidad;
	private String precioCompra;
	private String precioVenta;

	public IngresoStockDTO() {

	}

	public List<String> validate() {
		List<String> ret = new LinkedList<>();
		ret.addAll(new StringValidator(cantidad).notBlank("La cantidad es obligatoria.").positiveInteger("La cantidad debe ser un número.").validate());
		ret.addAll(new StringValidator(precioCompra).notBlank("El precio de compra es obligatorio.").positiveDouble("El precio de compra debe ser un número.").validate());
		ret.addAll(new StringValidator(precioVenta).notBlank("El precio de venta es obligatorio.").positiveDouble("El precio de venta debe ser un número.").validate());
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
