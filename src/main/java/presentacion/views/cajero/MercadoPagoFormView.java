package presentacion.views.cajero;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import dto.AltaClienteDTO;
import dto.ClienteDTO;
import dto.DatosPersonalesDTO;
import dto.TarjetaCreditoDTO;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class MercadoPagoFormView extends JDialog {

	private static final long serialVersionUID = -6293870932970697649L;
	private final JPanel contentPanel = new JPanel();

	private static MercadoPagoFormView instance;
	private JButton btnSalvar;

	private MercadoPagoFormView() {
		setBounds(100, 100, 461, 536);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNumeroTarjeta = new JLabel("Escane el QR:");
		lblNumeroTarjeta.setBounds(14, 3, 105, 14);
		panel.add(lblNumeroTarjeta);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(73, 28, 316, 420);
		panel.add(panel_2);

		JPanel panel_1 = new JPanel();
		contentPanel.add(panel_1, BorderLayout.SOUTH);

		btnSalvar = new JButton("Confirmar recepcion");
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
		
		Imagen Imagen = new Imagen();
		panel_2.add(Imagen);
		panel_2.setLayout(null);
		panel_2.repaint();
	
	}

	public static MercadoPagoFormView getInstance() {
		if (instance == null) {
			instance = new MercadoPagoFormView();
		}
		return instance;
	}

	public class Imagen extends javax.swing.JPanel {
		 
		/**
		 * 
		 */
		private static final long serialVersionUID = -2207187888506087014L;

		public Imagen() {
		this.setSize(300, 400); 
		}
		 			 
		public void paint(Graphics grafico) {
		Dimension height = getSize();
		ImageIcon Img = new ImageIcon(getClass().getResource("/icons/qr.png")); 
		 
		grafico.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);
		 
		setOpaque(false);
		super.paintComponent(grafico);
		}
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