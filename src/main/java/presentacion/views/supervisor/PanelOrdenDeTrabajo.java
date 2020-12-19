package presentacion.views.supervisor;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import dto.taller.OrdenDeTrabajoDTO;

public class PanelOrdenDeTrabajo extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel lblNewLabel_15;
	private JTextField textFechaAltaOt;
	private JLabel lblNewLabel_16;
	private JTextField textFechaCierreOt;
	private JLabel lblNewLabel_17;
	private JTextField textTrabajoSolicitadoOt;
	private JLabel lblNewLabel_18;
	private JTextField textTrabajoSugeridoOt;
	private JLabel lblTipoDeOrden;
	private JTextField textTipoDeOT;
	/**
	 * Create the panel.
	 */
	public PanelOrdenDeTrabajo() {
		setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, }));

		lblNewLabel_15 = new JLabel("Fecha de Alta");
		add(lblNewLabel_15, "2, 2");

		textFechaAltaOt = new JTextField();
		textFechaAltaOt.setEditable(false);
		add(textFechaAltaOt, "4, 2, fill, default");
		textFechaAltaOt.setColumns(10);

		lblNewLabel_16 = new JLabel("Fecha de Cierre");
		add(lblNewLabel_16, "2, 4");

		textFechaCierreOt = new JTextField();
		textFechaCierreOt.setEditable(false);
		add(textFechaCierreOt, "4, 4, fill, default");
		textFechaCierreOt.setColumns(10);

		lblNewLabel_17 = new JLabel("Trabajo Solicitado");
		add(lblNewLabel_17, "2, 6, left, default");

		textTrabajoSolicitadoOt = new JTextField();
		textTrabajoSolicitadoOt.setEditable(false);
		add(textTrabajoSolicitadoOt, "4, 6, fill, default");
		textTrabajoSolicitadoOt.setColumns(10);

		lblNewLabel_18 = new JLabel("Trabajo Sugerido");
		add(lblNewLabel_18, "2, 8");

		textTrabajoSugeridoOt = new JTextField();
		textTrabajoSugeridoOt.setEditable(false);
		add(textTrabajoSugeridoOt, "4, 8, fill, default");
		textTrabajoSugeridoOt.setColumns(10);

		lblTipoDeOrden = new JLabel("Tipo de Orden de Trabajo");
		add(lblTipoDeOrden, "2, 10, right, default");

		textTipoDeOT = new JTextField();
		textTipoDeOT.setEditable(false);
		add(textTipoDeOT, "4, 10, fill, default");
		textTipoDeOT.setColumns(10);
	}
	
	public void setData(OrdenDeTrabajoDTO ordenDeTrabajo) {
		this.textFechaAltaOt.setText(ordenDeTrabajo.getFechaDeAlta().toString());
		this.textFechaCierreOt.setText(
				ordenDeTrabajo.getFechaEntregado() != null ? ordenDeTrabajo.getFechaEntregado().toString() : "");
		this.textTrabajoSugeridoOt.setText(ordenDeTrabajo.getTrabajoSujerido());
		this.textTrabajoSolicitadoOt.setText(ordenDeTrabajo.getTrabajoSolicitado());
		this.textTipoDeOT.setText(ordenDeTrabajo.getTipoOrdeTrabajo());
	}
	
	public void clearData() {
		this.textFechaAltaOt.setText("");
		this.textFechaCierreOt.setText("");
		this.textTrabajoSugeridoOt.setText("");
		this.textTrabajoSolicitadoOt.setText("");
		this.textTipoDeOT.setText("");
	}

}
