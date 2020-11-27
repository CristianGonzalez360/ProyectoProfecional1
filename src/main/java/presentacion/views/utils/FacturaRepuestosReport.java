package presentacion.views.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.DatosPersonalesDTO;
import dto.RepuestoCompradoDTO;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class FacturaRepuestosReport {

	private DatosPersonalesDTO cliente;
	private List<RepuestoCompradoDTO> repuestos;
	private Double total;
	private Date fecha;
	private Integer numero;
	
	public FacturaRepuestosReport() {
		// TODO Auto-generated constructor stub
	}
	
	public JRDataSource getCliente() {
		List<DatosPersonalesDTO> cliente = new ArrayList<>();
		cliente.add(this.cliente);
		return new JRBeanCollectionDataSource(cliente) ;
	}
	public void setCliente(DatosPersonalesDTO cliente) {
		this.cliente = cliente;
	}
	
	public JRDataSource getRepuestos() {
		return new JRBeanCollectionDataSource(repuestos);
	}
	public void setRepuestos(List<RepuestoCompradoDTO> repuestos) {
		this.repuestos = repuestos;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
}
