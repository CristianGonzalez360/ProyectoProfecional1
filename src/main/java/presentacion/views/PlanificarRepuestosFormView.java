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

	public static PlanificarRepuestosFormView getInstance() {
		if (vista == null)
			vista = new PlanificarRepuestosFormView();
		return vista;
	}

	@SuppressWarnings("serial")
	public PlanificarRepuestosFormView() {
		setBounds(100, 100, 500, 600);
		setTitle("Planificacion de repuestos");
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);

		contentPanel.setLayout(null);

		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			panelSuperior = new JPanel();
			panelSuperior.setForeground(SystemColor.menu);
			panelSuperior.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelSuperior.setBackground(SystemColor.menu);
			panelSuperior.setBounds(10, 11, 474, 277);
			panelSuperior.setLayout(null);
			contentPanel.add(panelSuperior);
			{
				panelInterior = new JPanel();
				panelInterior.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
				panelInterior.setBackground(SystemColor.menu);
				panelInterior.setBounds(10, 11, 454, 39);
				panelInterior.setLayout(null);
				panelSuperior.add(panelInterior);
				{
					comboMarcas = new JComboBox<>();
					comboMarcas.setBounds(0, 11, 124, 20);
					panelInterior.add(comboMarcas);
				}
				{
					lblDescripcion = new JLabel();
					lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
					lblDescripcion.setText("Descripción");
					lblDescripcion.setBounds(134, 13, 80, 17);
					panelInterior.add(lblDescripcion);
				}
				{
					textDescipcion = new JTextField();
					textDescipcion.setBounds(224, 11, 130, 20);
					panelInterior.add(textDescipcion);
					textDescipcion.setColumns(10);
				}
				{
					btnBuscar = new JButton("Buscar");
					btnBuscar.setBounds(364, 11, 90, 20);
					panelInterior.add(btnBuscar);
				}
			}
			{
				scrollPaneRepuestos = new JScrollPane();
				scrollPaneRepuestos.setBounds(10, 62, 454, 168);
				panelSuperior.add(scrollPaneRepuestos);

				modelRepuestos = new DefaultTableModel(null, nombreColumnasSuperior) {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				tablaRepuestos = new JTable(modelRepuestos);

				scrollPaneRepuestos.setViewportView(tablaRepuestos);
			}
			{
				btnAgregar = new JButton("Agregar");
				btnAgregar.setBounds(366, 241, 98, 25);
				panelSuperior.add(btnAgregar);
			}
			{
				lblCantidad = new JLabel("Cantidad");
				lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
				lblCantidad.setBounds(142, 245, 92, 19);
				panelSuperior.add(lblCantidad);
			}
			{
				textCantidad = new JTextField();
				textCantidad.setBounds(244, 242, 105, 24);
				panelSuperior.add(textCantidad);
				textCantidad.setColumns(10);
			}

			panelInferior = new JPanel();
			panelInferior.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Listado de Repuestos",
					TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelInferior.setBackground(SystemColor.menu);
			panelInferior.setBounds(10, 299, 474, 228);
			panelInferior.setLayout(null);
			contentPanel.add(panelInferior);
			{
				scrollPaneRepuestosInferior = new JScrollPane();
				scrollPaneRepuestosInferior.setBounds(10, 24, 454, 158);
				panelInferior.add(scrollPaneRepuestosInferior);

				modelRepuestosInferior = new DefaultTableModel(null, nombreColumnasInferior) {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};

				tablaRepuestosInferior = new JTable(modelRepuestosInferior);

				scrollPaneRepuestosInferior.setViewportView(tablaRepuestosInferior);
			}
			{
				btnLimpiar = new JButton("Limpiar");
				btnLimpiar.setBounds(51, 194, 89, 23);
				panelInferior.add(btnLimpiar);
			}
			{
				btnQuitar = new JButton("Quitar");
				btnQuitar.setBounds(191, 194, 89, 23);
				panelInferior.add(btnQuitar);
			}
			{
				btnEditar = new JButton("Editar");
				btnEditar.setBounds(331, 194, 89, 23);
				panelInferior.add(btnEditar);
			}

			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

	}

}
