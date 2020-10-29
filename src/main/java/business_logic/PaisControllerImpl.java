package business_logic;

import java.util.List;

import business_logic.exceptions.ConflictException;
import business_logic.exceptions.NotFoundException;
import dto.PaisDTO;
import repositories.PaisDao;

public class PaisControllerImpl implements PaisController {

	private static final String NOT_FOUND_EXCEPTION = "Pais no encontrado";
	
	private static final String CONFLICT_EXCEPTION = "No se puede utilizar un nombre de pais que ya está en uso";
	
	private PaisDao dao;
	
	public PaisControllerImpl(PaisDao dao) {
		assert dao != null;
		this.dao = dao;
	}	
	
	@Override
	public void save(PaisDTO paisDTO) {
		assert paisDTO != null;
		PaisDTO target = dao.readByName(paisDTO.getNombre());
		if(target != null) throw new ConflictException(CONFLICT_EXCEPTION);
		dao.insert(paisDTO);
	}

	@Override
	public void update(PaisDTO paisDTO) {
		assert paisDTO != null;
		PaisDTO target = dao.readByName(paisDTO.getNombre());
		if(target != null) throw new ConflictException(CONFLICT_EXCEPTION);
		if(dao.readByID(paisDTO.getId()) == null) throw new NotFoundException(NOT_FOUND_EXCEPTION);
		dao.update(paisDTO);
	}
	
	@Override
	public void deleteById(Integer id) {
		assert id != null;
		dao.deleteById(id);
	}
	
	@Override
	public List<PaisDTO> readAll() {
		return dao.readAll();
	}
}
