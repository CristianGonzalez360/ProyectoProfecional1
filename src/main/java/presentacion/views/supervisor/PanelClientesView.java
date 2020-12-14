package presentacion.views.supervisor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import dto.ClienteDTO;
import dto.GarantiaVehiculoDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
import dto.taller.OrdenDeTrabajoDTO;
import dto.taller.VehiculoConOrdenDeTrabajoDTO;
import presentacion.views.vendedor.PanelCaracteristicasDeLaGarantia;

import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
import javax.swing.JToolBar;

public class PanelClientesView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5623725856065117794L;
	private JTextField txtDNI;

	private final String[] columnasTablaVehiculos = new String[] { "NRO. VEHICULO", "KM GARANTIA", "ASEGURADORA",
			"NRO POLIZA SEGURO", "PATENTE" };
	private DefaultTableModel tableModelVehiculos;

	private static PanelClientesView instance;
	private JButton btnBuscar;
	private JSplitPane splitPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JTable table;

	private Integer idCliente;
	private Integer idDatosPersonalesDelCliente;
	private JTextField textDni;
	private JTextField textNombre;
	private JTextField textTelefono;
	private JTextField textEmail;
	private JTextField textCalle;
	private JTextField textAltura;
	private JTextField textPiso;
	private JTextField textDpto;
	private JTextField textLocalidad;

	private JTextField textNroMotor;
	private JTextField textNroDeChasis;
	private JTextField textMarca;
	private JTextField textModelo;
	private JTextField textColor;
	private JTextField textCombustion;

	private List<VehiculoConOrdenDeTrabajoDTO> vehiculos;
	private JPanel panel_7;
	private JToolBar toolBar;
	private JButton btnRegistrarNuevoVehiculo;
	private JButton btnRegistrarNuevoCliente;
	private JPanel panel_6;
	private JPanel panel_8;

	private JLabel lblNewLabel_15;
	private JTextField textFechaAltaOt;
	private JLabel lblNewLabel_16;
	private JTextField textFechaCierreOt;
	private JLabel lblNewLabel_17;
	private JTextField textTrabajoSolicitadoOt;
	private JLabel lblNewLabel_18;
	private JTextField textTrabajoSugeridoOt;
	private JToolBar toolBar_1;
	private JButton btnRegistrarOrdenDeTrabajo;
	private JButton btnEditarCliente;
	private JLabel lblTipoDeOrden;
	private JTextField textTipoDeOT;
	private JLabel lblApellido;
	private JTextField textApellido;
	private PanelCaracteristicasDeLaGarantia garantia;

	public PanelClientesView() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
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

		panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel_2 = new JPanel();
		panel_2.setBorder(
				new TitledBorder(null, "Datos del cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_2);
		panel_2.setLayout(new FormLayout(
				new ColumnSpec[] { ColumnSpec.decode("14px"), ColumnSpec.decode("right:max(0dlu;default)"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(80dlu;default):grow"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("right:66px:grow"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(93dlu;default):grow"),
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("26px"), FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		panel_2.add(lblNewLabel_1, "2, 4");

		textNombre = new JTextField();
		panel_2.add(textNombre, "4, 4, fill, default");
		textNombre.setColumns(10);

		lblApellido = new JLabel("Apellido");
		panel_2.add(lblApellido, "6, 4, right, default");

		textApellido = new JTextField();
		panel_2.add(textApellido, "8, 4, fill, default");
		textApellido.setColumns(10);

		JLabel lblNewLabel = new JLabel("Dni");
		panel_2.add(lblNewLabel, "2, 6");

		textDni = new JTextField();
		panel_2.add(textDni, "4, 6, fill, default");
		textDni.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Telefono");
		panel_2.add(lblNewLabel_2, "6, 6, right, default");

		textTelefono = new JTextField();
		panel_2.add(textTelefono, "8, 6, fill, default");
		textTelefono.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Email");
		panel_2.add(lblNewLabel_3, "2, 8");

		textEmail = new JTextField();
		panel_2.add(textEmail, "4, 8, fill, default");
		textEmail.setColumns(10);

		JLabel Calle = new JLabel("calle");
		panel_2.add(Calle, "6, 8");

		textCalle = new JTextField();
		panel_2.add(textCalle, "8, 8, fill, default");
		textCalle.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Altura");
		panel_2.add(lblNewLabel_4, "2, 10");

		textAltura = new JTextField();
		panel_2.add(textAltura, "4, 10, fill, default");
		textAltura.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Piso");
		panel_2.add(lblNewLabel_5, "6, 10");

		textPiso = new JTextField();
		panel_2.add(textPiso, "8, 10, fill, default");
		textPiso.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Dpto");
		panel_2.add(lblNewLabel_6, "2, 12");

		textDpto = new JTextField();
		panel_2.add(textDpto, "4, 12, fill, default");
		textDpto.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("localidad");
		panel_2.add(lblNewLabel_7, "6, 12, right, default");

		textLocalidad = new JTextField();
		panel_2.add(textLocalidad, "8, 12, fill, default");
		textLocalidad.setColumns(10);

		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Listado de vehiculos del cliente", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane);

		tableModelVehiculos = new DefaultTableModel(null, this.columnasTablaVehiculos) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -8904398996893892884L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(tableModelVehiculos);
		scrollPane.setViewportView(table);

		panel_7 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_7.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_3.add(panel_7, BorderLayout.SOUTH);

		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel_7.add(toolBar);

		btnRegistrarNuevoCliente = new JButton("Registrar nuevo cliente");
		toolBar.add(btnRegistrarNuevoCliente);

		btnEditarCliente = new JButton("Editar cliente");
		toolBar.add(btnEditarCliente);

		btnRegistrarNuevoVehiculo = new JButton("Registrar nuevo vehiculo");
		toolBar.add(btnRegistrarNuevoVehiculo);

		panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Ficha tecnica del vehiculo", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel_1.add(panel_5);
		panel_5.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblNewLabel_8 = new JLabel("Nro de motor");
		panel_5.add(lblNewLabel_8, "2, 2");

		textNroMotor = new JTextField();
		panel_5.add(textNroMotor, "4, 2, fill, default");
		textNroMotor.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Nro de chasis");
		panel_5.add(lblNewLabel_9, "2, 4");

		textNroDeChasis = new JTextField();
		panel_5.add(textNroDeChasis, "4, 4, fill, default");
		textNroDeChasis.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("Marca");
		panel_5.add(lblNewLabel_10, "2, 6");

		textMarca = new JTextField();
		panel_5.add(textMarca, "4, 6, fill, default");
		textMarca.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("Modelo");
		panel_5.add(lblNewLabel_11, "2, 8");

		textModelo = new JTextField();
		panel_5.add(textModelo, "4, 8, fill, default");
		textModelo.setColumns(10);

		JLabel lblNewLabel_12 = new JLabel("Color");
		panel_5.add(lblNewLabel_12, "2, 10");

		textColor = new JTextField();
		panel_5.add(textColor, "4, 10, fill, default");
		textColor.setColumns(10);

		JLabel lblNewLabel_13 = new JLabel("Combustion");
		panel_5.add(lblNewLabel_13, "2, 12");

		textCombustion = new JTextField();
		panel_5.add(textCombustion, "4, 12, fill, default");
		textCombustion.setColumns(10);

		garantia = new PanelCaracteristicasDeLaGarantia();
		panel_1.add(garantia);
		
		panel_6 = new JPanel();
		panel_6.setBorder(
				new TitledBorder(null, "Orden de trabajo actual", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_6);
		panel_6.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, }));

		lblNewLabel_15 = new JLabel("Fecha de alta");
		panel_6.add(lblNewLabel_15, "2, 2");

		textFechaAltaOt = new JTextField();
		textFechaAltaOt.setEditable(false);
		panel_6.add(textFechaAltaOt, "4, 2, fill, default");
		textFechaAltaOt.setColumns(10);

		lblNewLabel_16 = new JLabel("Fecha de cierre");
		panel_6.add(lblNewLabel_16, "2, 4");

		textFechaCierreOt = new JTextField();
		textFechaCierreOt.setEditable(false);
		panel_6.add(textFechaCierreOt, "4, 4, fill, default");
		textFechaCierreOt.setColumns(10);

		lblNewLabel_17 = new JLabel("Trabajo solicitado");
		panel_6.add(lblNewLabel_17, "2, 6, left, default");

		textTrabajoSolicitadoOt = new JTextField();
		textTrabajoSolicitadoOt.setEditable(false);
		panel_6.add(textTrabajoSolicitadoOt, "4, 6, fill, default");
		textTrabajoSolicitadoOt.setColumns(10);

		lblNewLabel_18 = new JLabel("Trabajo sugerido");
		panel_6.add(lblNewLabel_18, "2, 8");

		textTrabajoSugeridoOt = new JTextField();
		textTrabajoSugeridoOt.setEditable(false);
		panel_6.add(textTrabajoSugeridoOt, "4, 8, fill, default");
		textTrabajoSugeridoOt.setColumns(10);

		lblTipoDeOrden = new JLabel("Tipo de orden de trabajo");
		panel_6.add(lblTipoDeOrden, "2, 10, right, default");

		textTipoDeOT = new JTextField();
		textTipoDeOT.setEditable(false);
		panel_6.add(textTipoDeOT, "4, 10, fill, default");
		textTipoDeOT.setColumns(10);

		panel_8 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_8.getLayout();
		flowLayout.setVgap(8);
		panel_1.add(panel_8);

		toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		panel_8.add(toolBar_1);

		btnRegistrarOrdenDeTrabajo = new JButton("Registrar orden de trabajo");
		toolBar_1.add(btnRegistrarOrdenDeTrabajo);

		disableAllClienteInputs();
		disableAllFichaTecnicaInputs();
	}

	private void disableAllClienteInputs() {
		this.textNombre.setEditable(false);
		this.textApellido.setEditable(false);
		this.textDni.setEditable(false);
		this.textTelefono.setEditable(false);
		this.textEmail.setEditable(false);
		this.textCalle.setEditable(false);
		this.textAltura.setEditable(false);
		this.textPiso.setEditable(false);
		this.textDpto.setEditable(false);
		this.textLocalidad.setEditable(false);
	}

	private void disableAllFichaTecnicaInputs() {
		this.textNroDeChasis.setEditable(false);
		this.textNroMotor.setEditable(false);
		this.textMarca.setEditable(false);
		this.textColor.setEditable(false);
		this.textCombustion.setEditable(false);
		this.textModelo.setEditable(false);
	}

	public static PanelClientesView getInstance() {
		if (instance == null) {
			instance = new PanelClientesView();
		}
		return instance;
	}

	public void clearDataCliente() {
		this.idCliente = null;
		this.idDatosPersonalesDelCliente = null;
		this.textNombre.setText("");
		this.textApellido.setText("");
		this.textDni.setText("");
		this.textTelefono.setText("");
		this.textEmail.setText("");
		this.textCalle.setText("");
		this.textAltura.setText("");
		this.textPiso.setText("");
		this.textDpto.setText("");
		this.textLocalidad.setText("");
	}

	public void clearDataFichaTecnicaVehiculo() {
		this.textNroDeChasis.setText("");
		this.textNroMotor.setText("");
		this.textMarca.setText("");
		this.textColor.setText("");
		this.textCombustion.setText("");
		this.textModelo.setText("");
		this.textTipoDeOT.setText("");
	}

	public void clearDataListadoVehiculosCliente() {
		this.vehiculos = null;
		this.tableModelVehiculos.setRowCount(0);
		tableModelVehiculos.setColumnCount(0);
		tableModelVehiculos.setColumnIdentifiers(columnasTablaVehiculos);
	}

	public void clearDataOrdenDeTrabajo() {
		this.textFechaAltaOt.setText("");
		this.textFechaCierreOt.setText("");
		this.textTrabajoSugeridoOt.setText("");
		this.textTrabajoSolicitadoOt.setText("");
	}

	public void setData(ClienteDTO cliente) {
		this.idDatosPersonalesDelCliente = cliente.getDatosPersonalesDTO().getId();
		this.idCliente = cliente.getIdCliente();
		this.textNombre.setText(cliente.getDatosPersonalesDTO().getNombreCompleto());
		this.textApellido.setText(cliente.getDatosPersonalesDTO().getApellido());
		this.textDni.setText(cliente.getDatosPersonalesDTO().getDni().toString());
		this.textTelefono.setText(cliente.getDatosPersonalesDTO().getTelefono());
		this.textEmail.setText(cliente.getDatosPersonalesDTO().getEmail());
		this.textCalle.setText(cliente.getDatosPersonalesDTO().getCalle());
		this.textAltura.setText(cliente.getDatosPersonalesDTO().getAltura().toString());
		this.textPiso.setText(cliente.getDatosPersonalesDTO().getPiso().toString());
		this.textDpto.setText(cliente.getDatosPersonalesDTO().getDpto());
		this.textLocalidad.setText(cliente.getDatosPersonalesDTO().getLocalidad());
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

	public void setData(OrdenDeTrabajoDTO ordenDeTrabajo) {
		this.textFechaAltaOt.setText(ordenDeTrabajo.getFechaDeAlta().toString());
		this.textFechaCierreOt.setText(
				ordenDeTrabajo.getFechaEntregado() != null ? ordenDeTrabajo.getFechaEntregado().toString() : "");
		this.textTrabajoSugeridoOt.setText(ordenDeTrabajo.getTrabajoSujerido());
		this.textTrabajoSolicitadoOt.setText(ordenDeTrabajo.getTrabajoSolicitado());
		this.textTipoDeOT.setText(ordenDeTrabajo.getTipoOrdeTrabajo());
	}

	public void setData(List<VehiculoConOrdenDeTrabajoDTO> vehiculos) {
		this.vehiculos = vehiculos;
		for (VehiculoConOrdenDeTrabajoDTO dto : vehiculos) {
			Object[] row = { dto.getId(), dto.getKilometrajeGarantia(),// dto.getKilometrajeGarantia().toString()
					dto.getAseguradora(),
					dto.getNroPolizaSeguro(), //dto.getNroPolizaSeguro().toString(),
					dto.getPatente() };
			this.tableModelVehiculos.addRow(row);
		}
	}

	public void setActionBuscar(ActionListener listener) {
		this.btnBuscar.addActionListener(listener);
	}

	public VehiculoConOrdenDeTrabajoDTO getidVehiculoSeleccionado() {
		int rows = this.table.getSelectedRowCount();
		if (rows == 1) {
			int row = this.table.getSelectedRow();
			VehiculoConOrdenDeTrabajoDTO dto = this.vehiculos.get(row);
			return dto;
		}
		return null;
	}

	public String dniClienteSeleccionado() {
		return this.textDni.getText();
	}
	
	public Integer getIdCliente() {
		return this.idCliente;
	}

	public Integer getIdDatosPersonalesCliente() {
		return this.idDatosPersonalesDelCliente;
	}

	public void setActionSelectVehiculoCliente(ListSelectionListener listener) {
		this.table.getSelectionModel().addListSelectionListener(listener);
	}

	public String getDniCliente() {
		return txtDNI.getText();
	}

	public void setActionRegistrarCliente(ActionListener listener) {
		this.btnRegistrarNuevoCliente.addActionListener(listener);
	}

	public void setActionRegistrarVehiculo(ActionListener listener) {
		this.btnRegistrarNuevoVehiculo.addActionListener(listener);
	}

	public void setActionRegistrarOrdenDeTrabajo(ActionListener listener) {
		this.btnRegistrarOrdenDeTrabajo.addActionListener(listener);
	}

	public void setActionOnEditarCliente(ActionListener listener) {
		this.btnEditarCliente.addActionListener(listener);
	}

	public void clearAll() {
		this.clearDataCliente();
		this.clearDataFichaTecnicaVehiculo();
		this.clearDataListadoVehiculosCliente();
		this.clearDataOrdenDeTrabajo();
		this.clearDataGarantia();
	}

	public void lockButtonRegistrarOrdenDeTrabajo() {
		this.btnRegistrarOrdenDeTrabajo.setEnabled(false);
	}

	public void unlockButtonRegistrarOrdenDeTrabajo() {
		this.btnRegistrarOrdenDeTrabajo.setEnabled(true);
	}
	
	public void setDataGarantia(GarantiaVehiculoDTO garantia) {
		this.garantia.setData(garantia);
	}
	
	public void clearDataGarantia() {
		this.garantia.clearData();
	}
}