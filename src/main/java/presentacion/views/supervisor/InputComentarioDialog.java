package presentacion.views.supervisor;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import dto.PresupuestoDTO;

import javax.swing.JEditorPane;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import java.awt.BorderLayout;

public class InputComentarioDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String title;
	private JLabel lblNro;
	private JLabel lblPrecio;
	private JLabel lblEstado;
	private JPanel panelRechazo;
	private JTextField txtComentario;

/*	public InputComentarioDialog() {
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
	}*/

	public InputComentarioDialog(PresupuestoDTO presupuesto) {
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(420, 147));
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("76px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("104px:grow"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("53px"),
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("100px:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.LINE_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.LINE_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("top:default:grow"),}));
		
		lblNro = new JLabel("Nro:");
		contentPane.add(lblNro, "2, 2, right, center");
		
		JTextField tfNumero = new JTextField();
		tfNumero.setFocusable(false);
		tfNumero.setEditable(false);
		contentPane.add(tfNumero, "4, 2, fill, top");
		tfNumero.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha:");
		contentPane.add(lblFecha, "6, 2, right, center");
		
		JTextField tfFecha = new JTextField();
		tfFecha.setFocusable(false);
		tfFecha.setEditable(false);
		contentPane.add(tfFecha, "8, 2, fill, top");
		tfFecha.setColumns(10);
		
		JLabel lblComentario = new JLabel("Comentario:");
		contentPane.add(lblComentario, "2, 4, right, center");
		
		JTextField tfComentario = new JTextField();
		tfComentario.setFocusable(false);
		tfComentario.setEditable(false);
		contentPane.add(tfComentario, "4, 4, 5, 1, fill, top");
		tfComentario.setColumns(10);
		
		lblPrecio = new JLabel("Precio:");
		contentPane.add(lblPrecio, "2, 6, right, default");
		
		JTextField tfPrecio = new JTextField();
		tfPrecio.setFocusable(false);
		tfPrecio.setEditable(false);
		contentPane.add(tfPrecio, "4, 6, fill, top");
		tfPrecio.setColumns(10);
		
		lblEstado = new JLabel("Estado:");
		contentPane.add(lblEstado, "6, 6, right, default");
		
		JTextField tfEstado = new JTextField();
		tfEstado.setFocusable(false);
		tfEstado.setEditable(false);
		contentPane.add(tfEstado, "8, 6, fill, default");
		tfEstado.setColumns(10);
		title = "";
		
		tfNumero.setText(presupuesto.getIdPresupuesto()+"");
		tfFecha.setText(presupuesto.getFechaAltaPresu().toString());
		tfComentario.setText(presupuesto.getComentarioAltaPresu());
		tfPrecio.setText(presupuesto.getPrecio()+"");
		tfEstado.setText("RECHAZADO");
		
		panelRechazo = new JPanel();
		panelRechazo.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Raz\u00F3n del rechazo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelRechazo, "2, 8, 9, 1, fill, top");
		panelRechazo.setLayout(new BorderLayout(0, 0));
		
		txtComentario = new JTextField();
		txtComentario.setToolTipText("Ingrese un comentario.");
		panelRechazo.add(txtComentario, BorderLayout.CENTER);
		
		title("Presupuesto Rechazado");
	}

	public InputComentarioDialog title(String title) {
		this.title = title;
		return this;
	}

	public InputComentarioDialog setText(String text) {
		txtComentario.setText(text);
		return this;
	}

	public String open() {
		String ret = null;
		while (ret == null) {
			int result = JOptionPane.showConfirmDialog(null, contentPane, title, JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			ret = getData();
			if(ret == null) {
				JOptionPane.showMessageDialog(null, "ingrese un comentario", "Campo Obligatorio", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		return ret;
	}

	private String getData() {
		String nombre = txtComentario.getText();
		if (nombre.trim().isEmpty()) {
			return null;
		}
		return nombre;
	}
}
