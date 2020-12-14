package presentacion.views.gerente;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.toedter.calendar.JDateChooser;

import dto.TarjetaDebitoDTO;

public class ReporteRepuestos extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6720211970320268729L;

	private final JPanel contentPanel = new JPanel();

	private static ReporteRepuestos instance;
	private JButton btnSalvar;
	private JTextField textNumeroTarjeta;
	private JTextField textNombreYApellido;
	private JTextField textFechaExpiracion;
	private JTextField textCod;
	private JTextField textDni;

	private JDateChooser fechaExpiracion;

	private ReporteRepuestos() {
		setTitle("Pago con Tarjeta de Débito");
		setBounds(100, 100, 506, 253);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		setModal(true);

		JPanel panel = new JPanel();
		contentPanel.add(panel);
		panel.setLayout(new FormLayout(
				new ColumnSpec[] { ColumnSpec.decode("14px"), ColumnSpec.decode("right:max(37dlu;default)"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(80dlu;default):grow"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("right:89px"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(47dlu;default):grow"), },
				new RowSpec[] { FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("26px"), FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblNumeroTarjeta = new JLabel("Número de Tarjeta");
		panel.add(lblNumeroTarjeta, "2, 2, right, default");

		textNumeroTarjeta = new JTextField();
		textNumeroTarjeta.setColumns(10);
		panel.add(textNumeroTarjeta, "4, 2, fill, default");

		JLabel lblNombreYApellido = new JLabel("Nombre y Apellido");
		panel.add(lblNombreYApellido, "2, 4, right, default");

		textNombreYApellido = new JTextField();
		textNombreYApellido.setColumns(10);
		panel.add(textNombreYApellido, "4, 4, fill, default");

		JLabel lblFechaExpiracion = new JLabel("Fecha de Expiración");
		panel.add(lblFechaExpiracion, "2, 6, right, default");

		fechaExpiracion = new JDateChooser();
		panel.add(fechaExpiracion, "4, 6, fill, default");

		JLabel lblCod = new JLabel("CVV");
		panel.add(lblCod, "6, 6, right, default");

		textCod = new JTextField();
		textCod.setColumns(10);
		panel.add(textCod, "8, 6, fill, default");

		JLabel lblDni = new JLabel("DNI");
		panel.add(lblDni, "2, 8, right, default");

		textDni = new JTextField();
		textDni.setColumns(10);
		panel.add(textDni, "4, 8, fill, default");

		JPanel panel_1 = new JPanel();
		contentPanel.add(panel_1, BorderLayout.SOUTH);

		btnSalvar = new JButton("Confirmar Pago");
		panel_1.add(btnSalvar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		panel_1.add(btnCancelar);

		setVisible(false);
		clearData();

	}

	public static ReporteRepuestos getInstance() {
		if (instance == null) {
			instance = new ReporteRepuestos();
		}
		return instance;
	}

	public TarjetaDebitoDTO getData() {
		TarjetaDebitoDTO datosTarjeta = new TarjetaDebitoDTO();

		datosTarjeta.setNumeroTarjeta(textNumeroTarjeta.getText());
		datosTarjeta.setNombreyapellido(textNombreYApellido.getText());

		datosTarjeta.setCodSeguridad(textCod.getText());
		datosTarjeta.setDni(textDni.getText());

		return datosTarjeta;
	}

	public void setData(TarjetaDebitoDTO tarjeta) {
		TarjetaDebitoDTO datosTarjeta = new TarjetaDebitoDTO();

		textNumeroTarjeta.setText(datosTarjeta.getNumeroTarjeta());
		textNombreYApellido.setText(datosTarjeta.getNombreyapellido() + "");
		textFechaExpiracion.setText(datosTarjeta.getFechaExpiracion());
		textCod.setText(datosTarjeta.getCodSeguridad());
		textDni.setText(datosTarjeta.getDni() + "");
	}

	public void setActionOnRegistrar(ActionListener listener) {
		this.btnSalvar.addActionListener(listener);
	}

	public void clearData() {
		textNumeroTarjeta.setText("");
		textNombreYApellido.setText("");

		textCod.setText("");
		textDni.setText("");

		this.btnSalvar.setVisible(true);
	}

	public void display() {
		setVisible(true);
	}

	public void close() {
		setVisible(false);
	}
}