package dto;

public class VehiculoReservadoDTO {

	private Integer idVehiculoReservado;
	private Integer idFichaTecnica;
	private Integer idPedido;
	private Double precioVenta;

	public VehiculoReservadoDTO() {
		// TODO Auto-generated constructor stub
	}

	public VehiculoReservadoDTO makeTestDTO() {
		VehiculoReservadoDTO ret = new VehiculoReservadoDTO();
		ret.setPrecioVenta((Double) 1000.0);
		return ret;
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

	@Override
	public String toString() {
		return "VehiculoReservadoDTO [idVehiculoReservado=" + idVehiculoReservado + ", idFichaTecnica=" + idFichaTecnica
				+ ", idPedido=" + idPedido + ", precioVenta=" + precioVenta + "]";
	}

}
