package repositories;

import dto.UserDTO;

public interface UsuariosDao extends GenericDao<UserDTO, Integer>{

	UserDTO readByCredentials(String email, String password);
}
