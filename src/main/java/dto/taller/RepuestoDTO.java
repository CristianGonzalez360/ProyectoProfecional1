package dto.taller;

import dto.temporal.AltaRepuestoDTO;

public class RepuestoDTO {

	private Integer idRepuesto;

	private Integer codigoRepuesto;

	private Double precioRepuesto;

	private String marcaRepuesto;

	private String descripcionRepuesto;

	private Integer stockRepuesto;

	private String fabricante;

	private Integer stockMinimo;

	private Boolean garantia;

	private Double precioCompra;

	public RepuestoDTO() {

	}

	public RepuestoDTO(AltaRepuestoDTO repuesto) {
		this.codigoRepuesto = Integer.parseInt(repuesto.getCodigoRepuesto());
		this.descripcionRepuesto = repuesto.getDescripcionRepuesto();
		this.fabricante = repuesto.getFabricante();
		this.garantia = repuesto.getGarantia();
		this.marcaRepuesto = repuesto.getMarcaRepuesto();
		this.precioCompra = Double.parseDouble(repuesto.getPrecioCompra());
		this.precioRepuesto = Double.parseDouble(repuesto.getPrecioRepuesto());
		this.stockMinimo = Integer.parseInt(repuesto.getStockMinimo());
		this.stockRepuesto = Integer.parseInt(repuesto.getStockRepuesto());
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

	public Boolean isGarantia() {
		return garantia;
	}

	public void setGarantia(Boolean garantia) {
		this.garantia = garantia;
	}

	public Double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(Double precioCompra) {
		this.precioCompra = precioCompra;
	}

	@Override
	public String toString() {
		return "RepuestoDTO [idRepuesto=" + idRepuesto + ", codigoRepuesto=" + codigoRepuesto + ", precioRepuesto="
				+ precioRepuesto + ", marcaRepuesto=" + marcaRepuesto + ", descripcionRepuesto=" + descripcionRepuesto
				+ ", stockRepuesto=" + stockRepuesto + ", fabricante=" + fabricante + ", stockMinimo=" + stockMinimo
				+ "]";
	}

}
