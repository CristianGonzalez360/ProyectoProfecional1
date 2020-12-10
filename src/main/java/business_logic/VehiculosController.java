package business_logic;

import java.util.Date;
import java.util.List;

import dto.CaracteristicaVehiculoDTO;
import dto.CompraVehiculoDTO;
import dto.VehiculoDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
import dto.temporal.CompraVehiculoUsadoDTO;
import repositories.DaosFactory;
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
	
	public CaracteristicaVehiculoDTO readCaracteristicaVehiculoByIdVehiculo(Integer idVehiculo) {
		VehiculoDTO vehiculo = daos.makeVehiculoDao().readByID(idVehiculo);
		return daos.makeCaracteristicasVehiculoDao().readByID(vehiculo.getIdCaracteristicas());
	}

	public FichaTecnicaVehiculoDTO readFichaTecnicaByIdVehiculo(Integer idVehiculo) {
		return daos.makeFichaTecnicaVehiculoDao().readByID(idVehiculo);
	}

	public void saveVehiculoUsado(CompraVehiculoUsadoDTO compra) {
		Integer idCarac = guardarCaracteristicas(compra);
		Integer idFicha = guardarFichaTecnica(compra);
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
		guardarCompra(compra, idVehiculo);
	}
	
	private void guardarCompra(CompraVehiculoUsadoDTO compra, int idVehiculo) {
		CompraVehiculoDTO c = new CompraVehiculoDTO();
		c.setFechaCompra(new Date());
		c.setIdUsuCompra(SessionServiceImpl.getInstance().getActiveSession().getIdSucursal());
		c.setIdVehiculo(idVehiculo);
		c.setPrecioCompra(Double.parseDouble(compra.getPrecio()));
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
		
		daos.makeFichaTecnicaVehiculoDao().insert(ficha);
		return daos.makeFichaTecnicaVehiculoDao().getIdMaximo();
	}
	
}
