package dto;

public class RepuestoCompradoDTO {
	
	private int cantidad;
	private RepuestoDTO repuesto;

	public void setCantRequerida(int cant) {
		this.cantidad = cant;
	}

	public void setRepuesto(RepuestoDTO repuesto) {
		this.repuesto = repuesto;
	}

}
