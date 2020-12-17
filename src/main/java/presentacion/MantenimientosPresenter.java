package presentacion;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import business_logic.MantenimientosController;
import business_logic.RepuestosController;
import dto.taller.MantenimientoDTO;
import dto.taller.RepuestoDTO;
import dto.taller.RepuestoMantenimientoDTO;
import dto.taller.TrabajoMantenimientoDTO;
import dto.validators.StringValidator;
import presentacion.views.supervisor.PanelMantenimientos;
import presentacion.views.tecnico.PlanificarRepuestosFormView;
import presentacion.views.tecnico.PlanificarTrabajosFormView;
import presentacion.views.utils.MessageDialog;

public class MantenimientosPresenter {

	private MantenimientosController mantenimientosController;
	private RepuestosController repuestosController;
	private PanelMantenimientos view;
	private PlanificarRepuestosFormView repuestos;
	private PlanificarTrabajosFormView trabajos;

	private MantenimientoDTO seleccionado;

	public MantenimientosPresenter(MantenimientosController mantenimientosController,
			RepuestosController repuestosController) {
		this.mantenimientosController = mantenimientosController;
		this.repuestosController = repuestosController;
		this.view = PanelMantenimientos.getInstance();
		this.repuestos = this.view.getRepuestosPanel();
		this.trabajos = this.view.getTrabajosPanel();

		this.repuestos.setActionOnBuscarRepuesto(a -> onBuscarRepuesto(a));
		this.repuestos.setActionOnAgregarRepuesto(a -> onAgregarRepuesto(a));
		this.repuestos.setActionOnQuitarRepuesto(a -> onQuitarRepuesto(a));

		this.trabajos.setActionOnAgregarTrabajo(a -> onAgregarTrabajos(a));
		this.trabajos.setActionOnQuitarTrabajo(a -> onQuitarTrabajo(a));

		this.view.setActionOnGuardar(a -> onGuardar(a));
		mostrarMantenimientos();
		mostrarRepuestos();
		this.view.setActionOnSeleccionar(a -> OnSeleccionar(a));
		this.seleccionado = new MantenimientoDTO();
	}

	private void onGuardar(ActionEvent a) {
		String comentario = this.view.getComentario();
		String nombre = this.view.getNombre();
		if (!comentario.isEmpty() && !seleccionado.getTrabajos().isEmpty() && !seleccionado.getRepuestos().isEmpty()) {
			seleccionado.setComentario(comentario);
			seleccionado.setNombre(nombre);
			if (seleccionado.getId() == null) {
				mantenimientosController.save(seleccionado);
			} else {
				mantenimientosController.update(seleccionado);
			}
			mostrarMantenimientos();
			this.view.clearData();
			this.seleccionado = new MantenimientoDTO();
		} else {
			List<String> errors = new ArrayList<>();
			if (comentario.isEmpty()) {
				errors.add("Ingrece un comentario");
			}
			if (nombre.isEmpty()) {
				errors.add("Ingrece un nombre");
			}
			if (seleccionado.getTrabajos().isEmpty()) {
				errors.add("Agregue al menos un trabajo");
			}
			if (seleccionado.getRepuestos().isEmpty()) {
				errors.add("Agregue al menos un repuesto");
			}
			new MessageDialog().showMessages(errors);
		}
	}

	private void OnSeleccionar(ActionEvent a) {
		Integer id = this.view.getMantenimientoSeleccionado();
		if (id != null) {
			seleccionado = mantenimientosController.readByID(id);
		} else {
			seleccionado = new MantenimientoDTO();
		}
		this.view.setDataRepuestos(seleccionado.getRepuestos());
		this.view.setDataTrabajos(seleccionado.getTrabajos());
		this.view.setData(seleccionado);
	}

	private void mostrarMantenimientos() {
		List<MantenimientoDTO> datos = mantenimientosController.readAll();
		MantenimientoDTO m = new MantenimientoDTO();
		m.setNombre("Nuevo");
		datos.add(0, m);
		this.view.setData(datos);
	}

	private void onQuitarRepuesto(ActionEvent a) {
		Integer fila = this.repuestos.getSeleccionado();
		if (fila >= 0) {
			onBuscarRepuesto(a);
			seleccionado.quitarRepuesto(fila);
			this.repuestos.setDataRepuestosMantenimiento(seleccionado.getRepuestos());
			this.view.setPrecio(seleccionado.getPrecio());
		}
	}

	private void onQuitarTrabajo(ActionEvent a) {
		Integer fila = this.trabajos.getSeleccionado();
		if (fila >= 0) {
			this.seleccionado.quitarTrabajo(fila);
			this.trabajos.setDataTrabajosMantenimiento(seleccionado.getTrabajos());
			this.view.setPrecio(seleccionado.getPrecio());
		}
	}

	private void onAgregarRepuesto(ActionEvent a) {
		String cantidad = this.repuestos.getCantidad();
		String idRepuesto = this.repuestos.getIdRepuesto();
		List<String> errors = new StringValidator(idRepuesto).number("Debe seleccionar un repuesto.").validate();
		errors.addAll(new StringValidator(cantidad).notBlank("Debe ingresar una cantidad.")
				.positiveInteger("La cantidad debe ser un número mayor a 0.").validate());
		if (errors.isEmpty()) {
			RepuestoDTO repuesto = repuestosController.readById(Integer.parseInt(idRepuesto));
			int cant = Integer.parseInt(cantidad);
			RepuestoMantenimientoDTO repuestoMantenimiento = new RepuestoMantenimientoDTO();
			repuestoMantenimiento.setCantRequerida(cant);
			repuestoMantenimiento.setRepuesto(repuesto);
			this.seleccionado.agregarRepuestos(repuestoMantenimiento);
			this.view.setPrecio(seleccionado.getPrecio());
			onBuscarRepuesto(a);
			this.repuestos.setDataRepuestosMantenimiento(seleccionado.getRepuestos());
		} else {
			new MessageDialog().showMessages(errors);
			;
		}
	}

	private void onAgregarTrabajos(ActionEvent a) {
		String descripcion = this.trabajos.getDescripcion();
		String monto = this.trabajos.getMonto();
		String esfuerzo = this.trabajos.getEsfuerzo();
		List<String> errors = new StringValidator(descripcion).notBlank("Ingrese una descripción.").validate();
		errors.addAll(new StringValidator(monto).notBlank("Ingrese un monto.")
				.positiveDouble("El monto debe ser un número mayor a 0.").validate());
		errors.addAll(new StringValidator(esfuerzo).notBlank("Ingrese un esfuerzo estimado.")
				.positiveInteger("El esfuerzo debe ser un número entero mayor a 0.").validate());
		if (errors.isEmpty()) {
			TrabajoMantenimientoDTO trabajo = new TrabajoMantenimientoDTO();
			trabajo.setDescripcionTrabajo(descripcion);
			trabajo.setTiempoEstTrabajo(Integer.parseInt(esfuerzo));
			trabajo.setPrecioTrabajo(Double.parseDouble(monto));
			seleccionado.agregarTrabajo(trabajo);
			this.view.setPrecio(seleccionado.getPrecio());
			this.trabajos.setDataTrabajosMantenimiento(seleccionado.getTrabajos());
			this.trabajos.clearDataNuevoTrabajo();
		} else {
			new MessageDialog().showMessages(errors);
		}
	}

	private void mostrarRepuestos() {
		List<String> marcas = repuestosController.readMarcas();
		marcas.add(0, "Todas");
		this.repuestos.setDataMarcas(marcas);
		this.repuestos.setDataRepuestos(repuestosController.readAll());

	}

	private void onBuscarRepuesto(ActionEvent a) {
		String marca = this.repuestos.getMarca();
		String descripcion = this.repuestos.getDescripcion();
		List<RepuestoDTO> listaRepuestos;
		if (descripcion.isEmpty()) {
			if (marca == "Todas") {
				listaRepuestos = repuestosController.readAll();
			} else {
				listaRepuestos = repuestosController.readByMarca(marca);
			}
		} else {
			if (marca == "Todas") {
				listaRepuestos = repuestosController.readByDescripcion(descripcion);
			} else {
				listaRepuestos = repuestosController.readbyMarcaYDescripcion(marca, descripcion);
			}
		}
		this.repuestos.setDataRepuestos(listaRepuestos);
	}
}
