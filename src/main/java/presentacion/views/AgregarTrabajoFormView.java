package presentacion.views;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JEditorPane;

public class AgregarTrabajoFormView extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5945189647492919676L;
	private static AgregarTrabajoFormView instance;
	private JPanel panelAgregarTrabajo = new JPanel();
	private JTextField tfEsfuerzo;
	private JTextField tfMonto;
	private JEditorPane editorDescripcion;
	private JButton btnGuardar;

	private AgregarTrabajoFormView() {
		setBounds(100, 100, 450, 300);
		panelAgregarTrabajo.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelAgregarTrabajo.setLayout(new BorderLayout(0, 0));

		JPanel panelBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelAgregarTrabajo.add(panelBotones, BorderLayout.SOUTH);

		btnGuardar = new JButton("Guardar");
		panelBotones.add(btnGuardar);

		JButton btnCancelar = new JButton("Cancelar");
		panelBotones.add(btnCancelar);

		JPanel panelPrincipal = new JPanel();
		panelAgregarTrabajo.add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new BorderLayout(0, 0));

		JPanel panelCentral = new JPanel();
		panelPrincipal.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));

		JPanel panelCentralInferior = new JPanel();
		FlowLayout fl_panelCentralInferior = (FlowLayout) panelCentralInferior.getLayout();
		fl_panelCentralInferior.setAlignment(FlowLayout.RIGHT);
		panelCentral.add(panelCentralInferior, BorderLayout.SOUTH);

		JLabel lblMonto = new JLabel("Monto:    ");
		panelCentralInferior.add(lblMonto);

		tfMonto = new JTextField();
		panelCentralInferior.add(tfMonto);
		tfMonto.setColumns(10);

		JPanel panelDescripción = new JPanel();
		panelCentral.add(panelDescripción, BorderLayout.CENTER);
		panelDescripción.setLayout(new BorderLayout(0, 0));

		JLabel lblDescripcin = new JLabel("Descripción");
		panelDescripción.add(lblDescripcin, BorderLayout.NORTH);

		editorDescripcion = new JEditorPane();

		JScrollPane scroll = new JScrollPane(editorDescripcion);
		panelDescripción.add(scroll, BorderLayout.CENTER);

		JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

		JLabel lblEsfuerzo = new JLabel("Esfuerzo:");
		panelInferior.add(lblEsfuerzo);

		tfEsfuerzo = new JTextField();
		panelInferior.add(tfEsfuerzo);
		tfEsfuerzo.setColumns(10);
	}

	/*public static AgregarTrabajoFormView getInstance() {
		if (instance == null)
			instance = new AgregarTrabajoFormView();
		return instance;
	}

	public void clearDataNuevoTrabajo() {
		editorDescripcion.setText("");
		tfMonto.setText("");
		tfEsfuerzo.setText("");
	}

	public TrabajoPresupuestadoDTO getDataNuevoTrabajo() {
		TrabajoPresupuestadoDTO ret = new TrabajoPresupuestadoDTO();
		ret.setDescripcionTrabajo(editorDescripcion.getText());
		ret.setPrecioTrabajo(Double.parseDouble(tfMonto.getText()));
		ret.setTiempoEstTrabajo(Integer.parseInt(tfEsfuerzo.getText()));
		return ret;
	}

	public void setActionOnAgregar(ActionListener listener) {
		this.btnGuardar.addActionListener(listener);
	}*/

}
