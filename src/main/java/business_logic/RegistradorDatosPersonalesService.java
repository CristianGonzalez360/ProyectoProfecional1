package business_logic;

import business_logic.exceptions.ConflictException;
import dto.DatosPersonalesDTO;
import repositories.DatosPersonalesDao;

public class RegistradorDatosPersonalesService {
	
	private static final String CONFLICT_EMAIL = "El email está en uso por otro contacto.";

	private static final String CONFLICT_TELEFONO = "El telefono está en uso por otro contacto.";

	private static final String CONFLICT_DNI = "El dni está en uso por otro cliente";

	private DatosPersonalesDao datosPersonalesDao;

	public RegistradorDatosPersonalesService(DatosPersonalesDao datosDao) {
		this.datosPersonalesDao = datosDao;
	}
	
	public void validate(DatosPersonalesDTO datos) {
		if (datosPersonalesDao.readByDni(datos.getDni()) != null)
			throw new ConflictException(CONFLICT_DNI);
		if (datosPersonalesDao.readByTelefono(datos.getTelefono()) != null)
			throw new ConflictException(CONFLICT_TELEFONO);
		if (datosPersonalesDao.readByEmail(datos.getEmail()) != null)
			throw new ConflictException(CONFLICT_EMAIL);
	}
}
