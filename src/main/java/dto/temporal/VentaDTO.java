package dto.temporal;

import java.util.Date;

public class VentaDTO {
	private Integer idVenta;
	private Date fechaDeVenta;
	private Date fechaDeEntrega;
	private String marcaVehiculo;
	private String modeloVehiculo;
	private String nombreCliente;
	private Double precioVenta;
	private Double comisionVenta;
	private String sucursal;

	VentaDTO() {

	}

	public VentaDTO(Integer idVenta, Date fechaDeVenta, Date fechaDeEntrega, String marcaVehiculo,
			String modeloVehiculo, String nombreCliente, Double precioVenta, Double comisionVenta, String sucursal) {
		super();
		this.idVenta = idVenta;
		this.fechaDeVenta = fechaDeVenta;
		this.fechaDeEntrega = fechaDeEntrega;
		this.marcaVehiculo = marcaVehiculo;
		this.modeloVehiculo = modeloVehiculo;
		this.nombreCliente = nombreCliente;
		this.precioVenta = precioVenta;
		this.comisionVenta = comisionVenta;
		this.sucursal = sucursal;
	}

	public Integer getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	public Date getFechaDeVenta() {
		return fechaDeVenta;
	}

	public void setFechaDeVenta(Date fechaDeVenta) {
		this.fechaDeVenta = fechaDeVenta;
	}

	public Date getFechaDeEntrega() {
		return fechaDeEntrega;
	}

	public void setFechaDeEntrega(Date fechaDeEntrega) {
		this.fechaDeEntrega = fechaDeEntrega;
	}

	public String getMarcaVehiculo() {
		return marcaVehiculo;
	}

	public void setMarcaVehiculo(String marcaVehiculo) {
		this.marcaVehiculo = marcaVehiculo;
	}

	public String getModeloVehiculo() {
		return modeloVehiculo;
	}

	public void setModeloVehiculo(String modeloVehiculo) {
		this.modeloVehiculo = modeloVehiculo;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public Double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Double previoVenta) {
		this.precioVenta = previoVenta;
	}

	public Double getComisionVenta() {
		return comisionVenta;
	}

	public void setComisionVenta(Double comisionVenta) {
		this.comisionVenta = comisionVenta;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

}
