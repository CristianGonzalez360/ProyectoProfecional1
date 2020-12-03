package presentacion.views.gerente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

@SuppressWarnings("serial")
public class RegistrarEntregaVehiculoFormView extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private static RegistrarEntregaVehiculoFormView instance;
	private JTextField textNombre;
	private JTextField textDNI;

	private JButton btnConfirmar;
	private JButton btnCancelar;
	private JTextField textFechaEntrega;

	public static RegistrarEntregaVehiculoFormView getInstance() {
		if (instance == null)
			instance = new RegistrarEntregaVehiculoFormView();
		return instance;
	}

	private RegistrarEntregaVehiculoFormView() {
		setBounds(100, 100, 350, 320);
		setTitle("Registrar entrega de vehiculo");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panelForm = new JPanel();
		panelForm.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelForm.setBounds(10, 11, 314, 226);
		contentPanel.add(panelForm);
		panelForm.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblFabricante = new JLabel("Fabricante");
		panelForm.add(lblFabricante, "4, 4");

		textNombre = new JTextField();
		panelForm.add(textNombre, "10, 4, fill, default");
		textNombre.setColumns(10);

		JLabel lblFechaVenta = new JLabel("Fecha Venta");
		panelForm.add(lblFechaVenta, "4, 6");

		textDNI = new JTextField();
		panelForm.add(textDNI, "10, 6, fill, default");
		textDNI.setColumns(10);

		JLabel lblFecha = new JLabel("Fecha de Entrega");
		panelForm.add(lblFecha, "4, 12");

		textFechaEntrega = new JTextField();
		panelForm.add(textFechaEntrega, "10, 12, fill, default");
		textFechaEntrega.setColumns(10);

		{
			JPanel panelButton = new JPanel();
			panelButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(panelButton, BorderLayout.SOUTH);
			{
				btnConfirmar = new JButton("OK");
				btnConfirmar.setActionCommand("OK");
				panelButton.add(btnConfirmar);
				getRootPane().setDefaultButton(btnConfirmar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setActionCommand("Cancelar");
				panelButton.add(btnCancelar);
			}
		}
		setModal(true);
		setVisible(false);
	}

	public void display() {
		this.setVisible(true);
	}

//	public VentaVehiculoDTO getData() {
//		VentaVehiculoDTO ventaDeVehiculo = new AltaDeTurnoDTO();
//		ventaDeVehiculo.setFabricante(getNombre());
//		ventaDeVehiculo.setDniCliente(getDNI());
//		ventaDeVehiculo.setTelefonoCliente(getTelefono());
//		ventaDeVehiculo.setFechaProgramada(getFechaTurno());
//		ventaDeVehiculo.setFechaAlta(getFechaHoy());
//		return ventaDeVehiculo;
//	}

	public Date getFechaHoy() {
		return new Date();
	}

	public void clearData() {
		textNombre.setText(null);
		textDNI.setText(null);
		textFechaEntrega.setText(null);

		this.btnConfirmar.setVisible(true);
		this.btnCancelar.setVisible(true);
	}

	public void setActionSave(ActionListener listener) {
		this.btnConfirmar.addActionListener(listener);
	}

	public void setActionCancel(ActionListener listener) {
		this.btnCancelar.addActionListener(listener);
	}

	public void cerrar() {
		setVisible(false);
	}

	public String getNombre() {
		return textNombre.getText();
	}

	public String getDNI() {
		return textDNI.getText();
	}

}
