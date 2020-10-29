package presentacion;

import java.awt.event.ActionEvent;

import business_logic.PaisController;
import business_logic.exceptions.ConflictException;
import business_logic.exceptions.ConstraintViolationException;
import dto.PaisDTO;
import presentacion.views.PaisView;
import presentacion.views.WorkbenchView;
import presentacion.views.utils.ErrorDialog;
import presentacion.views.utils.InputDialog;

public class PaisPresenter {

	private PaisView view = PaisView.getInstance();
	private PaisController controller;

	public PaisPresenter(PaisController controller) {
		this.controller = controller;
		onInjectWorkbenchAction();
		onInjectActions();
	}

	private void onInjectWorkbenchAction() {
		WorkbenchView.getInstance()
		.getMntmNewMenuItemPaises()
		.addActionListener((a)->onInit(a));
	}

	private void onInit(ActionEvent a) {
		reset();
		view.open();
	}
	
	private void onInjectActions() {
		view.setActionSave(a -> onDisplayFormForSave(a));
		view.setActionUpdate(a -> onDisplayFormForUpdate(a));
		view.setActionDelete(s -> onDelete(s));
	}
	
	private void onDisplayFormForSave(ActionEvent a) {
		String input = new InputDialog()
				.title("Ingrese los datos del nuevo país")
				.open();
		if(input != null && !input.trim().isEmpty()) {
			try {
				PaisDTO target = new PaisDTO(null, input);
				controller.save(target);
				reset();
			}catch(ConflictException e) {
				new ErrorDialog().showMessages(e.getMessage());
			}
		}
	}
	
	private void onDisplayFormForUpdate(ActionEvent a) {
		PaisDTO current = view.getData();
		if(current != null) {
			String input = new InputDialog()
					.title("Ingrese los nuevos datos del nuevo pais")
					.setText(current.getNombre())
					.open();
			if(input != null && !input.trim().isEmpty()) {
				try {
					PaisDTO target = new PaisDTO(null, input);
					target.setId(current.getId());
					controller.update(target);
					reset();
				}catch(ConflictException e) {
					new ErrorDialog().showMessages(e.getMessage());
				}
			}	
		}
	}
	
	private void onDelete(ActionEvent s) {
		PaisDTO target = view.getData();
		if(target != null) {
			try {
				controller.deleteById(target.getId());
				reset();
			} catch (ConstraintViolationException e) {
				new ErrorDialog().showMessages("No se puede eliminar un pais en uso");
			}
		}
	}
	
	private void reset() {
		view.clearData();
		view.setData(controller.readAll());
	}
}
