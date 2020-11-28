package presentacion.views.vendedor;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TablePanel extends JPanel {
	
	private static final long serialVersionUID = -6624934592820387877L;

	private String [] nombreColumnas;
	
	private DefaultTableModel model;
	
	private JTable table;
	
	public TablePanel(String [] nombreColumnas) {
		this.nombreColumnas = nombreColumnas; 
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		model = new DefaultTableModel(null, this.nombreColumnas);
		table = new JTable(model);
		scrollPane.setViewportView(table);
	} 
}
