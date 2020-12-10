package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

import business_logic.UsuariosController;
import business_logic.exceptions.ConflictException;
import dto.UsuarioDTO;
import presentacion.views.admin.AdminControlView;
import presentacion.views.admin.FormAltaUsuarioView;
import presentacion.views.admin.PanelGestionUsuariosView;
import presentacion.views.utils.MessageDialog;

public class GestionUsuariosPresenter {

	private PanelGestionUsuariosView view = AdminControlView.getInstance().getUsuariosView();
	
	private UsuariosController controller;
	
	public GestionUsuariosPresenter(UsuariosController controller) {
		this.controller = controller;
		this.view.setActionSelectUsuario((a)->onSelectUsuario(a));
		this.view.setActionRegistrarUsuario((a)->onDisplayFormAltaDeUsuario(a));
		FormAltaUsuarioView.getInstance().setActionOk((a)->onRegistrarUsuario(a));
		onInit();
	}
	
	private void onRegistrarUsuario(ActionEvent a) {
		UsuarioDTO dto = FormAltaUsuarioView.getInstance().getData();
		if(dto != null) {
			List<String> errors = dto.validate();
			if(errors.isEmpty()) {
				try {
					controller.save(dto);
					view.clearData();
					view.setData(controller.readAll());
					FormAltaUsuarioView.getInstance().close();
				} catch(ConflictException e) {
					new MessageDialog().showMessages(e.toString());
				}
			} else {
				new MessageDialog().showMessages(errors);
			}
		}
	}

	private void onDisplayFormAltaDeUsuario(ActionEvent a) {
		FormAltaUsuarioView.getInstance().clearData();
		FormAltaUsuarioView.getInstance().open();
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
