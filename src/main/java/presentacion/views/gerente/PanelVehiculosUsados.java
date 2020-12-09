package presentacion.views.gerente;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionListener;

import dto.CaracteristicaVehiculoDTO;
import dto.VehiculoDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
import presentacion.views.vendedor.CaracteristicaDeVehiculoPanel;
import presentacion.views.vendedor.TablePanel;

public class PanelVehiculosUsados extends JPanel {

	private static final long serialVersionUID = 1L;
	private CaracteristicaDeVehiculoPanel caracteristicas;
	private FichaTecnicaPanel fichaTecnica;
	private TablePanel<VehiculoDTO> tabla;
	private static final String[] columnas = {"NRO", "Marca", "Familia", "Linea"};
	private static PanelVehiculosUsados instance;
	
	private PanelVehiculosUsados() {
		setLayout(new BorderLayout());
		JPanel panelCentral = new JPanel();
		add(panelCentral, BorderLayout.CENTER);
		this.caracteristicas = new CaracteristicaDeVehiculoPanel();
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		this.fichaTecnica = new FichaTecnicaPanel();

		this.tabla = new TablePanel<VehiculoDTO>(columnas) {

			private static final long serialVersionUID = 2129598190864691648L;

			@Override
			public void setData(List<VehiculoDTO> data) {
				clearData();
				for (VehiculoDTO vehiculo : data) {
					Object[] row = {vehiculo.getIdVehiculo(), vehiculo.getMarca(), vehiculo.getFamilia(), vehiculo.getLinea()};
					model.addRow(row);
				}				
			}
			
			@Override
			public VehiculoDTO getData() {
				int selectedRow = table.getSelectedRow();
				VehiculoDTO ret = null; 
				if(selectedRow > -1) {
					ret = new VehiculoDTO();
					ret.setIdVehiculo((Integer) model.getValueAt(selectedRow, 0));
				}
				return ret;
			}
		};
		
		panelCentral.add(tabla);
		panelCentral.add(fichaTecnica);
		panelCentral.add(caracteristicas);
	}
	
	public static PanelVehiculosUsados getInstance() {
		if(instance == null) instance = new PanelVehiculosUsados();
		return instance;
	}

	public void setActionOnSeleccionarVehiculo(ListSelectionListener listener) {
		this.tabla.setActionSelect(listener);
	}
	
	public VehiculoDTO getSeleccionado() {
		return tabla.getData();
	}

	public void setData(List<VehiculoDTO> vehiculos) {
		tabla.setData(vehiculos);
	}

	public void setData(CaracteristicaVehiculoDTO carac) {
		this.caracteristicas.setData(carac);
	}

	public void setData(FichaTecnicaVehiculoDTO ficha) {
		this.fichaTecnica.setdata(ficha);
		
	}

	public void setData(VehiculoDTO vehiculo) {
		this.fichaTecnica.setdata(vehiculo);
		
	}
	
	
}
