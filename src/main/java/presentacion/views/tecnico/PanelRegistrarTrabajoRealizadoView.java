
package presentacion.views.tecnico;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dto.EstadoPresupuesto;
import dto.PresupuestoDTO;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.SwingConstants;

public class PanelRegistrarTrabajoRealizadoView extends JPanel {

	private static final long serialVersionUID = -5623725856065117794L;

	private static PanelRegistrarTrabajoRealizadoView instance;
	private final String[] columnasListadoDePresupuestos = new String[] { "NRO. Presupuesto", "FECHA ALTA",
			"COMENTARIO ALTA", "ESTADO",};
	private DefaultTableModel listadoDePresupuestosModel;
	private JPanel panel;
	private JScrollPane scrollPaneRepuestos;
	private JTable tablePresupuestos;
	private JPanel panel_1;
	private JButton btnRegistrarTrabajoRealizado;
	private JPanel panel_3;
	private JButton btnCargarPresupuestos;

	public static PanelRegistrarTrabajoRealizadoView getInstance() {
		if (instance == null) {
			instance = new PanelRegistrarTrabajoRealizadoView();
		}
		return instance;
	}

	public PanelRegistrarTrabajoRealizadoView() {
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Listado de presupuestos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		this.listadoDePresupuestosModel = new DefaultTableModel(null, this.columnasListadoDePresupuestos); 
		scrollPaneRepuestos = new JScrollPane();
		panel.add(scrollPaneRepuestos);
		tablePresupuestos = new JTable(listadoDePresupuestosModel){//tabla no editable
			
			private static final long serialVersionUID = -3765331683649186159L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};;
		scrollPaneRepuestos.setViewportView(tablePresupuestos);
		
		panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(15);
		panel.add(panel_1, BorderLayout.SOUTH);
		
		btnRegistrarTrabajoRealizado = new JButton("Registrar trabajo");
		btnRegistrarTrabajoRealizado.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(btnRegistrarTrabajoRealizado);
		btnRegistrarTrabajoRealizado.setEnabled(false);
		
		panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setVgap(15);
		add(panel_3, BorderLayout.NORTH);
		
		btnCargarPresupuestos = new JButton("Cargar presupuesto");
		panel_3.add(btnCargarPresupuestos);
		
	}

	public void cargarTabla(List<PresupuestoDTO> presupuestos) {
		for (PresupuestoDTO presupuesto : presupuestos) {
			if(presupuesto.getEstado()==EstadoPresupuesto.PENDIENTE) {
				Object[] row = { presupuesto.getIdPresupuesto().toString(), presupuesto.getFechaAltaPresu(), presupuesto.getComentarioAltaPresu(), presupuesto.getEstado() };
				listadoDePresupuestosModel.addRow(row);
			}
		}
		if(presupuestos.size()>=1) {
			botonvisible();
		}
	}

	public boolean iPersupuestoAprobado(int row, int column, JTable table) {
		return table.getValueAt(row, column) != null;
	}

	public void setActionOnBuscar(ActionListener listener) {
		this.btnCargarPresupuestos.addActionListener(listener);
	}
	
	public void setActionOnRegistrar(ActionListener listener) {
		this.btnRegistrarTrabajoRealizado.addActionListener(listener);
	}
	
	public void clear() {
		listadoDePresupuestosModel.setRowCount(0);
		listadoDePresupuestosModel.setColumnCount(0);
		listadoDePresupuestosModel.setColumnIdentifiers(columnasListadoDePresupuestos);
	}
	
	public int getIdPresupuestoSeleccionada(){
		int idSeleccionada=-1;
		int row = tablePresupuestos.getSelectedRow();
		if(row!=-1) {
			idSeleccionada= Integer.valueOf((String) tablePresupuestos.getValueAt(row, 0));
		}
		return idSeleccionada;
	}
	
	public void botonvisible() {
		this.btnRegistrarTrabajoRealizado.setEnabled(true);
	}
	
}
