package presentacion.views.supervisor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

@SuppressWarnings("serial")
public class ConfiguracionView extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private static ConfiguracionView instance;

	private JButton btnConfirmar;
	private JButton btnCancelar;

	private SpinnerNumberModel modelSpinner;
	private JSpinner spinner;

	public static ConfiguracionView getInstance() {
		if (instance == null)
			instance = new ConfiguracionView();
		return instance;
	}

	private ConfiguracionView() {
		setBounds(100, 100, 370, 241);
		setTitle("Configuración");
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panelForm = new JPanel();
		panelForm.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Capacidad de Turnos de Taller", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelForm.setBounds(10, 11, 334, 147);
		contentPanel.add(panelForm);
		panelForm.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		modelSpinner = new SpinnerNumberModel(1, 0, 1000, 1);

		JLabel lblCapacidad = new JLabel("Cantidad de Turnos");
		panelForm.add(lblCapacidad, "4, 6");
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 1000, 1));
		panelForm.add(spinner, "10, 6");
		
		JLabel lblSin = new JLabel("0 = sin límite.");
		panelForm.add(lblSin, "10, 8");

		{
			JPanel panelButton = new JPanel();
			panelButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(panelButton, BorderLayout.SOUTH);
			{
				btnConfirmar = new JButton("Cambiar");
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

	public String getCapacidadTurnos() {
		return this.spinner.getValue().toString();
	}

	public void setCapacidadTurnos(String capacidad) {
		this.spinner.setValue(capacidad);
	}

	public Date getFechaHoy() {
		return new Date();
	}

	public void clearData() {
		this.modelSpinner.setValue(0);

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
}
