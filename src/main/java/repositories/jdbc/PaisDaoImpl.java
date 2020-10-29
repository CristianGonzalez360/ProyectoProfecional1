package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.PaisDTO;
import repositories.PaisDao;

public class PaisDaoImpl extends GenericJdbcDao<PaisDTO> implements PaisDao {

	static final int FIRST = 0;
	static final String insert = "INSERT INTO Pais (PaisNombre) VALUES (?)";
	static final String update = "UPDATE Pais SET PaisNombre = ? WHERE PaisID = ?";
	static final String delete = "DELETE FROM Pais WHERE PaisID = ?";
	static final String readall = "SELECT * FROM Pais";
	static final String readbyid = "SELECT * FROM Pais WHERE PaisID = ?";
	static final String readByName = "SELECT * FROM Pais WHERE PaisNombre = ?";
		
	public PaisDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean update(PaisDTO dto) {
		return getTemplate()
				.query(update)
				.param(dto.getNombre())
				.param(dto.getId())
				.excecute();
	}

	@Override
	public boolean insert(PaisDTO dto) {
		return getTemplate()
				.query(insert)
				.param(dto.getNombre())
				.excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		return getTemplate()
				.query(delete)
				.param(id)
				.excecute();
	}

	@Override
	public PaisDTO readByID(Integer id) {
		return getTemplate()
				.query(readbyid)
				.param(id)
				.excecute(getMapper())
				.get(FIRST);
	}

	@Override
	public List<PaisDTO> readAll() {
		return getTemplate()
				.query(readall)
				.excecute(getMapper());
	}
	
	@Override
	public PaisDTO readByName(String nombre) {
		List<PaisDTO> res = getTemplate()
				.query(readByName)
				.param(nombre)
				.excecute(getMapper());
		if(res.isEmpty()) return null;
		return res.get(FIRST);
	}
	
	@Override
	protected Mapper<PaisDTO> getMapper() {
		return new Mapper<PaisDTO>() {
			@Override
			public PaisDTO map(Object[] obj) {
				return new PaisDTO((Integer) obj[0], (String) obj[1]);
			}
		};
	}	
}
