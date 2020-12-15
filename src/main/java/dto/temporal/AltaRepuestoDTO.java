package dto.temporal;

import java.util.LinkedList;
import java.util.List;

public class AltaRepuestoDTO {

	private String codigoRepuesto;

	private String precioRepuesto;

	private String marcaRepuesto;

	private String descripcionRepuesto;

	private String stockRepuesto;

	private String fabricante;

	private String stockMinimo;

	private Boolean garantia;

	private String precioCompra;

	public AltaRepuestoDTO() {

	}

	public List<String> validate() {
		List<String> ret = new LinkedList<>();
		// TODO Auto-generated constructor stub
		return ret;
	}

	public String getCodigoRepuesto() {
		return codigoRepuesto;
	}

	public void setCodigoRepuesto(String codigoRepuesto) {
		this.codigoRepuesto = codigoRepuesto;
	}

	public String getPrecioRepuesto() {
		return precioRepuesto;
	}

	public void setPrecioRepuesto(String precioRepuesto) {
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

	public String getStockRepuesto() {
		return stockRepuesto;
	}

	public void setStockRepuesto(String stockRepuesto) {
		this.stockRepuesto = stockRepuesto;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(String stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public String getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(String precioCompra) {
		this.precioCompra = precioCompra;
	}

	public Boolean getGarantia() {
		return garantia;
	}

	public void setGarantia(Boolean garantia) {
		this.garantia = garantia;
	}
}
