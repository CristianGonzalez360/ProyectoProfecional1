package presentacion.views.gerente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;

import dto.CaracteristicaVehiculoDTO;
import dto.ClienteDTO;
import dto.VentaVehiculoDTO;
import presentacion.views.supervisor.ClientePanelView;
import presentacion.views.vendedor.CaracteristicaDeVehiculoPanel;

public class PanelRegistroPedido extends JPanel {

	private static final long serialVersionUID = 1L;
	private ClientePanelView panelCliente;
	private CaracteristicaDeVehiculoPanel panelVehiculo;
	private PanelVentas panelVentas;

	private static PanelRegistroPedido instance;
	private JPanel panelSuperior;
	private JPanel PanelInferior;
	private JButton btnRefrescar;
	private JButton btnRegistrarPedido;

	private PanelRegistroPedido() {
		setLayout(new BorderLayout(0, 0));

		JPanel principal = new JPanel();
		add(principal, BorderLayout.CENTER);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 413, 129, 0 };
		gridBagLayout.rowHeights = new int[] { 272, 142, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		principal.setLayout(gridBagLayout);

		panelVentas = new PanelVentas();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		principal.add(panelVentas, gbc_panel);

		panelCliente = new ClientePanelView();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 0;
		principal.add(panelCliente, gbc_panel_2);

		panelVehiculo = new CaracteristicaDeVehiculoPanel();
		panelVehiculo.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
				"Datos de Vehiculo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		principal.add(panelVehiculo, gbc_panel_1);

		panelSuperior = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelSuperior.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panelSuperior, BorderLayout.NORTH);

		btnRefrescar = new JButton("Refrescar");
		panelSuperior.add(btnRefrescar);

		PanelInferior = new JPanel();
		add(PanelInferior, BorderLayout.SOUTH);

		btnRegistrarPedido = new JButton("Registrar Pedido");
		PanelInferior.add(btnRegistrarPedido);
	}

	public static PanelRegistroPedido getInstance() {
		if (instance == null) {
			instance = new PanelRegistroPedido();
		}
		return instance;
	}

	public void setData(List<VentaVehiculoDTO> ventas) {
		this.panelVentas.setData(ventas);
	}

	public void setData(ClienteDTO cliente) {
		this.panelCliente.setData(cliente);
	}

	public void setdata(CaracteristicaVehiculoDTO vehiculo) {
		this.panelVehiculo.setData(vehiculo);
	}

	public void setActionOnSeleccionarVenta(ListSelectionListener listener) {
		this.panelVentas.setActionOnSeleccionarVenta(listener);
	}

	public int getFilaSeleciconada() {
		return panelVentas.getFIlaSaleccionada();
	}

	public void setActionOnRefrescar(ActionListener listener) {
		this.btnRefrescar.addActionListener(listener);
	}

	public void setActionOnRegistrar(ActionListener listener) {
		this.btnRegistrarPedido.addActionListener(listener);
	}

	public void clearData() {
		panelCliente.clearData();
		panelVehiculo.clearData();
		panelVentas.clearData();
	}
}
