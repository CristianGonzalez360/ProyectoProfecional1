package presentacion.views.admin;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;

import dto.MonedaDTO;
import dto.SucursalDTO;
import presentacion.views.vendedor.TablePanel;

public class AdminControlView extends JInternalFrame {

	private static final long serialVersionUID = -8187193486426314619L;

	private static AdminControlView instance;
	
	private PanelBusquedaSucursal panelBusqueda;
	
	private PanelDatosMoneda panelDatosMoneda;
	
	private TablePanel<SucursalDTO> tablePanel;
	
	public static AdminControlView getInstance() {
		if(instance == null) instance = new AdminControlView();
		return instance;
	}
	
	private AdminControlView() {
		setMaximizable(true);
		setIconifiable(true);
		setTitle("Admin control view");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panelBusqueda = new PanelBusquedaSucursal();
		getContentPane().add(panelBusqueda, BorderLayout.NORTH);
		
		tablePanel = new TablePanel<SucursalDTO>(new String [] {"Nro. de sucursal", "Pais", "Calle", "Altura", "Localidad"}) {
			private static final long serialVersionUID = -8335676016788818853L;
			@Override
			public void setData(List<SucursalDTO> data) {
				for(SucursalDTO suc: data) {
					Object [] row = { suc.getIdSucursal().toString(), suc.getPais(), suc.getCalle(), suc.getAltura(), suc.getLocalidad() };
					model.addRow(row);
				}
			}

			@Override
			public SucursalDTO getData() {
				SucursalDTO ret = null;
				if(table.getSelectedRowCount() == 1) {
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
		tablePanel.setBorder(new TitledBorder(null, "Listado de sucursales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panelCenter = new JPanel();
		getContentPane().add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new BorderLayout(0, 0));

		panelCenter.add(tablePanel, BorderLayout.CENTER);
				
		panelDatosMoneda = new PanelDatosMoneda();
		panelDatosMoneda.setNonEditable();
		panelCenter.add(panelDatosMoneda, BorderLayout.SOUTH);
	}
	
	public void display() {
		try {
			setMaximum(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
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
}
