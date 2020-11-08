package presentacion.views.supervisor;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public abstract class GenericTablePanelView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2374014421049375807L;
	private DefaultTableModel tableModel;	
	private JTable table;
	private String [] columns;
	
	public GenericTablePanelView(String [] columns) {
		this.columns = columns;
		setBorder(new TitledBorder(null, "Listado de vehiculos del cliente", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);

		tableModel = new DefaultTableModel(null, columns) {

			private static final long serialVersionUID = -8904398996893892884L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
	}

	public <T> void setData(List<T> entities, Mapper<T> m) {
		for (T entity : entities) {
			Object[] row = m.map(entity);
			this.tableModel.addRow(row);
		}
	}
	
	public abstract Object[] getSelectedRow();
	
	public void clearData() {
		this.tableModel.setRowCount(0);
		tableModel.setColumnCount(0);
		tableModel.setColumnIdentifiers(columns);
	}
}