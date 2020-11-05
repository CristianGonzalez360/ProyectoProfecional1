package presentacion.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.BoxLayout;

public class PlanificarRepuestosFormView extends JDialog {

	private static final long serialVersionUID = -3149040258338164711L;

	private final JPanel contentPanel = new JPanel();

	private static final String[] nombreColumnasSuperior = { "Codigo", "Descripcion", "Marca", "Fabricante", "Stock",
			"Precio" };
	private static final String[] nombreColumnasInferior = { "Codigo", "Descripcion", "Marca", "Fabricante", "Stock",
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

	public static PlanificarRepuestosFormView getInstance() {
		if (vista == null)
			vista = new PlanificarRepuestosFormView();
		return vista;
	}

	@SuppressWarnings("serial")
	private PlanificarRepuestosFormView() {
		setBounds(100, 100, 500, 600);
		setTitle("Planificacion de repuestos");
		getContentPane().setLayout(new BorderLayout());

		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

		panelSuperior = new JPanel();
		panelSuperior.setForeground(SystemColor.menu);
		panelSuperior.setBorder(
				new TitledBorder(null, "Repuestos disponibles", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelSuperior.setBackground(SystemColor.menu);
		contentPanel.add(panelSuperior);
		panelSuperior.setLayout(new BorderLayout(0, 0));

		modelRepuestos = new DefaultTableModel(null, nombreColumnasSuperior) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		panel_1 = new JPanel();
		panelSuperior.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FormLayout(
				new ColumnSpec[] { ColumnSpec.decode("10px"), ColumnSpec.decode("132px"),
						FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("right:75px"),
						FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("86px:grow"),
						FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("65px"), },
				new RowSpec[] { FormSpecs.LINE_GAP_ROWSPEC, RowSpec.decode("23px"), }));

		comboMarcas = new JComboBox<>();
		panel_1.add(comboMarcas, "2, 2, default, center");

		lblDescripcion = new JLabel();
		panel_1.add(lblDescripcion, "4, 2, right, center");
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setText("Descripción");

		textDescipcion = new JTextField();
		panel_1.add(textDescipcion, "6, 2, default, center");
		textDescipcion.setColumns(10);

		btnBuscar = new JButton("Buscar");
		panel_1.add(btnBuscar, "8, 2, left, top");

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
		panelInferior.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Repuestos planificados",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelInferior.setBackground(SystemColor.menu);
		contentPanel.add(panelInferior);
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
		panel.add(btnLimpiar);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		setVisible(false);
	}

	public void clearData() {
		// TODO Auto-generated method stub
		
	}

	public void display() {
		setVisible(true);
	}

}
