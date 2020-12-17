package presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;

import dto.MonedaDTO;
import dto.SucursalDTO;
import presentacion.views.admin.PanelBusquedaSucursal;
import presentacion.views.admin.PanelDataMoneda;
import presentacion.views.vendedor.TablePanel;

public class PanelSucursales extends JPanel {

	private static final long serialVersionUID = -8187193486426314619L;

	private PanelBusquedaSucursal panelBusqueda;

	private PanelDataMoneda panelDatosMoneda;

	private TablePanel<SucursalDTO> tablePanel;

	private JPanel panel;

	private JButton btnEscogerComoTerminal;
	
	private JButton btnRegistrarSucursal;
	private JButton btnRegistrarMoneda;

	public PanelSucursales() {
		setBounds(100, 100, 633, 424);
		setLayout(new BorderLayout(0, 0));

		panelBusqueda = new PanelBusquedaSucursal();
		add(panelBusqueda, BorderLayout.NORTH);
		
		btnRegistrarMoneda = new JButton("Registrar moneda");
		panelBusqueda.add(btnRegistrarMoneda);

		tablePanel = new TablePanel<SucursalDTO>(
				new String[] { "Nro. de sucursal", "Pais", "Calle", "Altura", "Localidad" }) {
			private static final long serialVersionUID = -8335676016788818853L;

			@Override
			public void setData(List<SucursalDTO> data) {
				for (SucursalDTO suc : data) {
					Object[] row = { suc.getIdSucursal().toString(), suc.getPais(), suc.getCalle(), suc.getAltura(),
							suc.getLocalidad() };
					model.addRow(row);
				}
			}

			@Override
			public SucursalDTO getData() {
				SucursalDTO ret = null;
				if (table.getSelectedRowCount() == 1) {
					int row = table.getSelectedRow();
					ret = new SucursalDTO();
					ret.setIdSucursal(Integer.parseInt(model.getValueAt(row, 0).toString()));
					ret.setPais(model.getValueAt(row, 1).toString());
					ret.setCalle(model.getValueAt(row, 2).toString());
					ret.setAltura(Integer.parseInt(model.getValueAt(row, 3).toString()));
					ret.setLocalidad(model.getValueAt(row, 4).toString());
					ret.setIdMoneda(0);
				}
				return ret;
			}
		};
		tablePanel.setBorder(
				new TitledBorder(null, "Listado de sucursales", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panelCenter = new JPanel();
		add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));

		panelCenter.add(tablePanel);

		panelDatosMoneda = new PanelDataMoneda();
		panelDatosMoneda.setNonEditable();
		panelCenter.add(panelDatosMoneda);

		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelCenter.add(panel);
		
		btnRegistrarSucursal = new JButton("Registrar sucursal");
		panel.add(btnRegistrarSucursal);

		btnEscogerComoTerminal = new JButton("Escoger como terminal");
		panel.add(btnEscogerComoTerminal);
	}

	public String getDataNombrePais() {
		return this.panelBusqueda.getData();
	}

	public void setActionBuscarSucursal(ActionListener listener) {
		this.panelBusqueda.setActionBuscar(listener);
	}

	public void addPaisesDeBusqueda(String[] strings) {
		this.panelBusqueda.addPaises(strings);
	}

	public void clearData() {
		this.tablePanel.clearData();
		this.panelDatosMoneda.clearData();
	}

	public void setData(List<SucursalDTO> sucursales) {
		this.tablePanel.setData(sucursales);
	}

	public void setActionSeleccionSucursal(ListSelectionListener listener) {
		this.tablePanel.setActionSelect(listener);
	}

	public SucursalDTO getData() {
		return tablePanel.getData();
	}

	public void setData(MonedaDTO moneda) {
		this.panelDatosMoneda.setData(moneda);
	}

	public void clearDataMoneda() {
		this.panelDatosMoneda.clearData();
	}

	public void setActionEscogerTerminal(ActionListener listener) {
		this.btnEscogerComoTerminal.addActionListener(listener);
	}

	public void setActionRegistrarSucursal(ActionListener listener) {
		this.btnRegistrarSucursal.addActionListener(listener);
	}
	
	public void setActionRegistrarMoneda(ActionListener listener) {
		this.btnRegistrarMoneda.addActionListener(listener);
	}
	
	public void close() {
		setVisible(false);
	}
}
