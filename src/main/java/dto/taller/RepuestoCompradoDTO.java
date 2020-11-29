package dto.taller;

public class RepuestoCompradoDTO {
	
	private int cantRequerida;
	private RepuestoDTO repuesto;
	private Integer idRepuestoComprado;
	private Integer idFacturaCarrito;

	public void setRepuesto(RepuestoDTO repuesto) {
		this.repuesto = repuesto;
	}

	public int getCantRequerida() {
		return cantRequerida;
	}

	public void setCantRequerida(int cantidad) {
		this.cantRequerida = cantidad;
	}

	public RepuestoDTO getRepuesto() {
		return repuesto;
	}

	public Integer getIdRepuestoComprado() {
		return idRepuestoComprado;
	}

	public void setIdRepuestoComprado(Integer idRepuestoComprado) {
		this.idRepuestoComprado = idRepuestoComprado;
	}

	public Integer getIdFacturaCarrito() {
		return idFacturaCarrito;
	}

	public void setIdFacturaCarrito(Integer idFacturaCarrito) {
		this.idFacturaCarrito = idFacturaCarrito;
	}

	public Double getPrecioTotal() {
		return repuesto.getPrecioRepuesto()*cantRequerida;
	}
	
	public String getDescripcion() {
		return repuesto.getDescripcionRepuesto();
	}
	
	public Double getPrecio() {
		return repuesto.getPrecioRepuesto();
	}
	
	public String getMarca() {
		return repuesto.getMarcaRepuesto();
	}
}
