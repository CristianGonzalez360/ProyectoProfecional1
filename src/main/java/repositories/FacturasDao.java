package repositories;

import java.util.Date;
import java.util.List;

import dto.FacturaDTO;

public interface FacturasDao extends GenericDao<FacturaDTO, Integer>{

	boolean updateFechaCierrePorPago(Integer id, Date fecha);

	List<FacturaDTO> readByOrdenDeTrabajoId(Integer id);

	List<FacturaDTO> readByFactura(Integer id);
	
	FacturaDTO readById(Integer id);

}
