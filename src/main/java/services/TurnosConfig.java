package services;

import java.io.File;
import java.io.IOException;

public class TurnosConfig {

	private static PropertiesServiceImpl propertyService;
	private final static String pathArchivoConfiguracion = "conf/turnos.properties";

	private static Integer TURNOS_POR_DIA = 0;

	public static int getTurnosPorDia() {
		cargarDatosDeRemitente();

		return TURNOS_POR_DIA;
	}

	private static String getAbsolutePath() {
		File file = new File(pathArchivoConfiguracion);
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return file.getAbsolutePath();
	}

	private static boolean cargarDatosDeRemitente() {
		try {
			TURNOS_POR_DIA = Integer.valueOf(propertyService.readProperty("cantidad"));
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
