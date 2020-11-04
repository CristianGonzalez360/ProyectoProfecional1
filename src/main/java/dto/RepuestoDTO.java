package dto;

public class RepuestoDTO {
	private Integer idRepuesto;
	private Integer codigoRepuesto;
	private Double precioRepuesto;
	private String marcaRepuesto;
	private String descripcionRepuesto;
	private Integer stockRepuesto;
	private String fabricante;
	private Integer stockMinimo;
	
	public RepuestoDTO () {
		
	}
	
	public Integer getIdRepuesto() {
		return idRepuesto;
	}
	public void setIdRepuesto(Integer idRepuesto) {
		this.idRepuesto = idRepuesto;
	}
	public Integer getCodigoRepuesto() {
		return codigoRepuesto;
	}
	public void setCodigoRepuesto(Integer codigoRepuesto) {
		this.codigoRepuesto = codigoRepuesto;
	}
	public Double getPrecioRepuesto() {
		return precioRepuesto;
	}
	public void setPrecioRepuesto(Double precioRepuesto) {
		this.precioRepuesto = precioRepuesto;
	}
	public String getMarcaRepuesto() {
		return marcaRepuesto;
	}
	public void setMarcaRepuesto(String marcaRepuesto) {
		this.marcaRepuesto = marcaRepuesto;
	}
	public String getDescripcionRepuesto() {
		return descripcionRepuesto;
	}
	public void setDescripcionRepuesto(String descripcionRepuesto) {
		this.descripcionRepuesto = descripcionRepuesto;
	}
	public Integer getStockRepuesto() {
		return stockRepuesto;
	}
	public void setStockRepuesto(Integer stockRepuesto) {
		this.stockRepuesto = stockRepuesto;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public Integer getStockMinimo() {
		return stockMinimo;
	}
	public void setStockMinimo(Integer stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	@Override
	public String toString() {
		return "RepuestoDTO [idRepuesto=" + idRepuesto + ", codigoRepuesto=" + codigoRepuesto + ", precioRepuesto="
				+ precioRepuesto + ", marcaRepuesto=" + marcaRepuesto + ", descripcionRepuesto=" + descripcionRepuesto
				+ ", stockRepuesto=" + stockRepuesto + ", fabricante=" + fabricante + ", stockMinimo=" + stockMinimo
				+ "]";
	}
	
}
