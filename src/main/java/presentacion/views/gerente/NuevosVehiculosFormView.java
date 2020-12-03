package presentacion.views.gerente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import dto.VehiculoDTO;

@SuppressWarnings("serial")
public class NuevosVehiculosFormView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tablaVehiculos;
	private DefaultTableModel modelo;
	private static final String[] nombreColumnas = { "Marca", "Familia", "Linea", "Color", "Precio", "Quitar" };// columna
																												// checkbox
																												// editable
	private JButton okButton;
	private JButton cancelButton;
	private static NuevosVehiculosFormView instance;

	public static NuevosVehiculosFormView getInstance() {
		if (instance == null)
			instance = new NuevosVehiculosFormView();
		return instance;
	}

	public NuevosVehiculosFormView() {
		setTitle("Nuevos Vehiculos");
		setBounds(100, 100, 800, 551);
		setModal(true);// para hacer que la ventana se tenga que cerrar si o si
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// deshabilite el boton cerrar
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			modelo = new DefaultTableModel(null, nombreColumnas) {
				public boolean isCellEditable(int row, int column) {
					return column == 5;
				}

				@SuppressWarnings({ "unchecked", "rawtypes" })
				@Override
				public Class getColumnClass(int col) {
					if (col == 5)
						return Boolean.class;
					else
						return Object.class;
				}
			};

			tablaVehiculos = new JTable(modelo);
			JScrollPane scrollPane = new JScrollPane(tablaVehiculos);
			contentPanel.add(scrollPane, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Cargar vehiculos");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void cargarTabla(List<VehiculoDTO> vehiculos) {
		for (VehiculoDTO vehiculo : vehiculos) {
			Object[] row = { vehiculo.getMarca(), vehiculo.getFamilia(), vehiculo.getLinea(), vehiculo.getColor(),
					vehiculo.getPrecioVenta() };
			modelo.addRow(row);
		}
		TableColumn tc = tablaVehiculos.getColumnModel().getColumn(5);
		tc.setCellEditor(tablaVehiculos.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(tablaVehiculos.getDefaultRenderer(Boolean.class));
	}

	public void mostrar() {
		this.setVisible(true);
	}

	public void cerrar() {
		this.setVisible(false);
	}

	public void clear() {
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
		modelo.setColumnIdentifiers(nombreColumnas);
	}

	public void setActionOnValidadCarga(ActionListener listener) {
		this.okButton.addActionListener(listener);
	}

	public void setActionOnCancelarCarga(ActionListener listener) {
		this.cancelButton.addActionListener(listener);
	}

	public List<Integer> getIdVehiculosNoAceptados() {
		List<Integer> vehiculosId = new LinkedList<Integer>();
		int rows = this.modelo.getRowCount();
		for (int index = 0; index < rows; index++) {
			Boolean isOk = (Boolean) modelo.getValueAt(index, 5);
			if (isOk != null && isOk != false) {// igual true quitar
				vehiculosId.add(index);
			}
		}
		return vehiculosId;
	}
}
