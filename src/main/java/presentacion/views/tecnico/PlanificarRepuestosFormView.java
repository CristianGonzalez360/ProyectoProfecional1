package presentacion.views.tecnico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import dto.RepuestoDTO;
import dto.RepuestoPlanificadoDTO;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.BevelBorder;

public class PlanificarRepuestosFormView extends JPanel {

	private static final long serialVersionUID = -3149040258338164711L;

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

	static PlanificarRepuestosFormView vista;

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
	private JLabel lblMarca;

	public static PlanificarRepuestosFormView getInstance() {
		if (vista == null)
			vista = new PlanificarRepuestosFormView();
		return vista;
	}

	@SuppressWarnings("serial")
	private PlanificarRepuestosFormView() {
		setBounds(100, 100, 500, 600);

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		idRepuestos = new ArrayList<>();

		panelSuperior = new JPanel();
		panelSuperior.setForeground(SystemColor.menu);
		panelSuperior.setBorder(
				new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Repuestos disponibles", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSuperior.setBackground(SystemColor.menu);
		add(panelSuperior);
		panelSuperior.setLayout(new BorderLayout(0, 0));

		modelRepuestos = new DefaultTableModel(null, nombreColumnasSuperior) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		panel_1 = new JPanel();
		panelSuperior.add(panel_1, BorderLayout.NORTH);
		FormLayout fl_panel_1 = new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("10px"),
				ColumnSpec.decode("max(30dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("132px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("right:75px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("86px:grow"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("81px"),},
			new RowSpec[] {
				FormSpecs.LINE_GAP_ROWSPEC,
				RowSpec.decode("fill:default:grow"),});
		panel_1.setLayout(fl_panel_1);
		
		lblMarca = new JLabel("Marca:");
		panel_1.add(lblMarca, "2, 2, right, default");

		comboMarcas = new JComboBox<>();
		panel_1.add(comboMarcas, "4, 2, fill, center");

		lblDescripcion = new JLabel();
		panel_1.add(lblDescripcion, "6, 2, right, center");
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setText("Descripción");

		textDescipcion = new JTextField();
		panel_1.add(textDescipcion, "8, 2, fill, center");
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
		panel_2.add(btnAgregar);

		panelInferior = new JPanel();
		panelInferior.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Repuestos planificados", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelInferior.setBackground(SystemColor.menu);
		add(panelInferior);
		panelInferior.setLayout(new BorderLayout(0, 0));

		scrollPaneRepuestosInferior = new JScrollPane();
		panelInferior.add(scrollPaneRepuestosInferior, BorderLayout.CENTER);

		modelRepuestosInferior = new DefaultTableModel(null, nombreColumnasInferior) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tablaRepuestosInferior = new JTable(modelRepuestosInferior);

		scrollPaneRepuestosInferior.setViewportView(tablaRepuestosInferior);

		panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelInferior.add(panel, BorderLayout.SOUTH);

		btnEditar = new JButton("Editar");
		panel.add(btnEditar);

		btnQuitar = new JButton("Quitar");
		panel.add(btnQuitar);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setVisible(false);
		panel.add(btnLimpiar);
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

	public void setDataRepuestosPlanificados(List<RepuestoPlanificadoDTO> repuestos) {
		modelRepuestosInferior.setRowCount(0);
		for (RepuestoPlanificadoDTO r : repuestos) {
			idRepuestos.add(r.getIdRepuestoPlanificado());
			Object[] row = { r.getRepuesto().getCodigoRepuesto(), r.getRepuesto().getDescripcionRepuesto(),
					r.getRepuesto().getMarcaRepuesto(), r.getRepuesto().getFabricante(), r.getCantRequerida() };
			modelRepuestosInferior.addRow(row);
		}
	}

	public void setDataRepuestos(List<RepuestoDTO> repuestos) {
		textCantidad.setText("");
		modelRepuestos.setRowCount(0);
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

	public void close() {
		setVisible(false);
	}

	public void setActionOnBuscar(ActionListener listener) {
		this.btnBuscar.addActionListener(listener);
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
}
