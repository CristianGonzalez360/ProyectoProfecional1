package presentacion.views.tecnico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import dto.taller.FichaTecnicaVehiculoDTO;
import dto.taller.OrdenDeTrabajoDTO;
import dto.taller.PresupuestoDTO;
import dto.taller.RepuestoPlanificadoDTO;
import dto.taller.TrabajoPresupuestadoDTO;
import dto.taller.IngresoOrdenDeTrabajoDTO;

import javax.swing.UIManager;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class PanelGestionPresupuestoView extends JPanel {

	private static final long serialVersionUID = -5623725856065117794L;
	private JTextField txtDNI;

	private static PanelGestionPresupuestoView instance;
	private JButton btnBuscar;
	private JSplitPane splitPane;
	private JPanel panelEste;
	private JPanel panelOeste;
	private JPanel panelEsteNorte;
	private List<IngresoOrdenDeTrabajoDTO> vehiculos;

	private final String[] columnasTablaVehiculos = new String[] { "NRO. VEHICULO", "KM GARANTIA", "ASEGURADORA",
			"NRO POLIZA SEGURO", "PATENTE" };
	private DefaultTableModel tableModelVehiculos;
	private JTable tableVehiculos;

	private final String[] columnasListadoDePresupuestos = new String[] { "NRO. Presupuesto", "FECHA ALTA",
			"COMENTARIO ALTA", "PRECIO", "GARANTÍA" };
	private DefaultTableModel listadoDePresupuestosModel;

	private final String[] columnasListadoDeRepuestos = new String[] { "CODIGO", "MARCA", "DESCRIPCIÓN", "PRECIO",
			"CANTIDAD", "GARANTÍA" };
	private DefaultTableModel listadoDeRepuestosModel;

	private final String[] columnasListadoDeTrabajos = new String[] { "NRO", "FECHA ALTA", "DESCRIPCIÓN", "PRECIO",
			"ESTIMADO", "FECHA CIERRE" };
	private DefaultTableModel listadoDeTrabajosModel;
	private JPanel panelEsteSur;

	private JLabel lblTipo;
	private JTextField textTipoTrabajo;
	private JTextField textFechaCierreOt;
	private JTextField textTrabajoSolicitadoOt;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField textTrabajoSugeridoOt;
	private JLabel lblNewLabel_4;
	private JTextField textFechaAltaOt;
	private JPanel panel_1;
	private JTabbedPane tabbedPane;
	private JPanel panel_3;
	private JPanel panel_5;
	private JScrollPane scrollPaneRepuestos;
	private JTable tableRepuestos;
	private JTable tableTrabajos;
	private JPanel panel_7;
	private JToolBar toolBar;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JTextField textMarca;
	private JLabel lblNewLabel_5;
	private JTextField textModelo;
	private JLabel lblNewLabel_6;
	private JTextField textColor;
	private JLabel lblNewLabel_7;
	private JTextField textCombustion;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JTextField textNroMotor;
	private JTextField textNroDeChasis;
	private Integer idOrdenDeTrabajo;
	private JPanel panel_8;
	private JScrollPane scrollPane;
	private JTable tablePresupuestos;
	private List<Integer> idsPresupuestos;
	private JButton btnNuevoPresupuesto;
	private JCheckBox chckbxGarantia;

	public static PanelGestionPresupuestoView getInstance() {
		if (instance == null) {
			instance = new PanelGestionPresupuestoView();
		}
		return instance;
	}

	private PanelGestionPresupuestoView() {
		setLayout(new BorderLayout(0, 0));

		this.idsPresupuestos = new ArrayList<>();
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

		btnBuscar = new JButton("Buscar");
		panel_4.add(btnBuscar);

		splitPane = new JSplitPane();
		add(splitPane, BorderLayout.CENTER);

		panelEste = new JPanel();
		splitPane.setLeftComponent(panelEste);
		panelEste.setLayout(new BoxLayout(panelEste, BoxLayout.Y_AXIS));

		panelEsteNorte = new JPanel();
		panelEsteNorte.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Ordenes de trabajo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelEste.add(panelEsteNorte);
		panelEsteNorte.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPaneVehiculos = new JScrollPane();
		panelEsteNorte.add(scrollPaneVehiculos);

		tableModelVehiculos = new DefaultTableModel(null, this.columnasTablaVehiculos);
		tableVehiculos = new JTable(tableModelVehiculos) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPaneVehiculos.setViewportView(tableVehiculos);

		panelEsteSur = new JPanel();
		panelEsteSur.setBorder(
				new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Descripci\u00F3n de la orden de trabajo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelEste.add(panelEsteSur);

		panelEsteSur.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), }));

		lblTipo = new JLabel("Tipo de trabajo");
		panelEsteSur.add(lblTipo, "2, 2");

		textTipoTrabajo = new JTextField();
		textTipoTrabajo.setEditable(false);
		panelEsteSur.add(textTipoTrabajo, "4, 2, fill, default");
		textTipoTrabajo.setColumns(10);

		lblNewLabel = new JLabel("Alta");
		panelEsteSur.add(lblNewLabel, "6, 2");

		textFechaAltaOt = new JTextField();
		textFechaAltaOt.setEditable(false);
		panelEsteSur.add(textFechaAltaOt, "8, 2, fill, default");
		textFechaAltaOt.setColumns(10);

		lblNewLabel_4 = new JLabel("Cierre");
		panelEsteSur.add(lblNewLabel_4, "2, 4");

		textFechaCierreOt = new JTextField();
		textFechaCierreOt.setEditable(false);
		panelEsteSur.add(textFechaCierreOt, "4, 4, fill, default");
		textFechaCierreOt.setColumns(10);
		
		chckbxGarantia = new JCheckBox("Garantia");
		chckbxGarantia.setFocusable(false);
		chckbxGarantia.setHorizontalTextPosition(SwingConstants.LEFT);
		for (EventListener eventListener : chckbxGarantia.getListeners(MouseListener.class)) {
			chckbxGarantia.removeMouseListener((MouseListener) eventListener);
		}
		panelEsteSur.add(chckbxGarantia, "8, 4, center, center");

		lblNewLabel_3 = new JLabel("Trabajo sugerido");
		panelEsteSur.add(lblNewLabel_3, "2, 6");

		textTrabajoSugeridoOt = new JTextField();
		textTrabajoSugeridoOt.setEditable(false);
		panelEsteSur.add(textTrabajoSugeridoOt, "4, 6, 5, 1, fill, default");
		textTrabajoSugeridoOt.setColumns(10);

		lblNewLabel_2 = new JLabel("Trabajo soclicitad");
		panelEsteSur.add(lblNewLabel_2, "2, 8");

		textTrabajoSolicitadoOt = new JTextField();
		textTrabajoSolicitadoOt.setEditable(false);
		panelEsteSur.add(textTrabajoSolicitadoOt, "4, 8, 5, 1, fill, default");
		textTrabajoSolicitadoOt.setColumns(10);

		panel = new JPanel();
		panel.setBorder(
				new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Ficha t\u00E9cnica del veh\u00EDculo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelEste.add(panel);
		panel.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(33dlu;default)"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(65dlu;default):grow"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(48dlu;default)"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(89dlu;default):grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		lblNewLabel_1 = new JLabel("Marca");
		panel.add(lblNewLabel_1, "2, 2");

		textMarca = new JTextField();
		textMarca.setEditable(false);

		panel.add(textMarca, "4, 2, fill, default");
		textMarca.setColumns(10);

		lblNewLabel_5 = new JLabel("Modelo");
		panel.add(lblNewLabel_5, "6, 2");

		textModelo = new JTextField();
		textModelo.setEditable(false);
		panel.add(textModelo, "8, 2, fill, default");
		textModelo.setColumns(10);

		lblNewLabel_6 = new JLabel("Color");
		panel.add(lblNewLabel_6, "2, 4");

		textColor = new JTextField();
		textColor.setEditable(false);
		panel.add(textColor, "4, 4, fill, default");
		textColor.setColumns(10);

		lblNewLabel_7 = new JLabel("Combustion");
		panel.add(lblNewLabel_7, "6, 4");

		textCombustion = new JTextField();
		textCombustion.setEditable(false);
		panel.add(textCombustion, "8, 4, fill, default");
		textCombustion.setColumns(10);

		lblNewLabel_8 = new JLabel("Nro. Chasis");
		panel.add(lblNewLabel_8, "2, 6");

		textNroDeChasis = new JTextField();
		textNroDeChasis.setEditable(false);
		panel.add(textNroDeChasis, "4, 6, fill, default");
		textNroDeChasis.setColumns(10);

		lblNewLabel_9 = new JLabel("Motor");
		panel.add(lblNewLabel_9, "6, 6");

		textNroMotor = new JTextField();
		textNroMotor.setEditable(false);
		panel.add(textNroMotor, "8, 6, fill, default");
		textNroMotor.setColumns(10);

		panelOeste = new JPanel();
		splitPane.setRightComponent(panelOeste);
		panelOeste.setLayout(new BoxLayout(panelOeste, BoxLayout.Y_AXIS));

		this.listadoDePresupuestosModel = new DefaultTableModel(null, this.columnasListadoDePresupuestos) {

			private static final long serialVersionUID = 3547862522448460634L;

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return columnIndex == 4 ? Boolean.class : super.getColumnClass(columnIndex);
			}
		};

		panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Presupuestos", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelOeste.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));

		tablePresupuestos = new JTable(listadoDePresupuestosModel) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPane = new JScrollPane(tablePresupuestos);
		panel_8.add(scrollPane, BorderLayout.CENTER);

		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Detalles del presupuesto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelOeste.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel_1.add(tabbedPane);

		panel_3 = new JPanel();
		tabbedPane.addTab("Repuestos planificados", null, panel_3, null);
		panel_3.setLayout(new BorderLayout(0, 0));

		scrollPaneRepuestos = new JScrollPane();
		panel_3.add(scrollPaneRepuestos, BorderLayout.CENTER);

		this.listadoDeRepuestosModel = new DefaultTableModel(null, this.columnasListadoDeRepuestos) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return columnIndex == 5 ? Boolean.class : super.getColumnClass(columnIndex);
			}
		};
		tableRepuestos = new JTable(listadoDeRepuestosModel) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPaneRepuestos.setViewportView(tableRepuestos);

		panel_5 = new JPanel();
		tabbedPane.addTab("Trabajos planificados", null, panel_5, null);
		panel_5.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPaneTrabajos = new JScrollPane();
		panel_5.add(scrollPaneTrabajos, BorderLayout.CENTER);

		this.listadoDeTrabajosModel = new DefaultTableModel(null, this.columnasListadoDeTrabajos);
		tableTrabajos = new JTable(listadoDeTrabajosModel) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPaneTrabajos.setViewportView(tableTrabajos);

		panel_7 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_7.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelOeste.add(panel_7);

		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel_7.add(toolBar);

		btnNuevoPresupuesto = new JButton("Nuevo Presupuesto");
		toolBar.add(btnNuevoPresupuesto);
	}

	void addCheckBox(int column, JTable table) {
	}

	public boolean iPersupuestoAprobado(int row, int column, JTable table) {
		return table.getValueAt(row, column) != null;
	}

	public void setDataRepuestosPlanificados(List<RepuestoPlanificadoDTO> repuestos) {
		this.listadoDeRepuestosModel.setRowCount(0);
		for (RepuestoPlanificadoDTO r : repuestos) {
			Object[] row = { r.getRepuesto().getCodigoRepuesto(), r.getRepuesto().getMarcaRepuesto(),
					r.getRepuesto().getDescripcionRepuesto(), r.getRepuesto().getPrecioRepuesto(), r.getCantRequerida(),
					r.getRepuesto().isGarantia() };
			this.listadoDeRepuestosModel.addRow(row);
		}
	}

	public void setDataTrabajosPlanificados(List<TrabajoPresupuestadoDTO> trabajos) {
		this.listadoDeTrabajosModel.setRowCount(0);
		for (TrabajoPresupuestadoDTO t : trabajos) {
			Object[] row = { t.getIdTrabajoPresu(), t.getFechaAlta(), t.getDescripcionTrabajo(), t.getPrecioTrabajo(),
					t.getTiempoEstTrabajo(), t.getFechaCierre() };
			this.listadoDeTrabajosModel.addRow(row);
		}
	}

	public void setActionOnBuscar(ActionListener listener) {
		this.btnBuscar.addActionListener(listener);
	}

	public void setActionSelectVehiculoCliente(ListSelectionListener listener) {
		this.tableVehiculos.getSelectionModel().addListSelectionListener(listener);
	}

	public IngresoOrdenDeTrabajoDTO getidVehiculoSeleccionado() {
		int rows = this.tableVehiculos.getSelectedRowCount();
		if (rows == 1) {
			int row = this.tableVehiculos.getSelectedRow();
			IngresoOrdenDeTrabajoDTO dto = this.vehiculos.get(row);
			return dto;
		}
		return null;
	}

	public void setData(List<IngresoOrdenDeTrabajoDTO> vehiculos) {
		this.vehiculos = vehiculos;
		for (IngresoOrdenDeTrabajoDTO dto : vehiculos) {
			Object[] row = { dto.getId().toString(), dto.getKilometrajeGarantia().toString(), dto.getAseguradora(),
					dto.getNroPolizaSeguro().toString(), dto.getPatente() };
			this.tableModelVehiculos.addRow(row);
		}
	}

	public void clearDataFichaTecnicaVehiculo() {
		this.textMarca.setText("");
		this.textColor.setText("");
		this.textNroDeChasis.setText("");
		this.textModelo.setText("");
		this.textCombustion.setText("");
		this.textNroMotor.setText("");
	}

	public void clearDataOrdenDeTrabajo() {
		this.textTipoTrabajo.setText("");
		this.textTrabajoSolicitadoOt.setText("");
		this.textTrabajoSugeridoOt.setText("");
		this.textFechaAltaOt.setText("");
		this.textFechaCierreOt.setText("");
		this.idOrdenDeTrabajo = null;
		this.idsPresupuestos.clear();
	}

	public void setData(FichaTecnicaVehiculoDTO fichaVehiculo) {
		fichaVehiculo.getId();
		this.textNroDeChasis.setText(fichaVehiculo.getNroChasis().toString());
		this.textNroMotor.setText(fichaVehiculo.getNroMotor().toString());
		this.textMarca.setText(fichaVehiculo.getMarca());
		this.textColor.setText(fichaVehiculo.getColor());
		this.textCombustion.setText(fichaVehiculo.getCombustion());
		this.textModelo.setText(fichaVehiculo.getModelo().toString());
	}

	public Integer getIdOrdenDeTrabajo() {
		return this.idOrdenDeTrabajo;
	}

	public void setData(OrdenDeTrabajoDTO ordenDeTrabajo) {
		this.idOrdenDeTrabajo = ordenDeTrabajo.getIdOrdenTrabajo();
		this.textTipoTrabajo.setText(ordenDeTrabajo.getTipoOrdeTrabajo());
		this.textFechaAltaOt.setText(ordenDeTrabajo.getFechaDeAlta().toString());
		this.textFechaCierreOt.setText(
				ordenDeTrabajo.getFechaEntregado() != null ? ordenDeTrabajo.getFechaEntregado().toString() : "");
		this.textTrabajoSugeridoOt.setText(ordenDeTrabajo.getTrabajoSujerido());
		this.textTrabajoSolicitadoOt.setText(ordenDeTrabajo.getTrabajoSolicitado());
		this.idOrdenDeTrabajo = ordenDeTrabajo.getIdOrdenTrabajo();
	}

	public void clearDataListadoVehiculosCliente() {
		this.vehiculos = null;
		this.tableModelVehiculos.setRowCount(0);
		tableModelVehiculos.setColumnCount(0);
		tableModelVehiculos.setColumnIdentifiers(columnasTablaVehiculos);
	}

	public void clearAll() {
		this.clearDataPresupuestos();
		this.clearDataFichaTecnicaVehiculo();
		this.clearDataListadoVehiculosCliente();
		this.clearDataOrdenDeTrabajo();
	}

	public String getTxtDni() {
		return txtDNI.getText();
	}

	public void setDataPresupuestos(List<PresupuestoDTO> presupuestos) {
		clearDataPresupuestos();
		for (PresupuestoDTO p : presupuestos) {
			this.idsPresupuestos.add(p.getIdPresupuesto());
			Object[] row = { p.getIdPresupuesto(), p.getFechaAltaPresu(), p.getComentarioAltaPresu(), p.getPrecio(),
					p.isGarantia() };
			this.listadoDePresupuestosModel.addRow(row);
		}
	}

	public void clearDataPresupuestos() {
		listadoDePresupuestosModel.setRowCount(0);
		listadoDeRepuestosModel.setRowCount(0);
		listadoDeTrabajosModel.setRowCount(0);
		idsPresupuestos.clear();
	}

	public void setActionOnSeleccionarPresupuesto(ListSelectionListener listener) {
		this.tablePresupuestos.getSelectionModel().addListSelectionListener(listener);
	}

	public Integer getIdPresupuesto() {
		Integer fila = tablePresupuestos.getSelectedRow();
		if (fila >= 0) {
			fila = this.idsPresupuestos.get(fila);
		}
		return fila;
	}

	public void setDataPresupuesto(PresupuestoDTO presupuesto) {
		setDataRepuestosPlanificados(presupuesto.getRepuestos());
		setDataTrabajosPlanificados(presupuesto.getTrabajos());
	}

	public void setActionOnNuevoPresupuesto(ActionListener listener) {
		this.btnNuevoPresupuesto.addActionListener(listener);
	}

	public void setGarantia(boolean garantia) {
		this.chckbxGarantia.setSelected(garantia);
	}

}
