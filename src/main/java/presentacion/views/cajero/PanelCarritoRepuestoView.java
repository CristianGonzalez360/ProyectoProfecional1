package presentacion.views.cajero;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import dto.ClienteDTO;
import dto.RepuestoCompradoDTO;
import dto.RepuestoDTO;
import dto.RepuestoPlanificadoDTO;
import presentacion.views.supervisor.ClientePanelView;

import javax.swing.JSplitPane;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;

public class PanelCarritoRepuestoView extends JPanel {

	private static final long serialVersionUID = -3149040258338164711L;

	private final JPanel panelDerecho = new JPanel();

	private static final String[] nombreColumnasSuperior = { "Codigo", "Descripcion", "Marca", "Fabricante", "Stock",
			"Precio" };
	private static final String[] nombreColumnasInferior = { "Codigo", "Descripcion", "Marca", "Fabricante",
			"Cantidad" };

	private JPanel panelSuperior = new JPanel();
	private JPanel panelInterior = new JPanel();
	private JComboBox<String> comboMarcas;
	private JLabel lblDescripcion;
	private JTextField textDescipcion;
	private JButton btnBuscar;
	private JScrollPane scrollPaneRepuestos;
	private DefaultTableModel modelRepuestos;
	private JTable tablaRepuestos;

	private JPanel panelInferior = new JPanel();
	private JScrollPane scrollPaneRepuestosInferior;
	private DefaultTableModel modelRepuestosInferior;
	private JTable tablaRepuestosInferior;

	static PanelCarritoRepuestoView vista;

	private JButton btnLimpiar;
	private JButton btnQuitar;
	private JButton btnEditar;
	private JButton btnAgregar;
	private JLabel lblCantidad;
	private JTextField textCantidad;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private List<Integer> idRepuestos;
	private JButton btnCrearFactura;
	private JButton btnCancelar;
	private ClientePanelView panelCliente;
	private JSplitPane splitPane;
	private JPanel panelBotonesCliente;
	private JButton btnRegistrarCliente;
	private JPanel panelBuscador;
	private JTextField tfDni;
	private JButton btnBuscarCliente;

	private List<Integer> idRepuestosComprados;
	private JLabel lblMarca;
	private JTextField tfTotalFactura;
	private JLabel lblTotalFactura;

	public static PanelCarritoRepuestoView getInstance() {
		if (vista == null)
			vista = new PanelCarritoRepuestoView();
		return vista;
	}

	@SuppressWarnings("serial")
	private PanelCarritoRepuestoView() {
		setBounds(100, 100, 712, 600);
		setLayout(new BorderLayout());
		idRepuestos = new ArrayList<>();
		idRepuestosComprados = new ArrayList<>();
		
		modelRepuestos = new DefaultTableModel(null, nombreColumnasSuperior) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		modelRepuestosInferior = new DefaultTableModel(null, nombreColumnasInferior) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(buttonPane, BorderLayout.SOUTH);

		btnCrearFactura = new JButton("Aceptar");
		btnCrearFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		lblTotalFactura = new JLabel("Total factura: ");
		buttonPane.add(lblTotalFactura);
		
		tfTotalFactura = new JTextField();
		tfTotalFactura.setEditable(false);
		buttonPane.add(tfTotalFactura);
		tfTotalFactura.setColumns(10);
		btnCrearFactura.setActionCommand("OK");
		buttonPane.add(btnCrearFactura);
//		tfTotalFactura.setText(arg0);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setVisible(false);
		btnCancelar.setActionCommand("Cancel");
		buttonPane.add(btnCancelar);

		splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setResizeWeight(0.0);
		add(splitPane, BorderLayout.CENTER);
		splitPane.setRightComponent(panelDerecho);
		
		JPanel panelIzquierdo = new JPanel(new BorderLayout());
		
		panelCliente = new ClientePanelView();
		panelIzquierdo.add(panelCliente, BorderLayout.CENTER);
		
		
		splitPane.setLeftComponent(panelIzquierdo);
		
		panelBotonesCliente = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBotonesCliente.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelIzquierdo.add(panelBotonesCliente, BorderLayout.SOUTH);
		
		btnRegistrarCliente = new JButton("Registrar Cliente");
		panelBotonesCliente.add(btnRegistrarCliente);
		
		panelBuscador = new JPanel();
		panelIzquierdo.add(panelBuscador, BorderLayout.NORTH);
		
		tfDni = new JTextField();
		panelBuscador.add(tfDni);
		tfDni.setColumns(15);
		
		btnBuscarCliente = new JButton("Buscar");
		panelBuscador.add(btnBuscarCliente);
		
		panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));

		panelSuperior = new JPanel();
		panelSuperior.setForeground(SystemColor.menu);
		panelSuperior.setBorder(
				new TitledBorder(null, "Repuestos disponibles", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelSuperior.setBackground(SystemColor.menu);
		panelDerecho.add(panelSuperior);
		panelSuperior.setLayout(new BorderLayout(0, 0));

		panel_1 = new JPanel();
		panelSuperior.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("10px"),
				ColumnSpec.decode("max(34dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("132px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("right:75px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("78px:grow"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("85px"),},
			new RowSpec[] {
				FormSpecs.LINE_GAP_ROWSPEC,
				RowSpec.decode("23px"),}));
		
		lblMarca = new JLabel("Marca");
		panel_1.add(lblMarca, "2, 2, right, default");

		comboMarcas = new JComboBox<>();
		panel_1.add(comboMarcas, "4, 2, default, center");

		lblDescripcion = new JLabel();
		panel_1.add(lblDescripcion, "6, 2, right, center");
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setText("Descripción");

		textDescipcion = new JTextField();
		panel_1.add(textDescipcion, "8, 2, default, center");
		textDescipcion.setColumns(10);

		btnBuscar = new JButton("Buscar");
		panel_1.add(btnBuscar, "10, 2, fill, top");

		panelInterior = new JPanel();
		panelInterior.setBackground(SystemColor.menu);
		panelSuperior.add(panelInterior, BorderLayout.CENTER);
		panelInterior.setLayout(new BorderLayout(0, 0));
		tablaRepuestos = new JTable(modelRepuestos);
		scrollPaneRepuestos = new JScrollPane(tablaRepuestos);
		panelInterior.add(scrollPaneRepuestos, BorderLayout.CENTER);

		panel_2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelSuperior.add(panel_2, BorderLayout.SOUTH);

		lblCantidad = new JLabel("Cantidad");
		panel_2.add(lblCantidad);
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);

		textCantidad = new JTextField();
		panel_2.add(textCantidad);
		textCantidad.setColumns(10);

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_2.add(btnAgregar);

		panelInferior = new JPanel();
		panelInferior.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Carrito",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelInferior.setBackground(SystemColor.menu);
		panelDerecho.add(panelInferior);
		panelInferior.setLayout(new BorderLayout(0, 0));

		scrollPaneRepuestosInferior = new JScrollPane();
		panelInferior.add(scrollPaneRepuestosInferior, BorderLayout.CENTER);

		tablaRepuestosInferior = new JTable(modelRepuestosInferior);

		scrollPaneRepuestosInferior.setViewportView(tablaRepuestosInferior);

		panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelInferior.add(panel, BorderLayout.SOUTH);

		btnEditar = new JButton("Editar");
		btnEditar.setVisible(false);
		panel.add(btnEditar);

		btnQuitar = new JButton("Quitar");
		panel.add(btnQuitar);

		btnLimpiar = new JButton("Limpiar");
		panel.add(btnLimpiar);
		btnLimpiar.setVisible(false);
	}

	public void clearDataRepuestos() {
		modelRepuestos.setRowCount(0);
	}

	public void clearDataRepuestosPlanificados() {
		modelRepuestosInferior.setRowCount(0);
	}

	public void display() {
		setVisible(true);
	}

	public void setDataRepuestosComprados(List<RepuestoCompradoDTO> repuestos) {
		modelRepuestosInferior.setRowCount(0);
		for (RepuestoCompradoDTO r : repuestos) {
			idRepuestosComprados.add(r.getIdRepuestoComprado());
			Object[] row = { r.getRepuesto().getCodigoRepuesto(), r.getRepuesto().getDescripcionRepuesto(),
					r.getRepuesto().getMarcaRepuesto(), r.getRepuesto().getFabricante(), r.getCantidad() };
			modelRepuestosInferior.addRow(row);
		}
	}

	public void setDataRepuestos(List<RepuestoDTO> repuestos) {
		modelRepuestos.setRowCount(0);
		idRepuestos.clear();
		for (RepuestoDTO r : repuestos) {
			idRepuestos.add(r.getIdRepuesto());
			Object[] row = { r.getCodigoRepuesto(), r.getDescripcionRepuesto(), r.getMarcaRepuesto(), r.getFabricante(),
					r.getStockRepuesto(), r.getPrecioRepuesto() };
			modelRepuestos.addRow(row);
		}
	}

	public String getIdRepuesto() {
		String ret = -1 + "";
		if (tablaRepuestos.getSelectedRow() >= 0) {
			ret = "" + idRepuestos.get(tablaRepuestos.getSelectedRow());
		}
		return ret;
	}

	public String getCantidad() {
		return textCantidad.getText();
	}

	public void setActionOnAgregar(ActionListener listener) {
		this.btnAgregar.addActionListener(listener);
	}

	public void setActionOnCancelar(ActionListener listener) {
		this.btnCancelar.addActionListener(listener);
	}

	public void setActionOnAceptar(ActionListener listener) {
		this.btnCrearFactura.addActionListener(listener);
	}

	public void close() {
		setVisible(false);
	}

	public void setActionOnBuscar(ActionListener listener) {
		this.btnBuscar.addActionListener(listener);
	}
	
	public void setActionOnBuscarCliente(ActionListener listener) {
		this.btnBuscarCliente.addActionListener(listener);
	}

	public void setActionOnAgregarCliente(ActionListener listener) {
		this.btnRegistrarCliente.addActionListener(listener);
	}

	public String getMarca() {
		return (String) comboMarcas.getSelectedItem();
	}

	public String getDescripcion() {
		return textDescipcion.getText();
	}

	public void setDataMarcas(List<String> marcas) {
		DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
		for (String marca : marcas) {
			modelo.addElement(marca);
		}
		comboMarcas.setModel(modelo);
	}

	public void setActionOnQuitar(ActionListener Listener) {
		this.btnQuitar.addActionListener(Listener);
	}

	public Integer getSeleccionado() {
		return tablaRepuestosInferior.getSelectedRow();
	}

	public void clearClienteData() {
		panelCliente.clearData();
	}

	public String getDniCliente() {
		return this.tfDni.getText();
	}

	public void setDataCliente(ClienteDTO cliente) {
		this.panelCliente.setData(cliente);
	}
	
	public void setData(List<RepuestoCompradoDTO> repuestos) {
		modelRepuestosInferior.setRowCount(0);
		idRepuestos.clear();
		for (RepuestoCompradoDTO r : repuestos) {
			idRepuestos.add(r.getIdRepuestoComprado());
			Object[] row = { r.getRepuesto().getCodigoRepuesto(), r.getRepuesto().getDescripcionRepuesto(), r.getRepuesto().getMarcaRepuesto(), r.getRepuesto().getFabricante(),
					r.getRepuesto().getStockRepuesto(), r.getRepuesto().getStockMinimo(), r.getRepuesto().getPrecioRepuesto() };
			modelRepuestosInferior.addRow(row);
		}
	}

	public JLabel getLblTotalFactura() {
		return lblTotalFactura;
	}

	public void setLblTotalFactura(JLabel lblTotalFactura) {
		this.lblTotalFactura = lblTotalFactura;
	}

	public JTextField getTfTotalFactura() {
		return tfTotalFactura;
	}

	public void setTfTotalFactura(JTextField tfTotalFactura) {
		this.tfTotalFactura = tfTotalFactura;
	}

	public void setActionOnCrearFactura(ActionListener listener) {
		this.btnCrearFactura.addActionListener(listener);
		//poner algun modal de confirmacion issue32s
	}
	
	public void clear() {
	this.modelRepuestosInferior.setRowCount(0);
	this.panelCliente.clearData();
	this.tfTotalFactura.setText("0.0");
	
	}
}
	
	
