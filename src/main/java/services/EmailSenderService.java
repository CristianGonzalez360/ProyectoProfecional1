package services;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSenderService {

	private PropertiesServiceImpl propertyService;
	private final static String pathArchivoEncuestas = "conf/smtp.properties";

	private String correoRemitente = "";
	private String contraseñaRemitente = "";
	private String fechaRecordatorio = "";

	private Properties props;

	public EmailSenderService() {
		props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
		props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
		props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google
		props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
	}

	public boolean enviarMailRecordatorio(String correoDestinatario) {
		if (!cargarArchivoDePropiedades())
			return false;

		if (!cargarDatosDeRemitente())
			return false;
		
		Calendar hoy = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fechaHoy = sdf.format(hoy.getTime());
		
		if(!(fechaRecordatorio.equals(fechaHoy)) || fechaRecordatorio.length()==0) {//fecha del archivo vacia
			Session session = Session.getDefaultInstance(props);
			MimeMessage message = new MimeMessage(session);
			Transport transport;
	
			try {
				message.setFrom(new InternetAddress(correoRemitente));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestinatario));
				message.setSubject(Recordatorio.asunto);
				message.setContent(Recordatorio.recordatorioHYML, "text/html");
	
				transport = session.getTransport("smtp");
				transport.connect(correoRemitente, contraseñaRemitente);
				transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
				transport.close();
				establecerFecha();
			} catch (Exception me) {
				return false;
			}
		}
		return true;
	}

	public boolean enviarMailDeSatisfaccion(String correoDestinatario) {
		if (!cargarArchivoDePropiedades())
			return false;

		if (!cargarDatosDeRemitente())
			return false;

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		Transport transport;

		try {
			message.setFrom(new InternetAddress(correoRemitente));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestinatario));
			message.setSubject(Encuesta.asunto);
			message.setContent(Encuesta.encuestaHYML, "text/html");

			transport = session.getTransport("smtp");
			transport.connect(correoRemitente, contraseñaRemitente);
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();

		} catch (Exception me) {
			return false;
		}
		return true;
	}

	private boolean cargarArchivoDePropiedades() {
		String path = getAbsolutePath();
		if (path.isEmpty())
			return false;

		this.propertyService = new PropertiesServiceImpl(path);
		return true;
	}

	private String getAbsolutePath() {
		File file = new File(pathArchivoEncuestas);
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return file.getAbsolutePath();
	}

	private boolean cargarDatosDeRemitente() {
		try {
			correoRemitente = propertyService.readProperty("email");
			contraseñaRemitente = propertyService.readProperty("password");
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	private void establecerFecha() {
		Map<String, String> map = new HashMap<String, String>();
		String fechaHoy = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		
		try {
			map.put("email",propertyService.readProperty("email"));
			map.put("password",propertyService.readProperty("password"));
			map.put("fechaRecordatorio", fechaHoy);
			propertyService.updateValues(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
