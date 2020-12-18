package presentacion;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

import business_logic.ConfiguradorTerminalController;
import business_logic.SucursalesController;
import dto.MonedaDTO;
import dto.SucursalDTO;
import dto.temporal.AltaSucursalDTO;
import presentacion.views.admin.AdminControlView;
import presentacion.views.admin.FormAltaSucursal;
import presentacion.views.utils.MessageDialog;

public class SucursalesControlPresenter {

	private AdminControlView view = AdminControlView.getInstance();

	private SucursalesController controller;

	private ConfiguradorTerminalController confController;

	private static final String [] paisesDelMercosur = new String[] { "Argentina", "Uruguay", "Chile", "Paraguay" };
	
	public SucursalesControlPresenter(SucursalesController controller, ConfiguradorTerminalController confController) {
		assert controller != null;
		this.controller = controller;
		this.confController = confController;
		view.setActionBuscarSucursal((a) -> onBuscarSucursales(a));
		view.setActionSeleccionSucursal((a) -> onSelectSucursal(a));
		view.setActionEscogerTerminal((a) -> onEscogerComoTerminal(a));
		view.addPaisesDeBusqueda(paisesDelMercosur);				
		view.setActionRegistrarSucursal((a)->onDisplayFormAltaSucursal(a));
		FormAltaSucursal.getInstance().setActionOk((a)->onRegistrarSucursal(a));
		FormAltaSucursal.getInstance().setActionCancel((a)->{ FormAltaSucursal.getInstance().close(); });
	}

	private void onRegistrarSucursal(ActionEvent a) {
		AltaSucursalDTO nuevaSucursal = FormAltaSucursal.getInstance().getData();
		List<String> errors = nuevaSucursal.validate();
		if(errors.isEmpty()) {
			controller.save(nuevaSucursal);
			FormAltaSucursal.getInstance().close();
		} else {
			new MessageDialog().showMessages(errors);
		}
	}

	private void onDisplayFormAltaSucursal(ActionEvent a) {
		FormAltaSucursal.getInstance().clearData();
		FormAltaSucursal.getInstance().setDataPaises(Arrays.asList(paisesDelMercosur));
		FormAltaSucursal.getInstance().onDisplay();
	}

	private void onEscogerComoTerminal(ActionEvent a) {
		SucursalDTO sucursal = view.getData();
		if (sucursal != null) {
			confController.establecerSucursalPredetermidada(sucursal);
			new MessageDialog().showMessages("La sucursal fué escogida");
		}
	}

	private void onSelectSucursal(ListSelectionEvent a) {
		SucursalDTO sucursal = view.getData();
		if (sucursal != null) {
			MonedaDTO moneda = controller.readMonedaByPais(sucursal.getPais());
			if (moneda != null) {
				view.clearDataMoneda();
				view.setData(moneda);
			}
		}
	}

	private void onBuscarSucursales(ActionEvent a) {
		String nombrePais = view.getDataNombrePais();
		List<SucursalDTO> sucursales = controller.readByPais(nombrePais);
		view.clearData();
		if (!sucursales.isEmpty()) {
			view.setData(sucursales);
		}
	}
}
