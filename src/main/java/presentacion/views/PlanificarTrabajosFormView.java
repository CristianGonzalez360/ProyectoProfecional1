package presentacion.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import dto.TrabajoPresupuestadoDTO;

import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

public class PlanificarTrabajosFormView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8919580357142104418L;

	private final String[] columnas = new String[] { "DESCRIPCIÓN", "ESFUERZO", "PRECIO" };
	private static PlanificarTrabajosFormView instance;
	private final JPanel panelTrabajosPlanificados = new JPanel();
	private JTable table;
	
	private DefaultTableModel modelo;
	private JTextField tfEsfuerzo;
	private JTextField tfMonto;
	private JButton btnGuardar;

	private JButton btnAgregar;
	private JButton botonAceptar;
	private JButton cancelButton;
	private JEditorPane editorDescripcion;

	private PlanificarTrabajosFormView() {
		setBounds(100, 100, 450, 605);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panelAgregarTrabajo = new JPanel();
		panelAgregarTrabajo.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Nuevo Trabajo", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
		panelPrincipal.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("422px:grow"),},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:66px:grow"),
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("30px"),}));
		
				JLabel lblDescripcin = new JLabel("Descripción");
				panelPrincipal.add(lblDescripcin, "1, 1");
				
				JScrollPane scrollPane_1 = new JScrollPane();
				panelPrincipal.add(scrollPane_1, "1, 3, fill, fill");
				
				editorDescripcion = new JEditorPane();
				scrollPane_1.setViewportView(editorDescripcion);
		
				JPanel panelCentralInferior = new JPanel();
				panelPrincipal.add(panelCentralInferior, "1, 4, right, center");
				FlowLayout fl_panelCentralInferior = (FlowLayout) panelCentralInferior.getLayout();
				fl_panelCentralInferior.setAlignment(FlowLayout.RIGHT);
				
						JLabel lblMonto = new JLabel("Monto:    ");
						panelCentralInferior.add(lblMonto);
						
								tfMonto = new JTextField();
								panelCentralInferior.add(tfMonto);
								tfMonto.setColumns(10);

		JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelPrincipal.add(panelInferior, "1, 6, fill, center");

		JLabel lblEsfuerzo = new JLabel("Esfuerzo:");
		panelInferior.add(lblEsfuerzo);

		tfEsfuerzo = new JTextField();
		panelInferior.add(tfEsfuerzo);
		tfEsfuerzo.setColumns(10);
		
		
		//****
		getContentPane().add(panelAgregarTrabajo);
		
		panelTrabajosPlanificados.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Trabajos Planificados", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(panelTrabajosPlanificados);
		panelTrabajosPlanificados.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panelTrabajosPlanificados.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		table = new JTable();
		modelo = new DefaultTableModel(null, columnas);
		table.setModel(modelo);
		table.getColumnModel().getColumn(1).setMaxWidth(100);
		table.getColumnModel().getColumn(2).setMaxWidth(100);
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);

		JPanel panelBotones2 = new JPanel();
		panelTrabajosPlanificados.add(panelBotones2, BorderLayout.SOUTH);

		JButton btnQuitarTodo = new JButton("Quitar todo");
		panelBotones2.add(btnQuitarTodo);

		JButton btnQuitar = new JButton("Quitar");
		panelBotones2.add(btnQuitar);

		JButton btnEditar = new JButton("Editar");
		panelBotones2.add(btnEditar);

		btnAgregar = new JButton("Agregar");
		panelBotones2.add(btnAgregar);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane);

		botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		botonAceptar.setActionCommand("OK");
		buttonPane.add(botonAceptar);
		getRootPane().setDefaultButton(botonAceptar);

		cancelButton = new JButton("Cancelar");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		setVisible(false);
	}

	public void close() {
		setVisible(false);
	}

	public static PlanificarTrabajosFormView getInstance() {
		if (instance == null)
			instance = new PlanificarTrabajosFormView();
		return instance;
	}

	public void setActionOnAgregarTrabajo(ActionListener a) {
		this.btnAgregar.addActionListener(a);
	}

	public void clearData() {
		modelo.setRowCount(0);
	}

	public void display() {
		setVisible(true);
	}

	public void setData(List<TrabajoPresupuestadoDTO> trabajos) {
		modelo.setRowCount(0);
		for (TrabajoPresupuestadoDTO t : trabajos) {
			Object[] row = { t.getDescripcionTrabajo(), t.getTiempoEstTrabajo(), t.getPrecioTrabajo() };
			modelo.addRow(row);
		}
	}

	public void setActionOnAceptar(ActionListener listener) {
		this.botonAceptar.addActionListener(listener);
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
	}
}
