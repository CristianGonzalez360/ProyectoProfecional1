package dto.temporal;

import java.util.Date;

public class PedidoDTO {

	private Integer idPedido;
	private String nombreCliente;
	private String apellidoCliente;
	private String dniCliente;
	private String marcaAuto;
	private String modeloAuto;
	private String colorAuto;
	private String conbustionAuto;
	private String nombreUsuario;
	private Date fechaPedido;

	public PedidoDTO() {

	}

	public PedidoDTO(Integer idPedido, String nombreCliente, String apellidoCliente, String dniCliente,
			String marcaAuto, String modeloAuto, String colorAuto, String conbustionAuto, String nombreUsuario,
			Date fechaPedido) {
		super();
		this.idPedido = idPedido;
		this.nombreCliente = nombreCliente;
		this.apellidoCliente = apellidoCliente;
		this.dniCliente = dniCliente;
		this.marcaAuto = marcaAuto;
		this.modeloAuto = modeloAuto;
		this.colorAuto = colorAuto;
		this.conbustionAuto = conbustionAuto;
		this.nombreUsuario = nombreUsuario;
		this.fechaPedido = fechaPedido;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getApellidoCliente() {
		return apellidoCliente;
	}

	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}

	public String getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}

	public String getMarcaAuto() {
		return marcaAuto;
	}

	public void setMarcaAuto(String marcaAuto) {
		this.marcaAuto = marcaAuto;
	}

	public String getModeloAuto() {
		return modeloAuto;
	}

	public void setModeloAuto(String modeloAuto) {
		this.modeloAuto = modeloAuto;
	}

	public String getColorAuto() {
		return colorAuto;
	}

	public void setColorAuto(String colorAuto) {
		this.colorAuto = colorAuto;
	}

	public String getConbustionAuto() {
		return conbustionAuto;
	}

	public void setConbustionAuto(String conbustionAuto) {
		this.conbustionAuto = conbustionAuto;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	@Override
	public String toString() {
		return "PedidoDTO [idPedido=" + idPedido + ", nombreCliente=" + nombreCliente + ", apellidoCliente="
				+ apellidoCliente + ", dniCliente=" + dniCliente + ", marcaAuto=" + marcaAuto + ", modeloAuto="
				+ modeloAuto + ", colorAuto=" + colorAuto + ", conbustionAuto=" + conbustionAuto + ", nombreUsuario="
				+ nombreUsuario + ", fechaPedido=" + fechaPedido + "]";
	}

}
