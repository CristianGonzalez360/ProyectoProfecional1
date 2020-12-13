package presentacion.views.tecnico;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import dto.taller.MantenimientoDTO;
import dto.taller.PresupuestoDTO;

import java.awt.Dimension;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class AltaPresupuestoFormView extends JDialog {

	private static final long serialVersionUID = 2771968410097489023L;
	
	private static AltaPresupuestoFormView instance;
	private JButton btnAceptar;
	private JButton btnCancelar;
	
	private PlanificarTrabajosFormView trabajos;
	private PlanificarRepuestosFormView repuestos;
	private JTextField tfComentario;
	private JTextField tfFechaAlta;
	private JTextField tfPrecio;
	private JComboBox<MantenimientoDTO> comboMantenimientos;
	private JCheckBox checkBox;

	private AltaPresupuestoFormView() {
		setMinimumSize(new Dimension(1000, 600));
		setBounds(100, 100, 800, 600);
		setTitle("Formulario de Alta de Presupuesto");
		setModal(true);
		
		JPanel panelBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		
		btnAceptar = new JButton("Aceptar");
		panelBotones.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		panelBotones.add(btnCancelar);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setResizeWeight(0.5);
	    getContentPane().add(splitPane, BorderLayout.CENTER);
		
		trabajos = new PlanificarTrabajosFormView();
		repuestos = new  PlanificarRepuestosFormView();
		
		splitPane.setLeftComponent(trabajos);
		splitPane.setRightComponent(repuestos);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("86px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("94px:grow"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("84px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("86px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("76px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("278px:grow"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("45px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("86px"),},
			new RowSpec[] {
				FormSpecs.LINE_GAP_ROWSPEC,
				RowSpec.decode("top:default"),}));
		
		checkBox = new JCheckBox("");
		panel.add(checkBox, "2, 2, center, center");
		
		JLabel lblMantenimiento = new JLabel("Mantenimiento:");
		panel.add(lblMantenimiento, "4, 2, right, center");
		
		comboMantenimientos = new JComboBox<>();
		comboMantenimientos.setEnabled(false);
		panel.add(comboMantenimientos, "6, 2, fill, default");
		
		JLabel lblFechaDeAlta = new JLabel("Fecha de Alta:");
		panel.add(lblFechaDeAlta, "8, 2, right, center");
		
		tfFechaAlta = new JTextField();
		tfFechaAlta.setFocusable(false);
		tfFechaAlta.setEditable(false);
		panel.add(tfFechaAlta, "10, 2, fill, fill");
		tfFechaAlta.setColumns(10);
		
		JLabel lblComentario = new JLabel("Comentario:");
		panel.add(lblComentario, "12, 2, right, center");
		
		tfComentario = new JTextField();
		panel.add(tfComentario, "14, 2, fill, fill");
		tfComentario.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio:");
		panel.add(lblPrecio, "16, 2, right, center");
		
		tfPrecio = new JTextField();
		tfPrecio.setFocusable(false);
		tfPrecio.setEditable(false);
		panel.add(tfPrecio, "18, 2, fill, fill");
		tfPrecio.setColumns(10);
		
		setVisible(false);
	}

	public PlanificarRepuestosFormView getRepuestosPanel() {
		return repuestos;
	}

	public static AltaPresupuestoFormView getInstance() {
		if (instance == null)
			instance = new AltaPresupuestoFormView();
		return instance;
	}

	public PlanificarTrabajosFormView getTrabajosPanel() {
		return trabajos;
	}

	public void setActionOnAceptar(ActionListener listener) {
		this.btnAceptar.addActionListener(listener);
	}
	
	public void setActionOnCancelar(ActionListener listener) {
		this.btnCancelar.addActionListener(listener);
	}

	public void clearData() {
		this.trabajos.clearData();;
		this.repuestos.clearDataRepuestosPlanificados();
		this.tfComentario.setText("");
		this.tfPrecio.setText("");
	}

	public void display() {
		setVisible(true);
	}
	
	public String getComentario() {
		return this.tfComentario.getText();
	}
	
	public void setData(PresupuestoDTO presupuesto) {
		this.tfFechaAlta.setText(presupuesto.getFechaAltaPresu().toString());
		this.tfComentario.setText(presupuesto.getComentarioAltaPresu());
		this.tfPrecio.setText(presupuesto.getPrecio()+"");
		this.repuestos.setDataRepuestosPlanificados(presupuesto.getRepuestos());
		this.trabajos.setDataTrabajosPlanificados(presupuesto.getTrabajos());
	}
	
	public void setActionOnClose(WindowListener listener) {
		addWindowListener(listener);
	}

	public void close() {
		setVisible(false);
	}
	
	public void setPrecio(double precio) {
		this.tfPrecio.setText(precio+"");
	}

	public void setActionOnMantenimiento(ActionListener listener) {
		this.checkBox.addActionListener(listener);
	}
	
	public void deshabilitarEdicion() {
		this.tfComentario.setFocusable(false);
		this.tfComentario.setEditable(false);
		if (this.comboMantenimientos.getItemCount() != 0) {
			this.comboMantenimientos.setEnabled(true);
		}
		this.trabajos.deshabilitarEdición();
		this.repuestos.deshabilitarEdición();
	}
	
	public void habilitarEdicion() {
		this.tfComentario.setFocusable(true);
		this.tfComentario.setEditable(true);
		this.comboMantenimientos.setEnabled(false);
		this.trabajos.habilitarEdición();
		this.repuestos.habilitarEdición();
	}

	public boolean esMantenimiento() {
		return this.checkBox.isSelected();
	}

	public void setDataMantenimientos(List<MantenimientoDTO> datos) {
		DefaultComboBoxModel<MantenimientoDTO> model = new DefaultComboBoxModel<>();
		for (MantenimientoDTO mantenimiento : datos) {
			model.addElement(mantenimiento);
		}		
		this.comboMantenimientos.setModel(model);
	}

	public void setActionOnSeleccionar(ActionListener listener) {
		this.comboMantenimientos.addActionListener(listener);
	}

	public Integer getMantenimiento() {
		return comboMantenimientos.getModel().getElementAt(comboMantenimientos.getSelectedIndex()).getId();
	}
}
