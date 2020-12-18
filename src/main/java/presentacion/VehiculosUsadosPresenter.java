package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

import business_logic.VehiculosController;
import business_logic.exceptions.ConflictException;
import dto.CaracteristicaVehiculoDTO;
import dto.VehiculoDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
import dto.temporal.CompraVehiculoUsadoDTO;
import presentacion.views.gerente.FormularioCompraDeVehiculos;
import presentacion.views.gerente.PanelVehiculosUsados;
import presentacion.views.utils.MessageDialog;

public class VehiculosUsadosPresenter {

	private PanelVehiculosUsados view;
	private VehiculosController controller;

	public VehiculosUsadosPresenter(VehiculosController vehiculosController) {
		this.view = PanelVehiculosUsados.getInstance();
		this.controller = vehiculosController;

		this.view.setActionOnSeleccionarVehiculo(a -> onSeleccionarVehiculo(a));
		FormularioCompraDeVehiculos.getInstance().setActionOnRegistrar(a -> registrar(a));
		this.view.setActionOnRegistrar(a -> MostrarFormulario(a));
		this.view.setData(controller.readVehiculosUsados());
	}

	private void registrar(ActionEvent a) {
		CompraVehiculoUsadoDTO compra = FormularioCompraDeVehiculos.getInstance().getData();
		List<String> errors = compra.validate();
		if (errors.isEmpty()) {
			try {
				this.controller.saveVehiculoUsado(compra);
				FormularioCompraDeVehiculos.getInstance().close();
				this.view.clearData();
				this.view.setData(controller.readVehiculosUsados());
			}
			catch (ConflictException e) {
				new MessageDialog().showMessages(e.getMessage());	
			}
		} else {
			new MessageDialog().showMessages(errors);
		}
	}

	private void MostrarFormulario(ActionEvent a) {
		FormularioCompraDeVehiculos.getInstance().clearData();
		FormularioCompraDeVehiculos.getInstance().display();
	}

	private void onSeleccionarVehiculo(ListSelectionEvent a) {
		VehiculoDTO vehiculo = this.view.getSeleccionado();
		if (vehiculo != null) {
			vehiculo = controller.readVehiculoById(vehiculo.getIdVehiculo());
			CaracteristicaVehiculoDTO carac = controller
					.readCaracteristicaVehiculoByIdVehiculo(vehiculo.getIdVehiculo());
			FichaTecnicaVehiculoDTO ficha = controller.readFichaTecnicaByIdVehiculo(vehiculo.getIdVehiculo());
			this.view.setData(carac);
			this.view.setData(ficha);
			this.view.setData(vehiculo);
		}
	}
}
