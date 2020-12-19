package presentacion.views.tecnico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import dto.taller.TrabajoMantenimientoDTO;
import dto.taller.TrabajoPresupuestadoDTO;

public class PlanificarTrabajosFormView extends JPanel {
	private static final long serialVersionUID = -8919580357142104418L;

	private final String[] columnas = new String[] { "DESCRIPCIÓN", "ESFUERZO", "PRECIO" };
	private final JPanel panelTrabajosPlanificados = new JPanel();
	private JTable table;

	private DefaultTableModel modelo;
	private JTextField tfEsfuerzo;
	private JTextField tfMonto;
	private JButton btnAgregar;
	private JEditorPane editorDescripcion;
	private JButton btnQuitar;

	public PlanificarTrabajosFormView() {
		setBounds(100, 100, 500, 600);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel panelAgregarTrabajo = new JPanel();
		panelAgregarTrabajo.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Nuevo Trabajo", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelAgregarTrabajo.setLayout(new BorderLayout(0, 0));

		JPanel panelBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelAgregarTrabajo.add(panelBotones, BorderLayout.SOUTH);

		btnAgregar = new JButton("Agregar");
		panelBotones.add(btnAgregar);

		JPanel panelPrincipal = new JPanel();
		panelAgregarTrabajo.add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new FormLayout(new ColumnSpec[] { ColumnSpec.decode("422px:grow"), },
				new RowSpec[] { FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("fill:66px:grow"), FormSpecs.DEFAULT_ROWSPEC, RowSpec.decode("30px"), }));

		JLabel lblDescripcin = new JLabel("Descripción");
		panelPrincipal.add(lblDescripcin, "1, 1, default, center");

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
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
		panelPrincipal.add(panelInferior, "1, 5, fill, center");

		JLabel lblEsfuerzo = new JLabel("Esfuerzo:");
		panelInferior.add(lblEsfuerzo);

		tfEsfuerzo = new JTextField();
		panelInferior.add(tfEsfuerzo);
		tfEsfuerzo.setColumns(10);

		add(panelAgregarTrabajo);

		panelTrabajosPlanificados
				.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Trabajos Planificados", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panelTrabajosPlanificados);
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
		FlowLayout flowLayout_1 = (FlowLayout) panelBotones2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panelTrabajosPlanificados.add(panelBotones2, BorderLayout.SOUTH);

		btnQuitar = new JButton("Quitar");
		panelBotones2.add(btnQuitar);

		JButton btnQuitarTodo = new JButton("Limpiar");
		btnQuitarTodo.setVisible(false);
		panelBotones2.add(btnQuitarTodo);
	}

	public void clearData() {
		clearDataNuevoTrabajo();
		modelo.setRowCount(0);
	}

	public void display() {
		setVisible(true);
	}

	public void setDataTrabajosPlanificados(List<TrabajoPresupuestadoDTO> trabajos) {
		modelo.setRowCount(0);
		for (TrabajoPresupuestadoDTO t : trabajos) {
			Object[] row = { t.getDescripcionTrabajo(), t.getTiempoEstTrabajo(), t.getPrecioTrabajo() };
			modelo.addRow(row);
		}
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

	public void setActionOnAgregarTrabajo(ActionListener listener) {
		this.btnAgregar.addActionListener(listener);
	}

	public void setActionOnQuitarTrabajo(ActionListener listener) {
		this.btnQuitar.addActionListener(listener);
	}

	public Integer getSeleccionado() {
		return table.getSelectedRow();
	}

	public String getDescripcion() {
		return this.editorDescripcion.getText();
	}

	public String getMonto() {
		return this.tfMonto.getText();
	}

	public String getEsfuerzo() {
		return this.tfEsfuerzo.getText();
	}

	public void setDataTrabajosMantenimiento(List<TrabajoMantenimientoDTO> trabajos) {
		modelo.setRowCount(0);
		for (TrabajoMantenimientoDTO t : trabajos) {
			Object[] row = { t.getDescripcionTrabajo(), t.getTiempoEstTrabajo(), t.getPrecioTrabajo() };
			modelo.addRow(row);
		}
	}

	public void deshabilitarEdición() {
		this.editorDescripcion.setEditable(false);
		this.editorDescripcion.setFocusable(false);
		this.btnAgregar.setEnabled(false);
		this.btnQuitar.setEnabled(false);
		this.tfMonto.setEditable(false);
		this.tfMonto.setFocusable(false);
		this.tfEsfuerzo.setEditable(false);
		this.tfEsfuerzo.setFocusable(false);
	}

	public void habilitarEdición() {
		this.editorDescripcion.setEditable(true);
		this.editorDescripcion.setFocusable(true);
		this.btnAgregar.setEnabled(true);
		this.btnQuitar.setEnabled(true);
		this.tfMonto.setEditable(true);
		this.tfMonto.setFocusable(true);
		this.tfEsfuerzo.setEditable(true);
		this.tfEsfuerzo.setFocusable(true);
	}

}
