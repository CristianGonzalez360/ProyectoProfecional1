package repositories;

import java.util.Date;
import java.util.List;

import dto.FacturaDTO;

public interface FacturasDao extends GenericDao<FacturaDTO, Integer>{

	boolean updateFechaCierrePorPago(Integer id, Date fecha);

	FacturaDTO readByOrdenDeTrabajoId(Integer id);
	
	List<FacturaDTO> readAll();

	boolean updatePorPago(Integer id);

	List<FacturaDTO> readByFactura(Integer id);

}
