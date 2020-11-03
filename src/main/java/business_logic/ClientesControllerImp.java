package business_logic;

import java.util.List;

import business_logic.exceptions.ConflictException;
import business_logic.exceptions.NotFoundException;
import dto.ClienteDTO;
import repositories.ClientesDao;

public class ClientesControllerImp implements ClientesController {
	
	private static final String CONFLICT_EXCEPTION = "Ya existe un cliente registrado con este DNI";

	private static final String NOT_FOUND_EXCEPTION = "Cliente no encontrado";
	
	private ClientesDao dao;
	
	public ClientesControllerImp(ClientesDao dao) {
		assert dao!=null;
		this.dao = dao;
	}

	@Override
	public void save(ClienteDTO clienteDTO) {
		assert clienteDTO != null;
		ClienteDTO target = dao.readByDNI(clienteDTO.getDatosPersonalesDTO().getDni());
		if (target != null)
			throw new ConflictException(CONFLICT_EXCEPTION);
		dao.insert(clienteDTO);
	}

	@Override
	public void update(ClienteDTO clienteDTO) {
		assert clienteDTO != null;
		ClienteDTO target = dao.readByDNI(clienteDTO.getDatosPersonalesDTO().getDni());
		if (target != null)
			throw new ConflictException(CONFLICT_EXCEPTION);
		if (dao.readByID(clienteDTO.getIdCliente()) == null)
			throw new NotFoundException(NOT_FOUND_EXCEPTION);
		dao.update(clienteDTO);
	}

	@Override
	public void deleteById(Integer id) {
		assert id != null;
		dao.deleteById(id);
	}

	@Override
	public List<ClienteDTO> readAll() {
		return dao.readAll();
	}

	@Override
	public ClienteDTO readByDni(int dni) {
		ClienteDTO ret = dao.readByDNI(dni);
		if(ret == null) {
			throw new NotFoundException(NOT_FOUND_EXCEPTION);
		}
		return ret;
	}

}
