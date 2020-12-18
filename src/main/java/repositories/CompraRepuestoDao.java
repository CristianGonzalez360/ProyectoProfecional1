package repositories;

import java.util.Date;
import java.util.List;

import dto.CompraRepuestoDTO;

public interface CompraRepuestoDao extends GenericDao<CompraRepuestoDTO, Integer> {

	List <CompraRepuestoDTO> readFechas(Date desde, Date hasta);

}
