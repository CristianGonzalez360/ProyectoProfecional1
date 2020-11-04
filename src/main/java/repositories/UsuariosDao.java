package repositories;

import dto.UsuarioDTO;

public interface UsuariosDao extends GenericDao<UsuarioDTO, Integer> {

	UsuarioDTO readByCredentials(String email, String password);
}
