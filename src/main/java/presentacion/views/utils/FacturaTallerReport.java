package presentacion.views.utils;

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
	
	public FacturaTallerReport() {
		// TODO Auto-generated constructor stub
	}
	
	public DatosPersonalesDTO getCliente() {
		return cliente;
	}
	public void setCliente(DatosPersonalesDTO cliente) {
		this.cliente = cliente;
	}
	public FichaTecnicaVehiculoDTO getVehiculo() {
		return vehiculo;
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
}
