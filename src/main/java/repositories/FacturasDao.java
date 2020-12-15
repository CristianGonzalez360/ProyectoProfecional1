package repositories;

import java.util.Date;
import java.util.List;

import dto.taller.FacturaDTO;

public interface FacturasDao extends GenericDao<FacturaDTO, Integer>{

	boolean updateFechaCierrePorPago(Integer id, Date fecha);

	List<FacturaDTO> readByOrdenDeTrabajoId(Integer id);

	List<FacturaDTO> readByFactura(Integer id);
	
	FacturaDTO readById(Integer id);
	
	List<FacturaDTO> readAll();

	boolean updatePorPago(Integer id);

	void insertFacturaCarrito(FacturaDTO facturaCarrito);

	List<FacturaDTO> readByDates(Date desde, Date hasta);

}
