package presentacion.views.gerente;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JDialog;
import presentacion.views.vendedor.BusquedaVehiculoPanel;
import presentacion.views.vendedor.CaracteristicaDeVehiculoPanel;
import presentacion.views.vendedor.TablePanel;
import javax.swing.JPanel;
import dto.temporal.ConsultaVehiculoParaVentaDTO;
import dto.temporal.OutputConsultaVehiculoEnVentaDTO;
import javax.swing.JButton;

public class RegistroPedidoView extends JDialog {
	
	private TablePanel<OutputConsultaVehiculoEnVentaDTO> tableView;
	private BusquedaVehiculoPanel buscador;
	private CaracteristicaDeVehiculoPanel caracteristias;
	private JPanel panel;
	private JButton btnRegistrarPedido;
	
	private static RegistroPedidoView instance;
	
	public static RegistroPedidoView getInstance() {
		if(instance == null) {
			instance = new RegistroPedidoView();
		}
		return instance;
	}

	private RegistroPedidoView() {
		
		buscador = new BusquedaVehiculoPanel();
		getContentPane().add(buscador, BorderLayout.NORTH);
		
		JPanel panelCentral = new JPanel();
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		tableView = new TablePanel<OutputConsultaVehiculoEnVentaDTO>(new String [] {"Código", "Marca", "Familia", "Linea", "Cilindrada", "Color", "Precio"}) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 2082711134029767282L;

			@Override
			public void setData(List<OutputConsultaVehiculoEnVentaDTO> data) {
				for(OutputConsultaVehiculoEnVentaDTO dto : data) {
					Object [] row = { dto.getCodigo(), dto.getMarca(), dto.getFamilia(), dto.getLinea(), dto.getCilindrada(), dto.getColor(), dto.getPrecio() };
					model.addRow(row);	
				}
			}

			@Override
			public OutputConsultaVehiculoEnVentaDTO getData() {
				OutputConsultaVehiculoEnVentaDTO ret = null;
				if(table.getSelectedRowCount() == 1) {
					int row = table.getSelectedRow();
					ret = new OutputConsultaVehiculoEnVentaDTO();
					ret.setCodigo(model.getValueAt(row, 0).toString());
					ret.setMarca(model.getValueAt(row, 1).toString());
					ret.setFamilia(model.getValueAt(row, 2).toString());
					ret.setLinea(model.getValueAt(row, 3).toString());
					ret.setCilindrada(model.getValueAt(row, 4).toString());
					ret.setColor(model.getValueAt(row, 5).toString());
					ret.setPrecio(model.getValueAt(row, 6).toString());
				}
				return ret;
			}
		};
		panelCentral.add(tableView, BorderLayout.CENTER);
		caracteristias = new CaracteristicaDeVehiculoPanel();
		panelCentral.add(caracteristias, BorderLayout.SOUTH);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		btnRegistrarPedido = new JButton("Registrar Pedido");
		panel.add(btnRegistrarPedido);
		
		setVisible(false);
	}
	
	public void display() {
		setVisible(true);
	}
	
	public void close() {
		setVisible(false);
	}
	
	public void setActiioOnRegistrar(ActionListener listener) {
		btnRegistrarPedido.addActionListener(listener);
	}
	
	public void setActionOnBuscar(ActionListener listener) {
		buscador.setActionBuscar(listener);
	}

	public ConsultaVehiculoParaVentaDTO getData() {
		return buscador.getData();
	}

	public void clearData() {
		tableView.clearData();
	}

	public void setData(List<OutputConsultaVehiculoEnVentaDTO> vehiculos) {
		tableView.setData(vehiculos);
	}
}
