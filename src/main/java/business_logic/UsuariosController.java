package business_logic;

import java.util.Date;
import java.util.List;

import business_logic.exceptions.ConflictException;
import dto.CuentaDTO;
import dto.DatosPersonalesDTO;
import dto.UsuarioDTO;
import repositories.DaosFactory;

public class UsuariosController {
	
	private static final String CONFLICTO_CUENTA = "Los datos de la cuenta no son válidos, ya hay un usuario con los mismos datos";
	
	private DaosFactory daos;

	public UsuariosController(DaosFactory daos) {
		super();
		this.daos = daos;
	}

	public UsuarioDTO readById(Integer id) {
		return daos.makeUsuariosDao().readByID(id);
	}

	public List<UsuarioDTO> readAll() {
		return daos.makeUsuariosDao().readAll();
	}

	public void save(UsuarioDTO dto) {
		CuentaDTO target = daos.makeCuentasDao().readByCredentials(dto.getCuenta().getNombreUsuario(), dto.getCuenta().getPassword());
		if(target != null) throw new ConflictException(CONFLICTO_CUENTA);
		new RegistradorDatosPersonalesService(daos.makeDatosPersonalesDao()).validate(dto.getDatos());
		dto.getCuenta().setFechaDeAlta(new Date());
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setCuenta(insertCuenta(dto.getCuenta()));
		usuario.setDatos(insertDatos(dto.getDatos()));
		daos.makeUsuariosDao().insert(usuario);
	}
		
	private CuentaDTO insertCuenta(CuentaDTO cuenta) {
		daos.makeCuentasDao().insert(cuenta);
		return daos.makeCuentasDao().readByNombreUsuario(cuenta.getNombreUsuario());
	}
	
	private DatosPersonalesDTO insertDatos(DatosPersonalesDTO datos) {
		daos.makeDatosPersonalesDao().insert(datos);
		return daos.makeDatosPersonalesDao().readByDni(datos.getDni());
	}
}
