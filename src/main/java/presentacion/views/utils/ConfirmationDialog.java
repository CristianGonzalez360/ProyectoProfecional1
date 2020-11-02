package presentacion.views.utils;

import javax.swing.JOptionPane;

public class ConfirmationDialog {
	
	private String message;
	
	public ConfirmationDialog(String message) {
		this.message = message;
	}
	
	public int open() {
		return JOptionPane.showOptionDialog(null, message,
				"Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
	}
}
