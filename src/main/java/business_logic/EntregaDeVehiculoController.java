package business_logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.ClienteDTO;
import dto.DatosPersonalesDTO;
import dto.EntregaDeVehiculoDTO;
import dto.FichaTecnicaVehiculoDTO;
import dto.OrdenDeTrabajoDTO;
import dto.VehiculoConOrdenDeTrabajoDTO;
import repositories.ClientesDao;
import repositories.DatosPersonalesDao;
import repositories.FichaTecnicaVehiculoDao;
import repositories.OrdenesDeTrabajoDao;
import repositories.PresupuestosDao;
import repositories.VehiculosConOrdenDeTrabajoDao;

public class EntregaDeVehiculoController {

	private ClientesDao clientesDao;
	private DatosPersonalesDao datosPersonalesDao;
	private OrdenesDeTrabajoDao ordenesDeTrabajoDao;
	private PresupuestosDao presupuestosDao;
	private VehiculosConOrdenDeTrabajoDao vehiculosDao;
	private FichaTecnicaVehiculoDao fichaTecnicaDao;

	private List<OrdenDeTrabajoDTO> ordenesRealizadas;

	public EntregaDeVehiculoController(ClientesDao clienteDao, DatosPersonalesDao datosPersonalesDao,
			OrdenesDeTrabajoDao ordenDeTrabajoDao, PresupuestosDao presupuestoDao,
			VehiculosConOrdenDeTrabajoDao vehiculoConOrdeDeTrabajoDao, FichaTecnicaVehiculoDao fichaTecnicaDao) {
		this.clientesDao = clienteDao;
		this.datosPersonalesDao = datosPersonalesDao;
		this.ordenesDeTrabajoDao = ordenDeTrabajoDao;
		this.presupuestosDao = presupuestoDao;
		this.vehiculosDao = vehiculoConOrdeDeTrabajoDao;
		this.fichaTecnicaDao = fichaTecnicaDao;
	}

	public DatosPersonalesDTO readDatosClienteByDni(Integer dni) {
		return datosPersonalesDao.readByDni(dni);
	}

	public List<EntregaDeVehiculoDTO> readAllOrdenesRealizadas() {
		List<EntregaDeVehiculoDTO> entregas = new ArrayList<>();

		ordenesRealizadas = ordenesDeTrabajoDao.readAllOrdenesRealizadas();

		for (OrdenDeTrabajoDTO orden : ordenesRealizadas) {//Ordenes de trabajo con presupuesto finalizado
			Integer idOT = orden.getIdOrdenTrabajo();
			Integer idVehiculo = orden.getIdVehiculoOt();

			VehiculoConOrdenDeTrabajoDTO vehiculoConOT = vehiculosDao.readByID(idVehiculo);
			Integer idFichaTecnica = vehiculoConOT.getIdFichaTecnica();
			Integer idCliente = vehiculoConOT.getIdCliente();

			FichaTecnicaVehiculoDTO fichaTecnica = fichaTecnicaDao.readByID(idFichaTecnica);

			ClienteDTO cliente = clientesDao.readByID(idCliente);
			Integer idDatos = cliente.getIdDatosPersonales();

			DatosPersonalesDTO datosPersonales = datosPersonalesDao.readByID(idDatos);

			Integer dniCliente = datosPersonales.getDni();
			String nombreCompleto = String.format("%s %s", datosPersonales.getNombreCompleto(),
					datosPersonales.getApellido());
			String marcaAuto = fichaTecnica.getMarca();
			String modeloAuto = String.valueOf(fichaTecnica.getModelo());
			String colorAuto = fichaTecnica.getColor();
			String patenteAuto = vehiculoConOT.getPatente();

			
			EntregaDeVehiculoDTO nuevaEntrega = new EntregaDeVehiculoDTO(dniCliente, nombreCompleto, marcaAuto, modeloAuto, colorAuto,
					patenteAuto, idOT);
			entregas.add(nuevaEntrega);
		}
		return entregas;
	}

	public List<EntregaDeVehiculoDTO> readByDniCliente(Integer dniCliente) {
		List<EntregaDeVehiculoDTO> ordenesParaEntregar = readAllOrdenesRealizadas();
		List<EntregaDeVehiculoDTO> entregas  = new ArrayList<>();
				
		for(EntregaDeVehiculoDTO entrega : ordenesParaEntregar) {
			if(entrega.getDniCliente() != null && entrega.getDniCliente().equals(dniCliente)) {
				entregas.add(entrega);
			}
		}
		
		return entregas;
	}

	public void registrarEntregaById(Integer idOt) {
		assert idOt != null;
		OrdenDeTrabajoDTO orden = ordenesDeTrabajoDao.readByID(idOt);
		orden.setFechaEntregado(new Date());
		ordenesDeTrabajoDao.update(orden);
	}

	public OrdenDeTrabajoDTO readByID(Integer idOt) {
		return ordenesDeTrabajoDao.readByID(idOt);
	}

}
