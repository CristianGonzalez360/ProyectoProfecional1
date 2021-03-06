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
import com.toedter.calendar.JMonthChooser;

import dto.TarjetaDebitoDTO;

public class ReporteIngresoMensualFormView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -976490381401830710L;

	private final JPanel contentPanel = new JPanel();

	private static ReporteIngresoMensualFormView instance;
	private JButton btnSalvar;
	private JTextField textFechaExpiracion;

	private JMonthChooser fechaSeleccionada;

	private ReporteIngresoMensualFormView() {
		setTitle("Reporte ingreso mensual");
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

		JLabel lblFecha = new JLabel("Fecha de reporte");
		panel.add(lblFecha, "4, 6, right, default");

		fechaSeleccionada = new JMonthChooser();
		panel.add(fechaSeleccionada, "6, 6, fill, default");

		JPanel panel_1 = new JPanel();
		contentPanel.add(panel_1, BorderLayout.SOUTH);

		btnSalvar = new JButton("Confirmar");
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

	public static ReporteIngresoMensualFormView getInstance() {
		if (instance == null) {
			instance = new ReporteIngresoMensualFormView();
		}
		return instance;
	}

//	Devuelve la fecha seleccionada en el datePicker
	public int getData() {
		int mes = fechaSeleccionada.getMonth();
		return mes;
	}

	public void setData(TarjetaDebitoDTO tarjeta) {
		TarjetaDebitoDTO datosTarjeta = new TarjetaDebitoDTO();

		textFechaExpiracion.setText(datosTarjeta.getFechaExpiracion());
	}

	public void setActionOnRegistrar(ActionListener listener) {
		this.btnSalvar.addActionListener(listener);
	}

	public void clearData() {

		this.btnSalvar.setVisible(true);
	}

	public void display() {
		setVisible(true);
	}

	public void close() {
		setVisible(false);
	}
}