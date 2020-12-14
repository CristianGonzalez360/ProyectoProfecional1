package presentacion.views.utils;

public class ReporteVentas {

	private String marca;
	private String modelo;
	private String linea;
	private String color;

	public ReporteVentas(String marca, String modelo, String linea, String color) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.linea = linea;
		this.color = color;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
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

}
