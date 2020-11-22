package presentacion.views.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.DatosPersonalesDTO;
import dto.FichaTecnicaVehiculoDTO;
import dto.RepuestoPlanificadoDTO;
import dto.TrabajoPresupuestadoDTO;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class FacturaTallerReport {

	private DatosPersonalesDTO cliente;
	private FichaTecnicaVehiculoDTO vehiculo;
	private List<TrabajoPresupuestadoDTO> trabajos;
	private List<RepuestoPlanificadoDTO> repuestos;
	private Double total;
	private Date fecha;
	private Integer numero;
	
	public FacturaTallerReport() {
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
	public JRDataSource getVehiculo() {
		List<FichaTecnicaVehiculoDTO> vehiculo = new ArrayList<>();
		vehiculo.add(this.vehiculo);
		return new JRBeanCollectionDataSource(vehiculo);
	}
	public void setVehiculo(FichaTecnicaVehiculoDTO vehiculo) {
		this.vehiculo = vehiculo;
	}
	public JRDataSource getTrabajos() {
		return new JRBeanCollectionDataSource(trabajos);
	}
	public void setTrabajos(List<TrabajoPresupuestadoDTO> trabajos) {
		this.trabajos = trabajos;
	}
	public JRDataSource getRepuestos() {
		return new JRBeanCollectionDataSource(repuestos);
	}
	public void setRepuestos(List<RepuestoPlanificadoDTO> repuestos) {
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
