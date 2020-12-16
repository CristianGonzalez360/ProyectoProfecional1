package presentacion.views.supervisor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import dto.taller.TurnoDTO;

public class PanelTurnos extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final String[] COLUMNAS_TURNOS = new String[] { "NRO. TURNO", "NOMBRE", "APELLIDO", "DNI",
			"FECHA DE ALTA", "FECHA PROGRAMADA", "TELEFONO", "EMAIL" };

	private JTextField textFieldDni;

	private JTable tableTurnos;

	private DefaultTableModel tableModelTurnos;

	private JButton btnBuscar;

	private JButton btnRegistrarTurno;

	private JButton btnCancelarTurno;

	private JLabel lblNewLabel;
	private JButton btnConfiguracion;

	public PanelTurnos() {
		setLayout(new BorderLayout(0, 0));
		JPanel panel_1 = new JPanel();
		setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setHgap(20);
		add(panel_1, BorderLayout.NORTH);

		lblNewLabel = new JLabel("DNI");
		panel_1.add(lblNewLabel);

		textFieldDni = new JTextField("");
		textFieldDni.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(textFieldDni);
		textFieldDni.setColumns(20);

		btnBuscar = new JButton("Buscar");
		panel_1.add(btnBuscar);

		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.SOUTH);

		btnRegistrarTurno = new JButton("Registrar turno");
		panel_2.add(btnRegistrarTurno);

		btnCancelarTurno = new JButton("Cancelar turno");
		panel_2.add(btnCancelarTurno);

		btnConfiguracion = new JButton("Configuración");
		panel_2.add(btnConfiguracion);

		JScrollPane scrollPaneTurnos = new JScrollPane();
		add(scrollPaneTurnos, BorderLayout.CENTER);
		tableModelTurnos = new DefaultTableModel(null, COLUMNAS_TURNOS);
		tableTurnos = new JTable(tableModelTurnos);
		scrollPaneTurnos.setViewportView(tableTurnos);
	}

	public void setData(List<TurnoDTO> turnos) {
		for (TurnoDTO turno : turnos) {
			Object[] row = { turno.getIdTurno().toString(), turno.getNombreCliente(), turno.getApellidoCliente(),
					turno.getDniCliente(), turno.getFechaAlta().toString(), turno.getFechaProgramada().toString(),
					turno.getTelefonoCliente(), turno.getEmailCliente() };
			tableModelTurnos.addRow(row);
		}
	}

	public TurnoDTO getData() {
		int row = tableTurnos.getSelectedRow();
		if (tableTurnos.getSelectedRowCount() == 1) {
			TurnoDTO ret = new TurnoDTO();
			ret.setIdTurno(Integer.parseInt(tableModelTurnos.getValueAt(row, 0).toString()));
			ret.setNombreCliente(tableModelTurnos.getValueAt(row, 1).toString());
			ret.setApellidoCliente(tableModelTurnos.getValueAt(row, 2).toString());
			ret.setDniCliente(Integer.parseInt(tableModelTurnos.getValueAt(row, 3).toString()));
			ret.setFechaAlta(parse(tableModelTurnos.getValueAt(row, 4).toString()));
			ret.setFechaProgramada(parse(tableModelTurnos.getValueAt(row, 5).toString()));
			return ret;
		}
		return null;
	}

	public Integer getIdSelectedTurno() {
		int row = tableTurnos.getSelectedRow();
		int id;
		if (tableTurnos.getSelectedRowCount() == 1) {
			id = Integer.parseInt(tableModelTurnos.getValueAt(row, 0).toString());
			return id;
		}
		return null;
	}

	public void clear() {
		tableModelTurnos.setRowCount(0);
		tableModelTurnos.setColumnCount(0);
		tableModelTurnos.setColumnIdentifiers(COLUMNAS_TURNOS);
	}

	private Date parse(String str) {
		try {
			return new SimpleDateFormat("yyyy-mm-dd").parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setActionBuscar(ActionListener listener) {
		this.btnBuscar.addActionListener(listener);
	}

	public void setActionRegistrarTurno(ActionListener listener) {
		this.btnRegistrarTurno.addActionListener(listener);
	}

	public void setActionCancelarTurno(ActionListener listener) {
		this.btnCancelarTurno.addActionListener(listener);
	}

	public void setActionConfiguracion(ActionListener listener) {
		this.btnConfiguracion.addActionListener(listener);
	}

	public String getDniBusqueda() {
		return this.textFieldDni.getText();
	}

}
