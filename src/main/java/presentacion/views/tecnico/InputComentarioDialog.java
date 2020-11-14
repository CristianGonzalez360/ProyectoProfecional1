package presentacion.views.tecnico;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JEditorPane;
import java.awt.Dimension;

public class InputComentarioDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel textLabel;
	private JEditorPane textField;
	private String title;

	public InputComentarioDialog() {
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(300, 150));
		textLabel = new JLabel("Comentario (Opcional)");
		textField = new JEditorPane();
		textField.setText("");
		contentPane.setLayout(
				new FormLayout(new ColumnSpec[] { ColumnSpec.decode("8px"), ColumnSpec.decode("164px:grow"), },
						new RowSpec[] { FormSpecs.LINE_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
								FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("61px:grow"), }));
		contentPane.add(textLabel, "2, 2, left, top");
		contentPane.add(textField, "2, 4, fill, fill");
		title = "";
	}

	public InputComentarioDialog title(String title) {
		this.title = title;
		return this;
	}

	public InputComentarioDialog setText(String text) {
		textField.setText(text);
		return this;
	}

	public String open() {
		String ret = null;
		while (ret == null) {
			int result = JOptionPane.showConfirmDialog(null, contentPane, title, JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if(result == JOptionPane.OK_OPTION) {
				ret = getData();
				if(ret == null) {
					JOptionPane.showMessageDialog(null, "ingrese un comentario", "Campo Obligatorio", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				textField.setText("");
				break;
			}
		}
		return ret;
	}

	private String getData() {
		String nombre = textField.getText();
		if (nombre.trim().isEmpty()) {
			return null;
		}
		return nombre;
	}
}
