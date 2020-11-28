package dto;

public class VehiculoReservadoDTO {

	private Integer idVehiculoReservado;
	
	private Integer idFichaTecnica;
	
	private Integer idPedido;
	
	private Double precioVenta;
	
	public VehiculoReservadoDTO() {
	}
	
	public Integer getIdVehiculoReservado() {
		return idVehiculoReservado;
	}
	public void setIdVehiculoReservado(Integer idVehiculoReservado) {
		this.idVehiculoReservado = idVehiculoReservado;
	}
	public Integer getIdFichaTecnica() {
		return idFichaTecnica;
	}
	public void setIdFichaTecnica(Integer idFichaTecnica) {
		this.idFichaTecnica = idFichaTecnica;
	}
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}
}
