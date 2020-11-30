package dto.temporal;

public class OutputConsultaVehiculoEnVentaDTO {
	
	private String codigo;
	
	private String marca;
	
	private String familia;
	
	private String linea;
	
	private String sucursal;
	
	private String color;
	
	private String precio;

	public OutputConsultaVehiculoEnVentaDTO() {
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	@Override
	public String toString() {
		return "OutputConsultaVehiculoEnVentaDTO [codigo=" + codigo + ", marca=" + marca + ", familia=" + familia
				+ ", linea=" + linea + ", sucursal=" + sucursal + ", color=" + color + ", precio=" + precio + "]";
	}
}
