package presentacion.views.supervisor;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dto.taller.RepuestoDTO;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;

public class PanelGestionRepuestos extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTextField txtDescripcion;
	private JTable tablaRepuestos;
	private DefaultTableModel modelo;
	private ArrayList<Integer> idRepuestos;

	private static PanelGestionRepuestos instance;

	private static final String[] nombreColumnas = { "Codigo", "Descripcion", "Marca", "Fabricante", "Stock", "Minimo",
			"Precio Venta", "Precio Compra", "Garantia" };
	private JComboBox<String> comboMarcas;
	private JButton btnBuscar;
	private JButton btnIngresarStock;
	private JButton btnCargarArchivo;
	private JSeparator separator;
	private JLabel lblMarca;
	private JButton btnEditar;
	private JSeparator separator_2;
	private JButton btnBajoStock;

	private PanelGestionRepuestos() {
		setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));

		this.idRepuestos = new ArrayList<>();

		JPanel panelBuscador = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBuscador.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panelBuscador, BorderLayout.NORTH);

		lblMarca = new JLabel("Marca:");
		panelBuscador.add(lblMarca);

		comboMarcas = new JComboBox<>();
		panelBuscador.add(comboMarcas);

		JLabel lblDescripcion = new JLabel("Descripcion:");
		panelBuscador.add(lblDescripcion);

		txtDescripcion = new JTextField();
		panelBuscador.add(txtDescripcion);
		txtDescripcion.setColumns(10);

		btnBuscar = new JButton("Buscar");
		panelBuscador.add(btnBuscar);

		btnBajoStock = new JButton("Bajo Stock");
		panelBuscador.add(btnBajoStock);

		modelo = new DefaultTableModel();
		modelo.setColumnIdentifiers(nombreColumnas);
		tablaRepuestos = new JTable(modelo) {
			private static final long serialVersionUID = -7788564446939732701L;

			@Override
			public Class<?> getColumnClass(int column) {
				return column == 8 ? Boolean.class : super.getColumnClass(column);
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablaRepuestos.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(tablaRepuestos);
		add(scrollPane, BorderLayout.CENTER);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);

		JPanel panel = new JPanel();
		FlowLayout fl_panel = new FlowLayout(FlowLayout.LEFT, 5, 5);
		fl_panel.setAlignOnBaseline(true);
		panel.setLayout(fl_panel);
		panel.add(toolBar);
		add(panel, BorderLayout.SOUTH);

		btnIngresarStock = new JButton("Agregar Stock");
		toolBar.add(btnIngresarStock);

		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator);

		btnEditar = new JButton("Editar Repuesto");
		toolBar.add(btnEditar);

		separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_2);

		btnCargarArchivo = new JButton("Cargar Archivo");
		toolBar.add(btnCargarArchivo);
	}

	public static PanelGestionRepuestos getInstance() {
		if (instance == null) {
			instance = new PanelGestionRepuestos();
		}
		return instance;
	}

	public void setData(List<RepuestoDTO> repuestos) {
		modelo.setRowCount(0);
		idRepuestos.clear();
		for (RepuestoDTO r : repuestos) {
			idRepuestos.add(r.getIdRepuesto());
			Object[] row = { r.getCodigoRepuesto(), r.getDescripcionRepuesto(), r.getMarcaRepuesto(), r.getFabricante(),
					r.getStockRepuesto(), r.getStockMinimo(), r.getPrecioRepuesto(), r.getPrecioCompra(),
					r.isGarantia() };
			modelo.addRow(row);
		}
	}

	public String getMarca() {
		return (String) comboMarcas.getSelectedItem();
	}

	public String getDescripcion() {
		return txtDescripcion.getText();
	}

	public void setDataMarcas(List<String> marcas) {
		DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
		for (String marca : marcas) {
			modelo.addElement(marca);
		}
		comboMarcas.setModel(modelo);
	}

	public int getIdRepuesto() {
		int ret = -1;
		if (tablaRepuestos.getSelectedRow() >= 0) {
			ret = idRepuestos.get(tablaRepuestos.getSelectedRow());
		}
		return ret;
	}

	public void setActionOnBuscar(ActionListener listener) {
		this.btnBuscar.addActionListener(listener);
	}

	public void setActionOnIngresarStock(ActionListener listener) {
		this.btnIngresarStock.addActionListener(listener);
	}

	public void setActionOnCargarArchivo(ActionListener listener) {
		this.btnCargarArchivo.addActionListener(listener);
	}

	public void setActionOnEditarStock(ActionListener listener) {
		this.btnEditar.addActionListener(listener);
	}

	public void resetBuscador() {
		this.comboMarcas.setSelectedIndex(0);
		this.txtDescripcion.setText("");
	}

	public void setActionBajoStock(ActionListener listener) {
		this.btnBajoStock.addActionListener(listener);
	}
}
