package dto.taller;

import java.util.ArrayList;
import java.util.List;

public class MantenimientoDTO {

	private Integer id;
	private String nombre;
	private String comentario;
	private List<TrabajoMantenimientoDTO> trabajos;
	private List<RepuestoMantenimientoDTO> repuestos;

	public MantenimientoDTO() {
		this.trabajos = new ArrayList<>();
		this.repuestos = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<TrabajoMantenimientoDTO> getTrabajos() {
		return trabajos;
	}

	public void setTrabajos(List<TrabajoMantenimientoDTO> trabajos) {
		this.trabajos = trabajos;
	}

	public List<RepuestoMantenimientoDTO> getRepuestos() {
		return repuestos;
	}

	public void setRepuestos(List<RepuestoMantenimientoDTO> repuestos) {
		this.repuestos = repuestos;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

	public void agregarRepuestos(RepuestoMantenimientoDTO repuestoMantenimiento) {
		this.repuestos.add(repuestoMantenimiento);
	}

	public Double getPrecio() {
		Double ret = 0.0;
		for (RepuestoMantenimientoDTO dto : repuestos) {
			ret += dto.getRepuesto().getPrecioRepuesto() * dto.getCantRequerida();
		}
		for (TrabajoMantenimientoDTO dto : trabajos) {
			ret += dto.getPrecioTrabajo();
		}
		return ret;
	}

	public void agregarTrabajo(TrabajoMantenimientoDTO trabajo) {
		this.trabajos.add(trabajo);
	}

	public void quitarRepuesto(int fila) {
		this.repuestos.remove(fila);
	}

	public void quitarTrabajo(int fila) {
		this.trabajos.remove(fila);

	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getComentario() {
		return this.comentario;
	}
}
