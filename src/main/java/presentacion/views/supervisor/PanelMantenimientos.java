package presentacion.views.supervisor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import dto.taller.MantenimientoDTO;
import dto.taller.RepuestoMantenimientoDTO;
import dto.taller.TrabajoMantenimientoDTO;
import presentacion.views.tecnico.PlanificarRepuestosFormView;
import presentacion.views.tecnico.PlanificarTrabajosFormView;
import javax.swing.JComboBox;

public class PanelMantenimientos extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnAceptar;
	private JButton btnCancelar;

	private PlanificarTrabajosFormView trabajos;
	private PlanificarRepuestosFormView repuestos;
	private JPanel panel;
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JComboBox<MantenimientoDTO> comboPresets;
	private JLabel lblMantenimiento;

	private static PanelMantenimientos instance;
	private JLabel lblPrecio;
	private JTextField tfPrecio;
	private JLabel lblComentario;
	private JTextField tfComentario;

	private PanelMantenimientos() {
		setLayout(new BorderLayout());

		JPanel panelBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		add(panelBotones, BorderLayout.SOUTH);

		btnAceptar = new JButton("Aceptar");
		panelBotones.add(btnAceptar);

		btnCancelar = new JButton("Cancelar");
		panelBotones.add(btnCancelar);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setResizeWeight(0.5);
		add(splitPane, BorderLayout.CENTER);

		trabajos = new PlanificarTrabajosFormView();
		repuestos = new PlanificarRepuestosFormView();

		splitPane.setLeftComponent(trabajos);
		splitPane.setRightComponent(repuestos);

		panel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		add(panel, BorderLayout.NORTH);

		lblMantenimiento = new JLabel("Mantenimiento:");
		panel.add(lblMantenimiento);

		comboPresets = new JComboBox<>();
		panel.add(comboPresets);

		lblNombre = new JLabel("Nombre:");
		panel.add(lblNombre);

		tfNombre = new JTextField();
		panel.add(tfNombre);
		tfNombre.setColumns(10);

		lblComentario = new JLabel("Comentario:");
		panel.add(lblComentario);

		tfComentario = new JTextField();
		panel.add(tfComentario);
		tfComentario.setColumns(10);

		lblPrecio = new JLabel("Precio:");
		panel.add(lblPrecio);

		tfPrecio = new JTextField();
		tfPrecio.setFocusable(false);
		tfPrecio.setEditable(false);
		panel.add(tfPrecio);
		tfPrecio.setColumns(10);
	}

	public static PanelMantenimientos getInstance() {
		if (instance == null)
			instance = new PanelMantenimientos();
		return instance;
	}

	public void setData(List<MantenimientoDTO> mantenimientos) {
		DefaultComboBoxModel<MantenimientoDTO> model = new DefaultComboBoxModel<>();
		for (MantenimientoDTO mantenimiento : mantenimientos) {
			model.addElement(mantenimiento);
		}
		comboPresets.setModel(model);
	}

	public void setDataTrabajos(List<TrabajoMantenimientoDTO> trabajos) {
		this.trabajos.setDataTrabajosMantenimiento(trabajos);
	}

	public void setDataRepuestos(List<RepuestoMantenimientoDTO> repuestos) {
		this.repuestos.setDataRepuestosMantenimiento(repuestos);
	}

	public Integer getMantenimientoSeleccionado() {
		return comboPresets.getModel().getElementAt(comboPresets.getSelectedIndex()).getId();
	}

	public void setActionOnSeleccionar(ActionListener listener) {
		this.comboPresets.addActionListener(listener);
	}

	public void setData(MantenimientoDTO m) {
		this.tfNombre.setText(m.getNombre());
		this.tfComentario.setText(m.getComentario());
		this.tfPrecio.setText(m.getPrecio() + "");
	}

	public PlanificarRepuestosFormView getRepuestosPanel() {
		return this.repuestos;
	}

	public PlanificarTrabajosFormView getTrabajosPanel() {
		return this.trabajos;
	}

	public void setPrecio(Double precio) {
		this.tfPrecio.setText(precio + "");
	}

	public void setActionOnGuardar(ActionListener listener) {
		this.btnAceptar.addActionListener(listener);
	}

	public String getComentario() {
		return this.tfComentario.getText();
	}

	public String getNombre() {
		return this.tfNombre.getText();
	}

	public void clearData() {
		this.tfNombre.setText("");
		this.tfComentario.setText("");
		this.tfPrecio.setText("");
		this.trabajos.clearData();
		this.repuestos.clearDataRepuestosPlanificados();
	}
}
