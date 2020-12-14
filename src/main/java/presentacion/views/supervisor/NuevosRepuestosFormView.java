package presentacion.views.supervisor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import dto.taller.RepuestoDTO;

import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class NuevosRepuestosFormView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tablaRepuestos;
	private DefaultTableModel modelo;
	private static final String[] nombreColumnas = { "Codigo", "Descripcion", "Marca", "Fabricante", "Stock", "Precio" , "quitar"};//columna checkbox editable
	private JButton okButton;
	private JButton cancelButton;
	private static NuevosRepuestosFormView instance;

	public static NuevosRepuestosFormView getInstance() {
		if (instance == null)
			instance = new NuevosRepuestosFormView();
		return instance;
	}
	
	
	public NuevosRepuestosFormView() {
		setTitle("Nuevos repuestos");
		setBounds(100, 100, 800, 551);
		setModal(true);//para hacer que la ventana se tenga que cerrar si o si
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//deshabilite el boton cerrar
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			modelo = new DefaultTableModel(null,nombreColumnas) {
				public boolean isCellEditable(int row, int column) {
					return column == 6;
				}
				
				@SuppressWarnings({ "unchecked", "rawtypes" })
				@Override
			    public Class getColumnClass(int col) {
					if(col == 6) return Boolean.class;
					else return Object.class;
				}
			};
			
			tablaRepuestos = new JTable(modelo);			
			JScrollPane scrollPane = new JScrollPane(tablaRepuestos);
			contentPanel.add(scrollPane, BorderLayout.CENTER);}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Cargar repuestos");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);}
			{
				cancelButton = new JButton("Cancelar carga");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void cargarTabla(List<RepuestoDTO> repuestos) {
		for (RepuestoDTO repuesto : repuestos) {
			Object[] row = {
			repuesto.getCodigoRepuesto().toString(),
			repuesto.getDescripcionRepuesto(),
			repuesto.getMarcaRepuesto(),
			repuesto.getFabricante(),
			repuesto.getStockRepuesto(),
			repuesto.getPrecioCompra()};
			modelo.addRow(row);
		}
		TableColumn tc = tablaRepuestos.getColumnModel().getColumn(6);
		tc.setCellEditor(tablaRepuestos.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(tablaRepuestos.getDefaultRenderer(Boolean.class));
	}
	
	public void mostrar () {
		this.setVisible(true);
	}
	
	public void cerrar () {
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
	
	public List<Integer> getIdRepuestosNoAceptados() {
		List<Integer> repuestosId = new LinkedList<Integer>();
		int rows = this.modelo.getRowCount();		
		Integer repuestoId;		
		for (int index = 0; index < rows; index++) {
			repuestoId = Integer.parseInt(modelo.getValueAt(index, 0).toString());
			Boolean isOk = (Boolean) modelo.getValueAt(index, 6);
			if(isOk!=null && isOk!=false) {//igual true quitar
				repuestosId.add(repuestoId);
			}
		}
		return repuestosId;
	}	
}
