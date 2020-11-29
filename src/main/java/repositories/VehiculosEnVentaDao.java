package repositories;

import java.util.List;

import dto.VehiculoParaVentaDTO;
import dto.temporal.ConsultaVehiculoParaVentaDTO;

public interface VehiculosEnVentaDao extends GenericDao<VehiculoParaVentaDTO, Integer> {

	List<VehiculoParaVentaDTO> readByCriteria(ConsultaVehiculoParaVentaDTO consulta);

}
