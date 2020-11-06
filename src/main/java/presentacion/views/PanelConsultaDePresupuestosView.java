package presentacion.views;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import dto.OrdenDeTrabajoDTO;
import dto.PresupuestoDTO;
import dto.RepuestoPlanificadoDTO;
import dto.TrabajoPresupuestadoDTO;
import dto.VehiculoConOrdenDeTrabajoDTO;

import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JToolBar;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTabbedPane;

public class PanelConsultaDePresupuestosView extends JPanel {

	private static final long serialVersionUID = -5623725856065117794L;
	private JTextField txtDNI;

	private static PanelConsultaDePresupuestosView instance;
	private JButton btnBuscar;
	private JSplitPane splitPane;
	private JPanel panelEste;
	private JPanel panelOeste;
	private JPanel panelEsteNorte;

	private final String[] columnasTablaVehiculos = new String[] { "NRO. VEHICULO", "KM GARANTIA", "ASEGURADORA",
			"NRO POLIZA SEGURO", "PATENTE" };
	private DefaultTableModel tableModelVehiculos;
	private JTable tableVehiculos;

	private final String[] columnasListadoDePresupuestos = new String[] { "NRO. Presupuesto", "FECHA ALTA",
			"COMENTARIO ALTA", "APROBAR" };
	private DefaultTableModel listadoDePresupuestosModel;

	private final String[] columnasListadoDeRepuestos = new String[] { "CODIGO", "MARCA", "DESCRIPCIÓN", "PRECIO",
			"CANTIDAD" };
	private DefaultTableModel listadoDeRepuestosModel;

	private final String[] columnasListadoDeTrabajos = new String[] { "NRO","DESCRIPCIÓN", "PRECIO","ESFUERZO ESTIMADO Hrs."};
	private DefaultTableModel listadoDeTrabajosModel;

	private JTextField textField;
	private JLabel lblNewLabel;
	private JPanel panelEsteSur;

	private JLabel lblTipo;
	private JTextField textTipoDeTrabajo;
	private JLabel lblNewLabel_1;
	private JTextField textFechaAlta;
	private JTextField textTrabajoSolicitado;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField textTrabajoSugerido;
	private JLabel lblNewLabel_4;
	private JTextField textFechaCierre;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JTabbedPane tabbedPane;
	private JPanel panel_3;
	private JPanel panel_5;
	private JScrollPane scrollPanePresupuestos;
	private JTable tablePresupuestos;
	private JScrollPane scrollPaneRepuestos;
	private JTable tableRepuestos;
	private JTable tableTrabajos;
	
	private List<PresupuestoDTO> presupuestos;
	private List<VehiculoConOrdenDeTrabajoDTO> vehiculosCliente;
	private List<TrabajoPresupuestadoDTO> trabajos;
	private List<RepuestoPlanificadoDTO> repuestos;
	
	public static PanelConsultaDePresupuestosView getInstance() {
		if (instance == null) {
			instance = new PanelConsultaDePresupuestosView();
		}
		return instance;
	}

	public PanelConsultaDePresupuestosView() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setHgap(20);
		panel_4.getLayout();
		panel_4.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(panel_4, BorderLayout.NORTH);

		JLabel lblClienteDNI = new JLabel("Cliente DNI");
		panel_4.add(lblClienteDNI);

		txtDNI = new JTextField("");
		panel_4.add(txtDNI);
		txtDNI.setColumns(10);

		lblNewLabel = new JLabel("PATENTE");
		panel_4.add(lblNewLabel);

		textField = new JTextField();
		panel_4.add(textField);
		textField.setColumns(10);

		btnBuscar = new JButton("Buscar");
		panel_4.add(btnBuscar);

		splitPane = new JSplitPane();
		add(splitPane, BorderLayout.CENTER);

		panelEste = new JPanel();
		splitPane.setLeftComponent(panelEste);
		panelEste.setLayout(new BoxLayout(panelEste, BoxLayout.Y_AXIS));

		panelEsteNorte = new JPanel();
		panelEsteNorte.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ordenes de trabajo",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelEste.add(panelEsteNorte);
		panelEsteNorte.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPaneVehiculos = new JScrollPane();
		panelEsteNorte.add(scrollPaneVehiculos);

		tableModelVehiculos = new DefaultTableModel(null, this.columnasTablaVehiculos);
		tableVehiculos = new JTable(tableModelVehiculos);
		scrollPaneVehiculos.setViewportView(tableVehiculos);

		panelEsteSur = new JPanel();
		panelEsteSur.setBorder(new TitledBorder(null, "Descripci\u00F3n de la orden de trabajo", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelEste.add(panelEsteSur);

		panelEsteSur.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		lblTipo = new JLabel("Tipo de trabajo");
		panelEsteSur.add(lblTipo, "2, 2");

		textTipoDeTrabajo = new JTextField();
		panelEsteSur.add(textTipoDeTrabajo, "4, 2, fill, default");
		textTipoDeTrabajo.setColumns(10);

		lblNewLabel_1 = new JLabel("Fecha de alta");
		panelEsteSur.add(lblNewLabel_1, "2, 4");

		textFechaAlta = new JTextField();
		panelEsteSur.add(textFechaAlta, "4, 4, fill, default");
		textFechaAlta.setColumns(10);

		lblNewLabel_2 = new JLabel("Trabajo soclicitad");
		panelEsteSur.add(lblNewLabel_2, "2, 6");

		textTrabajoSolicitado = new JTextField();
		panelEsteSur.add(textTrabajoSolicitado, "4, 6, fill, default");
		textTrabajoSolicitado.setColumns(10);

		lblNewLabel_3 = new JLabel("Trabajo sugerido");
		panelEsteSur.add(lblNewLabel_3, "2, 8");

		textTrabajoSugerido = new JTextField();
		panelEsteSur.add(textTrabajoSugerido, "4, 8, fill, default");
		textTrabajoSugerido.setColumns(10);

		lblNewLabel_4 = new JLabel("Fecha de cierre");
		panelEsteSur.add(lblNewLabel_4, "2, 10");

		textFechaCierre = new JTextField();
		panelEsteSur.add(textFechaCierre, "4, 10, fill, default");
		textFechaCierre.setColumns(10);

		panelOeste = new JPanel();
		splitPane.setRightComponent(panelOeste);
		panelOeste.setLayout(new BoxLayout(panelOeste, BoxLayout.Y_AXIS));

		panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Listado de presupuestos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOeste.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		scrollPanePresupuestos = new JScrollPane();
		panel.add(scrollPanePresupuestos, BorderLayout.CENTER);

		this.listadoDePresupuestosModel = new DefaultTableModel(null, this.columnasListadoDePresupuestos);

		/**
		 * 
		 */
		tablePresupuestos = new JTable(listadoDePresupuestosModel);
		scrollPanePresupuestos.setViewportView(tablePresupuestos);
		this.addCheckBox(3, tablePresupuestos);

		panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Detalles del presupuesto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOeste.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel_1.add(tabbedPane, BorderLayout.CENTER);

		panel_3 = new JPanel();
		tabbedPane.addTab("Repuestos planificados", null, panel_3, null);
		panel_3.setLayout(new BorderLayout(0, 0));

		scrollPaneRepuestos = new JScrollPane();
		panel_3.add(scrollPaneRepuestos, BorderLayout.CENTER);

		this.listadoDeRepuestosModel = new DefaultTableModel(null, this.columnasListadoDeRepuestos);
		tableRepuestos = new JTable(listadoDeRepuestosModel);
		scrollPaneRepuestos.setViewportView(tableRepuestos);

		panel_5 = new JPanel();
		tabbedPane.addTab("Trabajos planificados", null, panel_5, null);
		panel_5.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPaneTrabajos = new JScrollPane();
		panel_5.add(scrollPaneTrabajos, BorderLayout.CENTER);

		this.listadoDeTrabajosModel = new DefaultTableModel(null, this.columnasListadoDeTrabajos);
		tableTrabajos = new JTable(listadoDeTrabajosModel);
		scrollPaneTrabajos.setViewportView(tableTrabajos);

		panel_2 = new JPanel();
		panelOeste.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel_2.add(toolBar, BorderLayout.NORTH);

		JButton btnNewButton = new JButton("Generar factura");
		toolBar.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Registrar pago");
		toolBar.add(btnNewButton_1);
	}

	void addCheckBox(int column, JTable table) {
		TableColumn tc = table.getColumnModel().getColumn(column);
		tc.setCellEditor(table.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	}

	public boolean iPersupuestoAprobado(int row, int column, JTable table) {
		return table.getValueAt(row, column) != null;
	}

	public String getTextDni() {
		return this.txtDNI.getText();
	}

	public void setActionOnBuscar(ActionListener listener) {
		this.btnBuscar.addActionListener(listener);
	}

	public void setData(List<VehiculoConOrdenDeTrabajoDTO> vehiculos) {
		vehiculosCliente = vehiculos;
		for (VehiculoConOrdenDeTrabajoDTO dto : vehiculos) {
			Object[] row = { dto.getId().toString(), dto.getKilometrajeGarantia().toString(), dto.getAseguradora(),
					dto.getNroPolizaSeguro().toString(), dto.getPatente() };
			this.tableModelVehiculos.addRow(row);
		}
	}

	public void clearDataListadoVehiculosCliente() {
		clearTable(tableModelVehiculos, columnasTablaVehiculos);
	}

	public VehiculoConOrdenDeTrabajoDTO getVehiculoSeleccionado() {
		int rows = this.tableVehiculos.getSelectedRowCount();
		if (rows == 1) {
			int row = this.tableVehiculos.getSelectedRow();
			VehiculoConOrdenDeTrabajoDTO dto = this.vehiculosCliente.get(row);
			return dto;
		}
		return null;
	}
	
	public void setActionSelectVehiculoCliente(ListSelectionListener listSelectionListener) {
		this.tableVehiculos.getSelectionModel().addListSelectionListener(listSelectionListener);
	}

	public void setData(OrdenDeTrabajoDTO ordenDeTrabajo) {
		this.textFechaAlta.setText(ordenDeTrabajo.getFechaDeAlta().toString() );
		this.textFechaCierre.setText(ordenDeTrabajo.getFechaEntregado() == null ? null :ordenDeTrabajo.getFechaEntregado().toString());
		this.textTrabajoSugerido.setText(ordenDeTrabajo.getTrabajoSujerido());
		this.textTrabajoSolicitado.setText(ordenDeTrabajo.getTrabajoSolicitado());
		this.textTipoDeTrabajo.setText(ordenDeTrabajo.getTipoOrdeTrabajo());
	}

	public void clearDataPresupuestos() {
		clearTable(listadoDePresupuestosModel, columnasListadoDePresupuestos);
	}
	
	public void clearDataOrdeDeTrabajo() {
		this.textFechaAlta.setText("");
		this.textFechaCierre.setText("");
		this.textTrabajoSugerido.setText("");
		this.textTrabajoSolicitado.setText("");
		this.textTipoDeTrabajo.setText("");
	}
	
	private void clearTable(DefaultTableModel model, String [] columns) {
		model.setRowCount(0);
		model.setColumnCount(0);
		model.setColumnIdentifiers(columns);
	}
	
	public void setDataPresupuestos(List<PresupuestoDTO> presupuestos) {
		this.presupuestos = presupuestos;
		for (PresupuestoDTO dto : presupuestos) {
			Object[] row = { 
					 dto.getIdPresupuesto().toString()
					,dto.getFechaAltaPresu().toString()
					,dto.getComentarioAltaPresu().toString()};
			this.listadoDePresupuestosModel.addRow(row);
		}
	}

	public void setActionSelectPresupuestoCliente(ListSelectionListener listSelectionListener) {
		this.tablePresupuestos.getSelectionModel().addListSelectionListener(listSelectionListener);
	}

	public PresupuestoDTO getPresupuestoSeleccionado() {
		int rows = this.tablePresupuestos.getSelectedRowCount();
		if (rows == 1) {
			int row = this.tablePresupuestos.getSelectedRow();
			PresupuestoDTO dto = this.presupuestos.get(row);
			return dto;
		}
		return null;
	}

	public void clearDataTrabajos() {
		clearTable(listadoDeTrabajosModel, columnasListadoDeTrabajos);		
	}

	public void clearDataRepuestos() {
		clearTable(listadoDeRepuestosModel, columnasListadoDeRepuestos);
	}
		
	public void setDataRepuestos(List<RepuestoPlanificadoDTO> repuestos) {
		this.repuestos = repuestos;
		for (RepuestoPlanificadoDTO dto : repuestos) {
			Object[] row = { 
				 dto.getRepuesto().getIdRepuesto().toString()
				,dto.getRepuesto().getMarcaRepuesto().toString()
				,dto.getRepuesto().getDescripcionRepuesto().toString()
				,dto.getRepuesto().getPrecioRepuesto().toString()
				,dto.getCantRequerida().toString()
			};
			this.listadoDeRepuestosModel.addRow(row);
		}
	}
	
	public void setDataTrabajos(List<TrabajoPresupuestadoDTO> trabajos) {
		this.trabajos = trabajos;
		for (TrabajoPresupuestadoDTO dto : trabajos) {
			Object[] row = { 
					 dto.getIdTrabajoPresu().toString()
					,dto.getDescripcionTrabajo().toString()
					,dto.getPrecioTrabajo().toString()
					,dto.getTiempoEstTrabajo().toString()
					};
			this.listadoDeTrabajosModel.addRow(row);
		}
	}
}