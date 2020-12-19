
package presentacion.views.supervisor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import dto.taller.EstadoPresupuesto;
import dto.taller.OrdenDeTrabajoDTO;
import dto.taller.PresupuestoDTO;
import dto.taller.RepuestoPlanificadoDTO;
import dto.taller.TrabajoPresupuestadoDTO;
import dto.taller.IngresoOrdenDeTrabajoDTO;

import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JToolBar;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTabbedPane;

public class ConsultaDePresupuestosSupervisorView extends JPanel {

	private static final long serialVersionUID = -5623725856065117794L;
	private JTextField txtDNI;

	private static ConsultaDePresupuestosSupervisorView instance;
	private JButton btnBuscar;
	private JSplitPane splitPane;
	private JPanel panelEste;
	private JPanel panelOeste;
	private JPanel panelEsteNorte;
	private static final int EDITABLE = 6;
	private static final int ESTADO = 4;

	private final String[] columnasTablaVehiculos = new String[] { "NRO. VEHICULO", "KM GARANTIA", "ASEGURADORA",
			"NRO POLIZA SEGURO", "PATENTE" };
	private DefaultTableModel tableModelVehiculos;
	private JTable tableVehiculos;

	private final String[] columnasListadoDePresupuestos = new String[] { "NRO.", "FECHA DE ALTA", "COMENTARIO DE ALTA",
			"PRECIO", "ESTADO", "COMENTARIO DE RECHAZO", "APROBAR" };
	private DefaultTableModel listadoDePresupuestosModel;

	private final String[] columnasListadoDeRepuestos = new String[] { "CODIGO", "MARCA", "DESCRIPCIÓN", "PRECIO",
			"CANTIDAD" };
	private DefaultTableModel listadoDeRepuestosModel;

	private final String[] columnasListadoDeTrabajos = new String[] { "NRO", "DESCRIPCIÓN", "PRECIO",
			"ESFUERZO ESTIMADO Hrs." };
	private DefaultTableModel listadoDeTrabajosModel;

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
	private List<IngresoOrdenDeTrabajoDTO> vehiculosCliente;
	private JButton btnGenerarFactura;
	private Integer idOrdenDeTrabajo;

	public static ConsultaDePresupuestosSupervisorView getInstance() {
		if (instance == null) {
			instance = new ConsultaDePresupuestosSupervisorView();
		}
		return instance;
	}

	public ConsultaDePresupuestosSupervisorView() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setHgap(20);
		panel_4.getLayout();
		panel_4.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(panel_4, BorderLayout.NORTH);

		JLabel lblClienteDNI = new JLabel("DNI");
		panel_4.add(lblClienteDNI);

		txtDNI = new JTextField("");
		panel_4.add(txtDNI);
		txtDNI.setColumns(10);

		btnBuscar = new JButton("Buscar");
		panel_4.add(btnBuscar);

		splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		add(splitPane, BorderLayout.CENTER);

		panelEste = new JPanel();
		splitPane.setLeftComponent(panelEste);
		panelEste.setLayout(new BoxLayout(panelEste, BoxLayout.Y_AXIS));

		panelEsteNorte = new JPanel();
		panelEsteNorte.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ordenes de Trabajo",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelEste.add(panelEsteNorte);
		panelEsteNorte.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPaneVehiculos = new JScrollPane();
		panelEsteNorte.add(scrollPaneVehiculos);

		tableModelVehiculos = new DefaultTableModel(null, this.columnasTablaVehiculos) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 8328745018078060001L;

			public boolean isCellEditable(int row, int column) {
				return false;// This causes all cells to be not editable
			}
		};
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

		lblTipo = new JLabel("Tipo de Trabajo");
		panelEsteSur.add(lblTipo, "2, 2");

		textTipoDeTrabajo = new JTextField();
		panelEsteSur.add(textTipoDeTrabajo, "4, 2, fill, default");
		textTipoDeTrabajo.setColumns(10);

		lblNewLabel_1 = new JLabel("Fecha de Alta");
		panelEsteSur.add(lblNewLabel_1, "2, 4");

		textFechaAlta = new JTextField();
		panelEsteSur.add(textFechaAlta, "4, 4, fill, default");
		textFechaAlta.setColumns(10);

		lblNewLabel_2 = new JLabel("Trabajo Soclicitad");
		panelEsteSur.add(lblNewLabel_2, "2, 6");

		textTrabajoSolicitado = new JTextField();
		panelEsteSur.add(textTrabajoSolicitado, "4, 6, fill, default");
		textTrabajoSolicitado.setColumns(10);

		lblNewLabel_3 = new JLabel("Trabajo Sugerido");
		panelEsteSur.add(lblNewLabel_3, "2, 8");

		textTrabajoSugerido = new JTextField();
		panelEsteSur.add(textTrabajoSugerido, "4, 8, fill, default");
		textTrabajoSugerido.setColumns(10);

		lblNewLabel_4 = new JLabel("Fecha de Cierre");
		panelEsteSur.add(lblNewLabel_4, "2, 10");

		textFechaCierre = new JTextField();
		panelEsteSur.add(textFechaCierre, "4, 10, fill, default");
		textFechaCierre.setColumns(10);

		panelOeste = new JPanel();
		splitPane.setRightComponent(panelOeste);
		panelOeste.setLayout(new BoxLayout(panelOeste, BoxLayout.Y_AXIS));

		panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Listado de Presupuestos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOeste.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		scrollPanePresupuestos = new JScrollPane();
		panel.add(scrollPanePresupuestos, BorderLayout.CENTER);

		this.listadoDePresupuestosModel = new DefaultTableModel(null, this.columnasListadoDePresupuestos) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -972882702173668623L;

			public boolean isCellEditable(int row, int column) {
				return listadoDePresupuestosModel.getValueAt(row, ESTADO) == EstadoPresupuesto.PENDIENTE.name();
			}

			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Class getColumnClass(int col) {
				if (col == EDITABLE)
					return Boolean.class;
				else
					return super.getColumnClass(col);
			}
		};
		tablePresupuestos = new JTable(listadoDePresupuestosModel);
		scrollPanePresupuestos.setViewportView(tablePresupuestos);

		panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Detalles del Presupuesto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOeste.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel_1.add(tabbedPane, BorderLayout.CENTER);

		panel_3 = new JPanel();
		tabbedPane.addTab("Repuestos Planificados", null, panel_3, null);
		panel_3.setLayout(new BorderLayout(0, 0));

		scrollPaneRepuestos = new JScrollPane();
		panel_3.add(scrollPaneRepuestos, BorderLayout.CENTER);

		this.listadoDeRepuestosModel = new DefaultTableModel(null, this.columnasListadoDeRepuestos) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableRepuestos = new JTable(listadoDeRepuestosModel);
		scrollPaneRepuestos.setViewportView(tableRepuestos);

		panel_5 = new JPanel();
		tabbedPane.addTab("Trabajos Planificados", null, panel_5, null);
		panel_5.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPaneTrabajos = new JScrollPane();
		panel_5.add(scrollPaneTrabajos, BorderLayout.CENTER);

		this.listadoDeTrabajosModel = new DefaultTableModel(null, this.columnasListadoDeTrabajos) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableTrabajos = new JTable(listadoDeTrabajosModel);
		scrollPaneTrabajos.setViewportView(tableTrabajos);

		panel_2 = new JPanel();
		panelOeste.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel_2.add(toolBar, BorderLayout.NORTH);

		btnGenerarFactura = new JButton("Generar factura");
		lockButtonGenerarFactura();
		toolBar.add(btnGenerarFactura);

		this.lockOrdenDeTrabajoPanel();
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

	public void setData(List<IngresoOrdenDeTrabajoDTO> vehiculos) {
		vehiculosCliente = vehiculos;
		for (IngresoOrdenDeTrabajoDTO dto : vehiculos) {
			Object[] row = { dto.getId().toString(), dto.getKilometrajeGarantia(), dto.getAseguradora(),
					dto.getNroPolizaSeguro(), dto.getPatente() };
			this.tableModelVehiculos.addRow(row);
		}
	}

	public void clearDataListadoVehiculosCliente() {
		clearTable(tableModelVehiculos, columnasTablaVehiculos);
	}

	public IngresoOrdenDeTrabajoDTO getVehiculoSeleccionado() {
		int rows = this.tableVehiculos.getSelectedRowCount();
		if (rows == 1) {
			int row = this.tableVehiculos.getSelectedRow();
			IngresoOrdenDeTrabajoDTO dto = this.vehiculosCliente.get(row);
			return dto;
		}
		return null;
	}

	public void setActionSelectVehiculoCliente(ListSelectionListener listSelectionListener) {
		this.tableVehiculos.getSelectionModel().addListSelectionListener(listSelectionListener);
	}

	public void setData(OrdenDeTrabajoDTO ordenDeTrabajo) {
		this.idOrdenDeTrabajo = ordenDeTrabajo.getIdOrdenTrabajo();
		this.textFechaAlta.setText(ordenDeTrabajo.getFechaDeAlta().toString());
		this.textFechaCierre.setText(
				ordenDeTrabajo.getFechaEntregado() == null ? null : ordenDeTrabajo.getFechaEntregado().toString());
		this.textTrabajoSugerido.setText(ordenDeTrabajo.getTrabajoSujerido());
		this.textTrabajoSolicitado.setText(ordenDeTrabajo.getTrabajoSolicitado());
		this.textTipoDeTrabajo.setText(ordenDeTrabajo.getTipoOrdeTrabajo());
	}

	public void clearDataPresupuestos() {
		clearTable(listadoDePresupuestosModel, columnasListadoDePresupuestos);
	}

	private void lockOrdenDeTrabajoPanel() {
		this.textFechaAlta.setEditable(false);
		this.textFechaCierre.setEditable(false);
		this.textTrabajoSugerido.setEditable(false);
		this.textTrabajoSolicitado.setEditable(false);
		this.textTipoDeTrabajo.setEditable(false);
	}

	public void clearDataOrdeDeTrabajo() {
		this.idOrdenDeTrabajo = null;
		this.textFechaAlta.setText("");
		this.textFechaCierre.setText("");
		this.textTrabajoSugerido.setText("");
		this.textTrabajoSolicitado.setText("");
		this.textTipoDeTrabajo.setText("");
	}

	private void clearTable(DefaultTableModel model, String[] columns) {
		model.setRowCount(0);
		model.setColumnCount(0);
		model.setColumnIdentifiers(columns);
	}

	public Object[] readPresupuestosPresentados() {
		return listadoDePresupuestosModel.getDataVector().toArray();
	}

	public void setDataPresupuestos(List<PresupuestoDTO> presupuestos) {
		this.presupuestos = presupuestos;
		for (PresupuestoDTO dto : presupuestos) {
			boolean check = false;
			if (dto.getEstado() == EstadoPresupuesto.APROBADO) {
				check = true;
			}
			Object[] row = { dto.getIdPresupuesto().toString(), dto.getFechaAltaPresu().toString(),
					dto.getComentarioAltaPresu().toString(), dto.getPrecio(), dto.getEstado().name(),
					dto.getComentarioRechazo(), check };
			this.listadoDePresupuestosModel.addRow(row);
		}
	}

	public void setActionSelectPresupuestoCliente(ListSelectionListener listSelectionListener) {
		this.tablePresupuestos.getSelectionModel().addListSelectionListener(listSelectionListener);
	}

	public Map<Integer, Boolean> getPresupuestosPresentados() {
		Map<Integer, Boolean> presu = new HashMap<>();
		int rows = this.listadoDePresupuestosModel.getRowCount();
		for (int index = 0; index < rows; index++) {
			if (listadoDePresupuestosModel.getValueAt(index, ESTADO) == EstadoPresupuesto.PENDIENTE.name()) {
				Integer presupuestoId = Integer.parseInt(listadoDePresupuestosModel.getValueAt(index, 0).toString());
				Boolean isOk = (Boolean) listadoDePresupuestosModel.getValueAt(index, EDITABLE);
				presu.put(presupuestoId, isOk);
			}
		}
		return presu;
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
		for (RepuestoPlanificadoDTO dto : repuestos) {
			Object[] row = { dto.getRepuesto().getIdRepuesto().toString(),
					dto.getRepuesto().getMarcaRepuesto().toString(),
					dto.getRepuesto().getDescripcionRepuesto().toString(),
					dto.getRepuesto().getPrecioRepuesto().toString(), dto.getCantRequerida().toString() };
			this.listadoDeRepuestosModel.addRow(row);
		}
	}

	public void setDataTrabajos(List<TrabajoPresupuestadoDTO> trabajos) {
		for (TrabajoPresupuestadoDTO dto : trabajos) {
			Object[] row = { dto.getIdTrabajoPresu().toString(), dto.getDescripcionTrabajo().toString(),
					dto.getPrecioTrabajo().toString(), dto.getTiempoEstTrabajo().toString() };
			this.listadoDeTrabajosModel.addRow(row);
		}
	}

	public void setActionGenerarFactura(ActionListener listener) {
		this.btnGenerarFactura.addActionListener(listener);
	}

	public Integer getIdOrdenDeTrabajoPresentada() {
		return this.idOrdenDeTrabajo;
	}

	public void lockButtonGenerarFactura() {
		this.btnGenerarFactura.setEnabled(false);
	}

	public void unLockButtonGenerarFactura() {
		this.btnGenerarFactura.setEnabled(true);
	}
}