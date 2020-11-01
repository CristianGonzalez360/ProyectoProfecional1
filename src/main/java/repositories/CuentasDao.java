package repositories;

import dto.CuentaDTO;

public interface CuentasDao extends GenericDao<CuentaDTO, Integer> {

	CuentaDTO readByCredentials(String email, String pass);

}
