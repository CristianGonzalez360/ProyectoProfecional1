package repositories;

import java.util.Date;
import java.util.List;

import dto.CompraVehiculoDTO;

public interface CompraVehiculoDao extends GenericDao<CompraVehiculoDTO, Integer> {

	List <CompraVehiculoDTO> readFechas(Date desde, Date hasta);

}
