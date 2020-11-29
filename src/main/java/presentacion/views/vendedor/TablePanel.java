package presentacion.views.vendedor;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public abstract class TablePanel<T> extends JPanel {
	
	private static final long serialVersionUID = -6624934592820387877L;

	protected String [] nombreColumnas;
	
	protected DefaultTableModel model;
	
	protected JTable table;
	
	public TablePanel(String [] nombreColumnas) {
		this.nombreColumnas = nombreColumnas; 
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		model = new DefaultTableModel(null, this.nombreColumnas);
		table = new JTable(model);
		scrollPane.setViewportView(table);
	}
	
	public abstract void setData(List<T> data);
	
	public Integer getData() {
		int row = table.getSelectedRow();
		Integer id = null;
		if (table.getSelectedRowCount() == 1) {
			id = Integer.parseInt(table.getValueAt(row, 0).toString());
			return id;
		}
		return null;
	}
		
	public void clearData() {
		model.setRowCount(0);
		model.setColumnCount(0);
		model.setColumnIdentifiers(nombreColumnas);
	}
}
