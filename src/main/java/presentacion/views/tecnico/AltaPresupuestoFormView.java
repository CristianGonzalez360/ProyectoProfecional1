package presentacion.views.tecnico;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import dto.PresupuestoDTO;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Dimension;

public class AltaPresupuestoFormView extends JDialog {

	private static final long serialVersionUID = 2771968410097489023L;
	
	private static AltaPresupuestoFormView instance;
	private JButton btnAceptar;
	private JButton btnCancelar;
	
	private PlanificarTrabajosFormView trabajos;
	private PlanificarRepuestosFormView repuestos;
	private JTextField tftNumero;
	private JTextField tfComentario;
	private JTextField tfFechaAlta;
	private JTextField tfPrecio;

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
		
		trabajos = PlanificarTrabajosFormView.getInstance();
		repuestos = PlanificarRepuestosFormView.getInstance();
		
		splitPane.setLeftComponent(trabajos);
		splitPane.setRightComponent(repuestos);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("57px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("94px"),
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
				RowSpec.decode("top:28px"),}));
		
		JLabel lblNmero = new JLabel("Número:");
		panel.add(lblNmero, "2, 2, right, center");
		
		tftNumero = new JTextField();
		tftNumero.setFocusable(false);
		tftNumero.setEditable(false);
		panel.add(tftNumero, "4, 2, fill, fill");
		tftNumero.setColumns(10);
		
		JLabel lblFechaDeAlta = new JLabel("Fecha de Alta:");
		panel.add(lblFechaDeAlta, "6, 2, right, center");
		
		tfFechaAlta = new JTextField();
		tfFechaAlta.setFocusable(false);
		tfFechaAlta.setEditable(false);
		panel.add(tfFechaAlta, "8, 2, fill, fill");
		tfFechaAlta.setColumns(10);
		
		JLabel lblComentario = new JLabel("Comentario:");
		panel.add(lblComentario, "10, 2, right, center");
		
		tfComentario = new JTextField();
		panel.add(tfComentario, "12, 2, fill, fill");
		tfComentario.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio:");
		panel.add(lblPrecio, "14, 2, right, center");
		
		tfPrecio = new JTextField();
		tfPrecio.setFocusable(false);
		tfPrecio.setEditable(false);
		panel.add(tfPrecio, "16, 2, fill, fill");
		tfPrecio.setColumns(10);
		
		setVisible(false);
	}

	public static AltaPresupuestoFormView getInstance() {
		if (instance == null)
			instance = new AltaPresupuestoFormView();
		return instance;
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
		this.tfFechaAlta.setText("");
		this.tfPrecio.setText("");
		this.tftNumero.setText("");
	}

	public void display() {
		setVisible(true);
	}
	
	public String getComentario() {
		return this.tfComentario.getText();
	}
	
	public void setData(PresupuestoDTO presupuesto) {
		this.tftNumero.setText(presupuesto.getIdPresupuesto()+"");
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
	
}
