package presentacion.views.vendedor;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import dto.GarantiaVehiculoDTO;

import com.jgoodies.forms.layout.FormSpecs;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PanelCaracteristicasDeLaGarantia extends JPanel {

	private static final long serialVersionUID = 4901869599014839966L;

	private JTextField textAniosDeGarantia;

	private JTextField textKilometrajeInicialDelVehiculo;

	private JTextField textKilometrajeGarantizado;

	private JTextField textFechaInicioDeLaGarantia;

	private JTextField textFechaDeCaducidadDeLaGarantia;

	private JTextField textCostoFinalConIVA;

	public PanelCaracteristicasDeLaGarantia() {
		setBorder(new TitledBorder(null, "Datos de la garant\u00EDa ", TitledBorder.LEADING, TitledBorder.TOP, null,
				null));
		setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblNewLabel_1 = new JLabel("Años de Garantía");
		add(lblNewLabel_1, "2, 2");

		textAniosDeGarantia = new JTextField();
		add(textAniosDeGarantia, "4, 2, fill, default");
		textAniosDeGarantia.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Kilometraje Inicial del Vehiculo");
		add(lblNewLabel_5, "2, 4, left, default");

		textKilometrajeInicialDelVehiculo = new JTextField();
		add(textKilometrajeInicialDelVehiculo, "4, 4, fill, default");
		textKilometrajeInicialDelVehiculo.setColumns(10);

		JLabel lblNewLabel = new JLabel("Kilometraje Garantizado");
		add(lblNewLabel, "2, 6, left, default");

		textKilometrajeGarantizado = new JTextField();
		add(textKilometrajeGarantizado, "4, 6, fill, default");
		textKilometrajeGarantizado.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Fecha de Inicio de Garantía");
		add(lblNewLabel_2, "2, 8, left, default");

		textFechaInicioDeLaGarantia = new JTextField();
		add(textFechaInicioDeLaGarantia, "4, 8, fill, default");
		textFechaInicioDeLaGarantia.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Fecha de Caducidad de Garantía");
		add(lblNewLabel_3, "2, 10, left, default");

		textFechaDeCaducidadDeLaGarantia = new JTextField();
		add(textFechaDeCaducidadDeLaGarantia, "4, 10, fill, default");
		textFechaDeCaducidadDeLaGarantia.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Costo final con IVA");
		add(lblNewLabel_4, "2, 12, left, default");

		textCostoFinalConIVA = new JTextField();
		add(textCostoFinalConIVA, "4, 12, fill, default");
		textCostoFinalConIVA.setColumns(10);
		clearData();
	}

	public void disableAllInputs() {
		this.textAniosDeGarantia.setEditable(false);
		this.textKilometrajeInicialDelVehiculo.setEditable(false);
		this.textKilometrajeGarantizado.setEditable(false);
		this.textFechaInicioDeLaGarantia.setEditable(false);
		this.textFechaDeCaducidadDeLaGarantia.setEditable(false);
		this.textCostoFinalConIVA.setEditable(false);
	}

	public void setData(GarantiaVehiculoDTO dto) {
		this.textAniosDeGarantia.setText(dto.getAniosDeGarantia().toString());
		this.textKilometrajeInicialDelVehiculo.setText(dto.getKilometrajeInicialDelVehiculo().toString());
		this.textKilometrajeGarantizado.setText(dto.getKilometrajeGarantizado().toString());
		this.textFechaInicioDeLaGarantia.setText(dto.getFechaInicioDeLaGarantia() != null ? dto.getFechaInicioDeLaGarantia().toString() : "");
		this.textFechaDeCaducidadDeLaGarantia.setText(dto.getFechaDeCaducidadDeLaGarantia() != null ? dto.getFechaDeCaducidadDeLaGarantia().toString() : "");
		this.textCostoFinalConIVA.setText(dto.getCostoFinalConIVA().toString());
	}

	public void clearData() {
		this.textAniosDeGarantia.setText("");
		this.textKilometrajeInicialDelVehiculo.setText("");
		this.textKilometrajeGarantizado.setText("");
		this.textFechaInicioDeLaGarantia.setText("");
		this.textFechaDeCaducidadDeLaGarantia.setText("");
		this.textCostoFinalConIVA.setText("");
	}

	public GarantiaVehiculoDTO getData() {
		GarantiaVehiculoDTO dto = new GarantiaVehiculoDTO();
		dto.setAniosDeGarantia((textAniosDeGarantia.getText().isEmpty() ? null : Integer.parseInt(textAniosDeGarantia.getText())));
		dto.setKilometrajeInicialDelVehiculo(textKilometrajeInicialDelVehiculo.getText().isEmpty() ? null : Integer.parseInt(textKilometrajeInicialDelVehiculo.getText()));
		dto.setKilometrajeGarantizado(textKilometrajeGarantizado.getText().isEmpty() ? null : Integer.parseInt(textKilometrajeGarantizado.getText()));
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-dd-mm");
		try {
			String date = textFechaInicioDeLaGarantia.getText();
			if(!date.isEmpty())dto.setFechaInicioDeLaGarantia(sdf.parse(textFechaInicioDeLaGarantia.getText()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			String date = textFechaDeCaducidadDeLaGarantia.getText();
			if(!date.isEmpty())	dto.setFechaDeCaducidadDeLaGarantia(sdf.parse(textFechaDeCaducidadDeLaGarantia.getText()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dto.setCostoFinalConIVA(textCostoFinalConIVA.getText().isEmpty() ? null : Double.parseDouble(textCostoFinalConIVA.getText()));
		return dto;
	}
}
