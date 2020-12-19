
package presentacion.views.cajero;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTextField;
import java.awt.Label;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import dto.taller.FacturaDTO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;

public class PanelCobroCajeroView extends JPanel {

	private static final long serialVersionUID = -3152338359660079392L;

	private static PanelCobroCajeroView instance;

	private final String[] columnasListadoDeFacturas = new String[] { "NRO. FACTURA", "DNI", "FECHA DE PAGO", "TOTAL",
			"ESTADO" };

	private DefaultTableModel listadoDeFacturasModel;

	private JPanel panel;
	private JScrollPane scrollPaneFacturas;
	private JTable tableFacturas;
	private JPanel panel_1;
	private JPanel panel_3;
	private JButton btnCargarFacturas;
	private JTextField textFactura;
	private JPanel panel_2;
	private JButton btnTarjetaCredito;
	private JButton btnTarjetaDebito;
	private JLabel lblNewLabel;
	private JButton btnMercadoPago;
	private JButton btnBitcoins;
	private JButton btnEfectivo;

	public static PanelCobroCajeroView getInstance() {
		if (instance == null) {
			instance = new PanelCobroCajeroView();
		}
		return instance;
	}

	public PanelCobroCajeroView() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		panel_3 = new JPanel();
		add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		Label label = new Label("Nro. Factura");
		panel_3.add(label);

		textFactura = new JTextField();
		panel_3.add(textFactura);
		textFactura.setColumns(10);

		btnCargarFacturas = new JButton("Cargar Factura");
		panel_3.add(btnCargarFacturas);

		panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Listado de facturas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel);

		this.listadoDeFacturasModel = new DefaultTableModel(null, this.columnasListadoDeFacturas);
		;
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		scrollPaneFacturas = new JScrollPane();
		panel.add(scrollPaneFacturas);
		tableFacturas = new JTable(listadoDeFacturasModel) {// tabla no editable

			private static final long serialVersionUID = -7694783590163789939L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPaneFacturas.setViewportView(tableFacturas);

		panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("100dlu"),
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("100dlu"), FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("100dlu"), FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("100dlu"),
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("100dlu"), FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(47dlu;default)"), }));

		btnTarjetaCredito = new JButton("Tarjeta de Crédito");
		btnTarjetaCredito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		lblNewLabel = new JLabel("Seleccione un medio de pago:");
		panel_2.add(lblNewLabel, "2, 2, default, center");

		btnEfectivo = new JButton("Efectivo");
		btnEfectivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_2.add(btnEfectivo, "2, 4");
		this.btnEfectivo.setEnabled(false);
		panel_2.add(btnTarjetaCredito, "2, 6");

		btnTarjetaDebito = new JButton("Tarjeta de Debito");
		btnTarjetaDebito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_2.add(btnTarjetaDebito, "6, 6");

		btnMercadoPago = new JButton("MercadoPago");
		btnMercadoPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_2.add(btnMercadoPago, "10, 6");

		btnBitcoins = new JButton("Bitcoins");
		btnBitcoins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_2.add(btnBitcoins, "14, 6");

		panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(15);
		panel.add(panel_1);

		this.btnTarjetaCredito.setEnabled(false);
		this.btnTarjetaDebito.setEnabled(false);
		this.btnMercadoPago.setEnabled(false);
		this.btnBitcoins.setEnabled(false);

	}

	public void cargarTabla(List<FacturaDTO> presupuestos) {
		for (FacturaDTO presupuesto : presupuestos) {
			Object[] row = { presupuesto.getIdFactura().toString(), presupuesto.getDni(),
					presupuesto.getFechaDeCierrePorPago(), presupuesto.getTotal(), presupuesto.getEstado() };
			listadoDeFacturasModel.addRow(row);
		}
		if (presupuestos.size() >= 1) {
			botonvisible();
		}
	}

	public void cargarTabla(FacturaDTO presupuesto) {
		if (presupuesto != null) {
			Object[] row = { presupuesto.getIdFactura().toString(), presupuesto.getDni(),
					presupuesto.getFechaDeCierrePorPago(), presupuesto.getTotal(), presupuesto.getEstado() };
			listadoDeFacturasModel.addRow(row);
			botonvisible();
		}
	}

	public String getFactura() {
		return (String) textFactura.getText();
	}

	public boolean iPersupuestoAprobado(int row, int column, JTable table) {
		return table.getValueAt(row, column) != null;
	}

	public void setActionOnBuscar(ActionListener listener) {
		this.btnCargarFacturas.addActionListener(listener);

	}

	public void clear() {
		listadoDeFacturasModel.setRowCount(0);
		listadoDeFacturasModel.setColumnCount(0);
		listadoDeFacturasModel.setColumnIdentifiers(columnasListadoDeFacturas);
	}

	public int getIdPresupuestoSeleccionada() {
		int idSeleccionada = -1;
		int row = tableFacturas.getSelectedRow();
		if (row != -1) {
			idSeleccionada = Integer.valueOf((String) tableFacturas.getValueAt(row, 0));
		}
		return idSeleccionada;
	}

	public String getEstadoSeleccionada() {
		String idSeleccionada = null;
		int row = tableFacturas.getSelectedRow();
		if (row != -1) {
			idSeleccionada = (String) tableFacturas.getValueAt(row, 4);
		}
		return idSeleccionada;
	}

	public Double getTotalSeleccionada() {
		Double idSeleccionada = null;
		int row = tableFacturas.getSelectedRow();
		if (row != -1) {
			idSeleccionada = (Double) tableFacturas.getValueAt(row, 3);
		}
		return idSeleccionada;
	}

	public void botonvisible() {
		this.btnTarjetaCredito.setEnabled(true);
		this.btnTarjetaDebito.setEnabled(true);
		this.btnMercadoPago.setEnabled(true);
		this.btnBitcoins.setEnabled(true);
		this.btnEfectivo.setEnabled(true);
	}

	public void setActionRegistrarTarjetaCredito(ActionListener listener) {
		this.btnTarjetaCredito.addActionListener(listener);
	}

	public void setActionRegistrarTarjetaDebito(ActionListener listener) {
		this.btnTarjetaDebito.addActionListener(listener);
	}

	public void setActionRegistrarMercadoPago(ActionListener listener) {
		this.btnMercadoPago.addActionListener(listener);
	}

	public void setActionRegistrarBitcoins(ActionListener listener) {
		this.btnBitcoins.addActionListener(listener);
	}

	public void setActionRegistrarEfectivo(ActionListener listener) {
		this.btnEfectivo.addActionListener(listener);
	}
}