package business_logic;

import java.util.ArrayList;
import java.util.List;

import dto.DatosPersonalesDTO;
import dto.EntregaDeVehiculoDTO;
import dto.OrdenDeTrabajoDTO;
import repositories.DatosPersonalesDao;
import repositories.OrdenesDeTrabajoDao;
import repositories.PresupuestosDao;
import repositories.VehiculosConOrdenDeTrabajoDao;

public class EntregaDeVehiculoController {

	private DatosPersonalesDao datosClienteDao;
	private OrdenesDeTrabajoDao ordenesDeTrabajoDao;
	private PresupuestosDao presupuestosDao;
	private VehiculosConOrdenDeTrabajoDao vehiculosDao;

	public EntregaDeVehiculoController(DatosPersonalesDao datosPersonalesDao,
			OrdenesDeTrabajoDao ordenDeTrabajoDao, PresupuestosDao presupuestoDao,
			VehiculosConOrdenDeTrabajoDao vehiculoConOrdeDeTrabajoDao) {
		this.datosClienteDao = datosPersonalesDao;
		this.ordenesDeTrabajoDao = ordenDeTrabajoDao;
		this.presupuestosDao = presupuestoDao;
		this.vehiculosDao = vehiculoConOrdeDeTrabajoDao;
	}

	public DatosPersonalesDTO readDatosClienteByDni(Integer dni) {
		return datosClienteDao.readByDni(dni);
	}

	public List<EntregaDeVehiculoDTO> readAllOrdenesRealizadas() {
		List<EntregaDeVehiculoDTO> entregas = new ArrayList<>();
		List<OrdenDeTrabajoDTO> ordenesPagas = ordenesDeTrabajoDao.readAllOrdenesRealizadas();

		for (OrdenDeTrabajoDTO orden : ordenesPagas) {
			entregas.add(new EntregaDeVehiculoDTO(0000, orden.getIdOrdenTrabajo() + "", orden.getIdVehiculoOt() + "", orden.getTipoOrdeTrabajo() + "", orden.getTrabajoSolicitado(), orden.getTrabajoSujerido()));
			System.out.println(orden.toString());
		}
		return entregas;
	}

	public List<EntregaDeVehiculoDTO> readByDniCliente(Integer dniCliente) {
		// TODO Auto-generated method stub
		return null;
	}

	public void registrarEntregaById(Integer idEntrega) {
		// TODO Auto-generated method stub
	}

}
