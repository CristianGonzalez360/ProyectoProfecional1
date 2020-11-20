package services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSenderService {

	private final static String correoRemitente = "";
	private final static String contraseñaRemitente = "";

	private final static String asuntoSatisfaccion = "Encuesta de Satisfaccion - Concesionario Larusso";
	private final static String mensajeSatisfaccion = "ENCUESTA DE SATISFACIÓN.\n\n"
			+ "Ayudanos a mejorar completando esta breve encuesta:\n\n\n"
			+ "https://docs.google.com/forms/d/e/1FAIpQLSd2f7w-q2bkI3OzbRt5zvUe_LubL_Nt8V0QS_eSgFf-G5WF5g/viewform?usp=sf_link\n\n\n"
			+ "Muchas Gracias.";

	public static boolean enviarMailDeSatisfaccion(String correoDestinatario) {

		Properties props = System.getProperties();

		props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
		props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
		props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google
		props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		Transport transport;

		try {
			message.setFrom(new InternetAddress(correoRemitente));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestinatario));
			message.setSubject(asuntoSatisfaccion);
			message.setText(mensajeSatisfaccion);

			transport = session.getTransport("smtp");
			transport.connect(correoRemitente, contraseñaRemitente);
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();

		} catch (Exception me) {
			return false;
		}
		return true;
	}
}
