package telecableayutla.api.ejb;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import telecableayutla.api.entity.Compra;
import telecableayutla.api.entity.Proveedor;

/**
 *
 * @author rcacacho
 */
@Local
public interface ComprasBeanLocal {
    
    List<Compra> listCompra();

    Compra saveCompra(Compra compra);

    Compra findCompraById(Integer idcompra);

    Proveedor saveProveedor(Proveedor proveedor);

    List<Compra> listCompraByFechaInicio(Date fechaInicio);

    List<Compra> listCompraByFechaFin(Date fechaFin);

    List<Compra> listCompraByFechaInicioFechaFin(Date fechaInicio, Date fechaFin);
    
}
