package dto.temporal;

import java.util.LinkedList;
import java.util.List;

import dto.validators.StringValidator;

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
		ret.addAll(new StringValidator(codigoRepuesto).notBlank("El código es obligatorio.").positiveInteger("El código debe ser un número.").validate());
		ret.addAll(new StringValidator(precioRepuesto).notBlank("El precio es obligatorio.").positiveDouble("El precio debe ser un número.").validate());
		ret.addAll(new StringValidator(marcaRepuesto).notBlank("La marca es obligatoria.").validate());
		ret.addAll(new StringValidator(descripcionRepuesto).notBlank("La descripción es obligatoria.").validate());
		ret.addAll(new StringValidator(stockRepuesto).notBlank("El stock es obligatorio.").positiveInteger("El estock debe ser un número.").validate());
		ret.addAll(new StringValidator(fabricante).notBlank("El fabricante es obligatorio.").validate());
		ret.addAll(new StringValidator(stockMinimo).notBlank("El stock mínimo es obligatorio.").positiveInteger("El estock mínimo debe ser un número.").validate());
		ret.addAll(new StringValidator(precioCompra).notBlank("El precio de compra es obligatorio.").positiveDouble("El precio debe ser un número.").validate());
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
