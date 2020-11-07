package repositories;

import java.util.Date;

import dto.FacturaDTO;

public interface FacturasDao extends GenericDao<FacturaDTO, Integer>{

	boolean updateFechaCierrePorPago(Integer id, Date fecha);

	FacturaDTO readByOrdenDeTrabajoId(Integer id);

}
