package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

import business_logic.SucursalesController;
import dto.MonedaDTO;
import dto.SucursalDTO;
import presentacion.views.admin.AdminControlView;

public class AdminControlPresenter {
	
	private AdminControlView view = AdminControlView.getInstance();
	
	private SucursalesController controller;
	
	public AdminControlPresenter(SucursalesController controller) {
		assert controller != null;
		this.controller = controller;
		this.view.setActionBuscarSucursal((a)->onBuscarSucursales(a));
		this.view.setActionSeleccionSucursal((a)->onSelectSucursal(a));
		this.view.addPaisesDeBusqueda(new String [] {"Argentina", "Uruguay", "Brazil", "Bolivia", "Venezuela", "Paraguay"});
	}
	
	private void onSelectSucursal(ListSelectionEvent a) {
		SucursalDTO sucursal = view.getData();
		if(sucursal != null) {
			MonedaDTO moneda = controller.readMonedaByPais(sucursal.getPais());
			if(moneda != null) {
				view.clearDataMoneda();
				view.setData(moneda);
			}	
		}
	}

	private void onBuscarSucursales(ActionEvent a) {
		String nombrePais = view.getDataNombrePais();
		List<SucursalDTO> sucursales = controller.readByPais(nombrePais);
		view.clearData();
		if(!sucursales.isEmpty()) {
			view.setData(sucursales);
		}
	}
}
