package presentacion.views.gerente;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionListener;

import dto.CaracteristicaVehiculoDTO;
import dto.VehiculoDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
import presentacion.views.vendedor.CaracteristicaDeVehiculoPanel;
import presentacion.views.vendedor.TablePanel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import java.awt.Color;

public class PanelVehiculosUsados extends JPanel {

	private static final long serialVersionUID = 1L;
	private CaracteristicaDeVehiculoPanel caracteristicas;
	private FichaTecnicaPanel fichaTecnica;
	private TablePanel<VehiculoDTO> tabla;
	private static final String[] columnas = { "NRO", "Marca", "Familia", "Linea" };
	private static PanelVehiculosUsados instance;
	private JPanel panel;
	private JButton btnRegistrar;

	private PanelVehiculosUsados() {
		setLayout(new BorderLayout());
		JPanel panelCentral = new JPanel();
		add(panelCentral, BorderLayout.CENTER);
		this.caracteristicas = new CaracteristicaDeVehiculoPanel();
		caracteristicas.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos T\u00E9cnicos del Veh\u00EDculo",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		this.fichaTecnica = new FichaTecnicaPanel();
		fichaTecnica.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n del Veh\u00EDculo",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		this.tabla = new TablePanel<VehiculoDTO>(columnas) {

			private static final long serialVersionUID = 2129598190864691648L;

			@Override
			public void setData(List<VehiculoDTO> data) {
				clearData();
				for (VehiculoDTO vehiculo : data) {
					Object[] row = { vehiculo.getIdVehiculo(), vehiculo.getMarca(), vehiculo.getFamilia(),
							vehiculo.getLinea() };
					model.addRow(row);
				}
			}

			@Override
			public VehiculoDTO getData() {
				int selectedRow = table.getSelectedRow();
				VehiculoDTO ret = null;
				if (selectedRow > -1) {
					ret = new VehiculoDTO();
					ret.setIdVehiculo((Integer) model.getValueAt(selectedRow, 0));
				}
				return ret;
			}
		};
		tabla.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Veh\u00EDculos Usados",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		panelCentral.add(tabla);
		panelCentral.add(fichaTecnica);
		panelCentral.add(caracteristicas);

		panel = new JPanel();
		add(panel, BorderLayout.SOUTH);

		btnRegistrar = new JButton("Registrar");
		panel.add(btnRegistrar);
	}

	public static PanelVehiculosUsados getInstance() {
		if (instance == null)
			instance = new PanelVehiculosUsados();
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

	public void setActionOnRegistrar(ActionListener listener) {
		this.btnRegistrar.addActionListener(listener);
	}

	public void clearData() {
		this.caracteristicas.clearData();
		this.fichaTecnica.cleardata();
		this.tabla.clearData();
	}
}
