package presentacion.views.gerente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dto.validators.StringValidator;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class FormRegistroAseguradoraView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textAseguradora;
	private JTextField textNroPoliza;
	private JButton btnSalvar;
	
	private static FormRegistroAseguradoraView instance;

	public static FormRegistroAseguradoraView getInstance() {
		if (instance == null)
			instance = new FormRegistroAseguradoraView();
		return instance;
	}

	public FormRegistroAseguradoraView() {
		setBounds(100, 100, 450, 300);
		setTitle("Registro aseguradora");
		setModal(true);
		setResizable(false);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblAseguradora = new JLabel("Aseguradora");
		lblAseguradora.setBounds(56, 27, 94, 14);
		contentPanel.add(lblAseguradora);
		
		JLabel lblNmeroDePoliza = new JLabel("Número de poliza");
		lblNmeroDePoliza.setBounds(56, 68, 94, 14);
		contentPanel.add(lblNmeroDePoliza);
		
		textAseguradora = new JTextField();
		textAseguradora.setBounds(157, 24, 131, 20);
		contentPanel.add(textAseguradora);
		textAseguradora.setColumns(10);
		
		textNroPoliza = new JTextField();
		textNroPoliza.setBounds(160, 65, 131, 20);
		contentPanel.add(textNroPoliza);
		textNroPoliza.setColumns(10);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
		btnSalvar = new JButton("OK");
		btnSalvar.setActionCommand("OK");
		buttonPane.add(btnSalvar);
		getRootPane().setDefaultButton(btnSalvar);
	
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				clearData();
			}
		});
			
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				clearData();
			}
		});
	}
	
	public void display() {
		clearData();
		setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void setActionSave(ActionListener listener) {
		this.btnSalvar.addActionListener(listener);
	}
	
	public void close() {
		clearData();
		setVisible(false);
	}
	
	public List<String> aseguradoraValida() {
		List<String> errors = new LinkedList<>();
		errors.addAll(new StringValidator(this.textAseguradora.getText()).notBlank("La Aseguradora es obligatoria.").validate());
		errors.addAll(new StringValidator(this.textNroPoliza.getText()).number("El número de poliza debe ser un número").validate());
		
		return errors;
	}
	
	public String getAseguradora() {
		return textAseguradora.getText();
	}
	
	public String getNroPoliza() {
		return textNroPoliza.getText();
	}
	
	public void clearData() {
		textAseguradora.setText("");
		textNroPoliza.setText("");
	}
}
