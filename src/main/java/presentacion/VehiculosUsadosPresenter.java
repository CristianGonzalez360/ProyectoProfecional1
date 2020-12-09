package presentacion;

import java.awt.event.ActionEvent;

import javax.swing.event.ListSelectionEvent;

import business_logic.VehiculosController;
import dto.CaracteristicaVehiculoDTO;
import dto.VehiculoDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
import dto.temporal.CompraVehiculoUsadoDTO;
import presentacion.views.gerente.FormularioCompraDeVehiculos;
import presentacion.views.gerente.PanelVehiculosUsados;

public class VehiculosUsadosPresenter {

	private PanelVehiculosUsados view;
	private VehiculosController controller;
	
	public VehiculosUsadosPresenter(VehiculosController vehiculosController) {
		this.view = PanelVehiculosUsados.getInstance();
		this.controller = vehiculosController;
		
		this.view.setActionOnSeleccionarVehiculo(a -> onSeleccionarVehiculo(a));
		FormularioCompraDeVehiculos.getInstance().setActionOnRegistrar(a ->registrar(a));
		this.view.setActionOnRegistrar(a->MostrarFormulario(a));
		this.view.setData(controller.readVehiculosUsados());
	}

	private void registrar(ActionEvent a) {
		CompraVehiculoUsadoDTO compra = FormularioCompraDeVehiculos.getInstance().getData();
		this.controller.saveVehiculoUsado(compra);
		FormularioCompraDeVehiculos.getInstance().close();
		this.view.clearData();
		this.view.setData(controller.readVehiculosUsados());
	}

	private void MostrarFormulario(ActionEvent a) {
		FormularioCompraDeVehiculos.getInstance().display();
	}

	private void onSeleccionarVehiculo(ListSelectionEvent a) {
		VehiculoDTO vehiculo = this.view.getSeleccionado();
		if(vehiculo != null) {
			vehiculo = controller.readVehiculoById(vehiculo.getIdVehiculo());
			CaracteristicaVehiculoDTO carac = controller.readCaracteristicaVehiculoByIdVehiculo(vehiculo.getIdVehiculo());
			FichaTecnicaVehiculoDTO ficha = controller.readFichaTecnicaByIdVehiculo(vehiculo.getIdVehiculo());
			this.view.setData(carac);
			this.view.setData(ficha);
			this.view.setData(vehiculo);
		}
	}
}
