package business_logic;

import java.util.Date;

import business_logic.exceptions.ConflictException;
import dto.ClienteDTO;
import dto.DatosPersonalesDTO;
import repositories.ClientesDao;
import repositories.DatosPersonalesDao;

public class ClientesController {

	private static final String CONFLICT_EMAIL = "El email está en uso por otro contacto.";

	private static final String CONFLICT_TELEFONO = "El telefono está en uso por otro contacto.";

	private static final String CONFLICT_DNI = "El dni está en uso por otro cliente";

	private ClientesDao clientesDao;

	private DatosPersonalesDao datosPersonalesDao;

	public ClientesController(ClientesDao clientes, DatosPersonalesDao datosDao) {
		assert clientes != null;
		assert datosDao != null;
		this.clientesDao = clientes;
		this.datosPersonalesDao = datosDao;
	}

	public ClienteDTO readByDni(Integer dni) {
		assert dni != null;
		return clientesDao.readByDNI(dni);
	}

	public void save(ClienteDTO cliente) {
		assert cliente != null;
		if (clientesDao.readByDNI(cliente.getDatosPersonalesDTO().getDni()) != null)
			throw new ConflictException(CONFLICT_DNI);
		if (clientesDao.readByTelefono(cliente.getDatosPersonalesDTO().getTelefono()) != null)
			throw new ConflictException(CONFLICT_TELEFONO);
		if (clientesDao.readByEmail(cliente.getDatosPersonalesDTO().getEmail()) != null)
			throw new ConflictException(CONFLICT_EMAIL);
		datosPersonalesDao.insert(cliente.getDatosPersonalesDTO());
		cliente.setFechaAltaCliente(new Date());
		DatosPersonalesDTO datosDto = datosPersonalesDao.readByDni(cliente.getDatosPersonalesDTO().getDni());
		cliente.setIdDatosPersonales(datosDto.getId());
		clientesDao.insert(cliente);
	}
}