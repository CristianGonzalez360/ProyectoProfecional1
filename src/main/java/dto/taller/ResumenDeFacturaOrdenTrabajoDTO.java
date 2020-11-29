package dto.taller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ResumenDeFacturaOrdenTrabajoDTO {

	private OrdenDeTrabajoDTO ordenDeTrabajo;
	
	private FacturaDTO factura;
	
	private List<RepuestoPlanificadoDTO> repuestos;
	
	private List<TrabajoPresupuestadoDTO> trabajos;

	public ResumenDeFacturaOrdenTrabajoDTO() {
		this.trabajos = new ArrayList<>();
		this.repuestos = new ArrayList<>();
	}
	
	public List<String> generarResumen() {
		List<String> renglones = new LinkedList<>();
		if(trabajos != null && repuestos != null) {
			renglones.add(" ------ CONCESIONARIOS LARUSSO 'Pateamos la competencia'------");
			renglones.add(" ------ FECHA DE EMISION DEL RESUMEN ----" + new Date() + "Suecursal: ARG BS.AS");
			for(TrabajoPresupuestadoDTO trabajo : trabajos) {
				renglones.add("COD. " + trabajo.getIdTrabajoPresu() + "PRECIO " + trabajo.getPrecioTrabajo());
			}
			for (RepuestoPlanificadoDTO repuesto : repuestos) {
				renglones.add("COD. " + repuesto.getIdRepuesto() + "PRECIO U. " 
						+ repuesto.getRepuesto().getPrecioRepuesto() 
						+ "SUBTOTAL " + repuesto.getRepuesto().getPrecioRepuesto()*repuesto.getCantRequerida());
			}
			renglones.add(" ----- MONTO TOTAL A PAGAR: " + factura.getTotal() + "------");	
		}
		return renglones;
	}
	
	public OrdenDeTrabajoDTO getOrdenDeTrabajo() {
		return ordenDeTrabajo;
	}

	public void setOrdenDeTrabajo(OrdenDeTrabajoDTO ordenDeTrabajo) {
		this.ordenDeTrabajo = ordenDeTrabajo;
	}

	public FacturaDTO getFactura() {
		return factura;
	}

	public void setFactura(FacturaDTO factura) {
		this.factura = factura;
	}

	public List<RepuestoPlanificadoDTO> getRepuestos() {
		return repuestos;
	}

	public void setRepuestos(List<RepuestoPlanificadoDTO> repuestos) {
		this.repuestos = repuestos;
	}

	public List<TrabajoPresupuestadoDTO> getTrabajos() {
		return trabajos;
	}

	public void setTrabajos(List<TrabajoPresupuestadoDTO> trabajos) {
		this.trabajos = trabajos;
	}
	
	public void agregarRepuestos(List<RepuestoPlanificadoDTO> repuestos) {
		this.repuestos.addAll(repuestos);
	}
	
	public void agregarTrabajos(List<TrabajoPresupuestadoDTO> trabajos) {
		this.trabajos.addAll(trabajos);
	}
	
	
}
