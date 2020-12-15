package presentacion.views.utils;

import java.util.Date;

public class VentasReport {

	private Date fechaDeVenta;
	private String marca;
	private String familia;
	private String linea;
	private String color;

	public VentasReport(Date fechaDeVenta, String marca, String familia, String linea, String color) {
		super();
		this.fechaDeVenta = fechaDeVenta;
		this.marca = marca;
		this.familia = familia;
		this.linea = linea;
		this.color = color;
	}

	public Date getFechaDeVenta() {
		return fechaDeVenta;
	}

	public void setFechaDeVenta(Date fechaDeVenta) {
		this.fechaDeVenta = fechaDeVenta;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "VentasReport [fechaDeVenta=" + fechaDeVenta + ", marca=" + marca + ", familia=" + familia + ", linea="
				+ linea + ", color=" + color + "]";
	}

}
