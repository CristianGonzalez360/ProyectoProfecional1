package repositories;

import java.util.List;

import dto.ConsultaVehiculoDTO;
import dto.VehiculoDTO;

public interface VehiculoDao extends GenericDao<VehiculoDTO, Integer> {

	public List<VehiculoDTO> readNuevosDisponibles();
	public List<VehiculoDTO> readUsados();
	public List<VehiculoDTO> readUsadosYNuevosDisponibles();
	public List<VehiculoDTO> readByCriteria(ConsultaVehiculoDTO consulta);
}
