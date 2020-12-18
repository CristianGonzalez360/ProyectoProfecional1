package business_logic;

import java.util.Date;
import java.util.List;

import business_logic.exceptions.ConflictException;
import dto.CaracteristicaVehiculoDTO;
import dto.CompraVehiculoDTO;
import dto.GarantiaVehiculoDTO;
import dto.VehiculoDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
import dto.temporal.AltaOrdenDeTrabajoDTO;
import dto.temporal.CompraVehiculoUsadoDTO;
import repositories.DaosFactory;
import repositories.FichaTecnicaVehiculoDao;
import repositories.IngresoOrdenDeTrabajoDao;
import services.SessionServiceImpl;

public class VehiculosController {

	private DaosFactory daos;

	public VehiculosController(DaosFactory daos) {
		assert daos != null;
		this.daos = daos;
	}

	public List<VehiculoDTO> readVehiculosUsados() {
		return daos.makeVehiculoDao().readVehiculosUsados();
	}

	public VehiculoDTO readVehiculoById(Integer idVehiculo) {
		return daos.makeVehiculoDao().readByID(idVehiculo);
	}

	public FichaTecnicaVehiculoDTO readByIdFichaTecnica(Integer idFicha) {
		return daos.makeFichaTecnicaVehiculoDao().readByID(idFicha);
	}
	
	public CaracteristicaVehiculoDTO readCaracteristicaVehiculoByIdVehiculo(Integer idVehiculo) {
		VehiculoDTO vehiculo = daos.makeVehiculoDao().readByID(idVehiculo);
		return daos.makeCaracteristicasVehiculoDao().readByID(vehiculo.getIdCaracteristicas());
	}

	public FichaTecnicaVehiculoDTO readFichaTecnicaByIdVehiculo(Integer idVehiculo) {
		VehiculoDTO vehiculo = daos.makeVehiculoDao().readByID(idVehiculo);
		return daos.makeFichaTecnicaVehiculoDao().readByID(vehiculo.getIdFichaTecnica());
	}

	public void saveVehiculoUsado(CompraVehiculoUsadoDTO compra) throws ConflictException{
		Integer idFicha = guardarFichaTecnica(compra);
		Integer idCarac = guardarCaracteristicas(compra);
		VehiculoDTO vehiculo = new VehiculoDTO();
		vehiculo.setIdCaracteristicas(idCarac);
		vehiculo.setIdFichaTecnica(idFicha);
		vehiculo.setColor(compra.getColor());
		vehiculo.setDisponible(true);
		vehiculo.setFamilia(compra.getFamilia());
		vehiculo.setIdSucursal(SessionServiceImpl.getInstance().getActiveSession().getIdSucursal());
		vehiculo.setLinea(compra.getLinea());
		vehiculo.setMarca(compra.getMarca());
		vehiculo.setPrecioVenta(Double.parseDouble(compra.getPrecioVenta()));
		vehiculo.setFechaIngreso(new Date());
		vehiculo.setUsado(true);
		daos.makeVehiculoDao().insert(vehiculo);
		Integer idVehiculo = daos.makeVehiculoDao().getIdMaximo();
		guardarGarantiaUsado(compra, idVehiculo);
		guardarCompra(compra, idVehiculo);
	}

	private void guardarCompra(CompraVehiculoUsadoDTO compra, int idVehiculo) {
		CompraVehiculoDTO c = new CompraVehiculoDTO();
		c.setFechaCompra(new Date());
		c.setIdUsuCompra(SessionServiceImpl.getInstance().getActiveSession().getIdSucursal());
		c.setIdVehiculo(idVehiculo);
		c.setPrecioCompra(Double.parseDouble(compra.getPrecio()));
		daos.makeVehiculoDao().registrarCompra(c);
	}

	private void guardarGarantiaUsado(CompraVehiculoUsadoDTO compra, int idVehiculo) {
		GarantiaVehiculoDTO garantia = new GarantiaVehiculoDTO();
		garantia.setIdVehiculo(idVehiculo);
		garantia.setAniosDeGarantia(1);
		garantia.setKilometrajeInicialDelVehiculo(Integer.parseInt(compra.getKilometraje()));
		daos.makeGarantiasVehiculosDao().insert(garantia);
	}

	private int guardarCaracteristicas(CompraVehiculoUsadoDTO compra) {
		CaracteristicaVehiculoDTO carac = new CaracteristicaVehiculoDTO();
		carac.setCantidadPuertas(compra.getCantidadPuertas());
		carac.setCilindrada(compra.getCilindrada());
		carac.setDireccion(compra.getDireccion());
		carac.setFrenosDelanteros(compra.getFrenosDelanteros());
		carac.setFrenosTraseros(compra.getFrenosTraseros());
		carac.setMotor(compra.getMotor());
		carac.setPotencia(compra.getPotencia());
		carac.setTorqueMaximo(compra.getTorqueMaximo());
		carac.setTransmision(compra.getTransmision());
		carac.setVolumenBaul(compra.getVolumenBaul());
		carac.setPrecio(compra.getPrecio());

		daos.makeCaracteristicasVehiculoDao().insert(carac);
		return daos.makeCaracteristicasVehiculoDao().getIdMaximo();
	}

	private int guardarFichaTecnica(CompraVehiculoUsadoDTO compra) {
		FichaTecnicaVehiculoDTO ficha = new FichaTecnicaVehiculoDTO();
		ficha.setColor(compra.getColor());
		ficha.setCombustion(compra.getCombustion());
		ficha.setKilometraje(Integer.parseInt(compra.getKilometraje()));
		ficha.setMarca(compra.getMarca());
		ficha.setModelo(Integer.parseInt(compra.getModelo()));
		ficha.setNroChasis(Integer.parseInt(compra.getNroChasis()));
		ficha.setNroMotor(Integer.parseInt(compra.getNroMotor()));
		ficha.setPatente(compra.getPatente());

		validar(ficha);

		daos.makeFichaTecnicaVehiculoDao().insert(ficha);
		return daos.makeFichaTecnicaVehiculoDao().getIdMaximo();
	}
	
	public void validar(FichaTecnicaVehiculoDTO ficha) {
		FichaTecnicaVehiculoDao fichasDao = daos.makeFichaTecnicaVehiculoDao();
		if (fichasDao.readByNroMotor(ficha.getNroMotor()) != null)
			throw new ConflictException("El nro. de motor ya está registrado.");
		IngresoOrdenDeTrabajoDao vehiculosDao = daos.makeVehiculoConOrdeDeTrabajoDao();
		if (vehiculosDao.readByPatente(ficha.getPatente()) != null)
			throw new ConflictException("La patente ya está registrada");
		if (fichasDao.readByNroChasis(ficha.getNroChasis()) != null)
			throw new ConflictException("El nro. de chasis ya está registrada.");
	}

	public Integer guardarCaracteristicaNueva(CaracteristicaVehiculoDTO caracteristica) {
		daos.makeCaracteristicasVehiculoDao().insert(caracteristica);
		return daos.makeCaracteristicasVehiculoDao().getIdMaximo();
	}

	public List<CaracteristicaVehiculoDTO> readAllCaracteristicas() {
		return daos.makeCaracteristicasVehiculoDao().readAll();
	}

	public Integer guardarFichaTecnicaNueva(FichaTecnicaVehiculoDTO ficha) {
		daos.makeFichaTecnicaVehiculoDao().insert(ficha);
		return daos.makeFichaTecnicaVehiculoDao().getIdMaximo();
	}

	public List<FichaTecnicaVehiculoDTO> readAllFichas() {
		return daos.makeFichaTecnicaVehiculoDao().readAll();
	}

	public Integer guardarVehiculoNuevo(VehiculoDTO vehiculo) {
		daos.makeVehiculoDao().insert(vehiculo);
		return daos.makeVehiculoDao().getIdMaximo();
	}

	public List<VehiculoDTO> readAllVehiculos() {
		return daos.makeVehiculoDao().readAll();
	}

	public void guardarGarantiaNuevo(GarantiaVehiculoDTO garantia) {
		daos.makeGarantiasVehiculosDao().insert(garantia);
	}

	public List<GarantiaVehiculoDTO> readAllGarantias() {
		return daos.makeGarantiasVehiculosDao().readAll();
	}

	public boolean isNroMotorExistente(Integer nroMotor) {// ==null no hay motor repetidos
		if (daos.makeFichaTecnicaVehiculoDao().readByNroMotor(nroMotor) != null) {
			return true;
		}
		return false;
	}

	public boolean isNroChasisExistente(Integer nroChasis) {// ==null no hay chasis repetidos
		if (daos.makeFichaTecnicaVehiculoDao().readByNroChasis(nroChasis) != null) {
			return true;
		}
		return false;
	}

	public void updateKilometraje(FichaTecnicaVehiculoDTO ficha, AltaOrdenDeTrabajoDTO ordenDeTrabajo) {
		FichaTecnicaVehiculoDTO target = daos.makeFichaTecnicaVehiculoDao().readByNroMotor(ficha.getNroMotor());
		Integer antiguo = ficha.getKilometraje();
		Integer actual = Integer.parseInt(ordenDeTrabajo.getKilometrajeActual());
		if(ordenDeTrabajo.getTipoDeTrabajo().equals("Garantia")) {
			if(actual <= antiguo) throw new ConflictException("El kilometraje debe ser mayor al registrado en la ficha tecnica");
		} else {
			if(actual < antiguo) throw new ConflictException("El kilometraje no debe ser menor al registrado en la ficha tecnica");
		}
		daos.makeFichaTecnicaVehiculoDao().updateKilometraje(target.getId(), actual);
	}
}
