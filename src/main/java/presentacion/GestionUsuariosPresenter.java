package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

import business_logic.UsuariosController;
import dto.UsuarioDTO;
import presentacion.views.admin.AdminControlView;
import presentacion.views.admin.PanelUsuarios;

public class GestionUsuariosPresenter {

	private PanelUsuarios view = AdminControlView.getInstance().getUsuariosView();
	
	private UsuariosController controller;
	
	public GestionUsuariosPresenter(UsuariosController controller) {
		this.controller = controller;
		this.view.setActionSelectUsuario((a)->onSelectUsuario(a));
		this.view.setActionRegistrarUsuario((a)->onRegistrarUsuario(a));
		onInit();
	}
	
	private void onRegistrarUsuario(ActionEvent a) {
		
	}

	private void onInit() {
		List<UsuarioDTO> usuarios = controller.readAll();
		this.view.setData(usuarios);
	}

	private void onSelectUsuario(ListSelectionEvent a) {
		Integer id = view.getData();
		view.clearUsuarioData();
		if(id != null) {
			UsuarioDTO target = controller.readById(id);
			if(target != null) {
				view.setData(target);
			}
		}
	}
}
