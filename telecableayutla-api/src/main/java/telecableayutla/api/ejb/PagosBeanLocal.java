package telecableayutla.api.ejb;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import telecableayutla.api.entity.Detallepago;
import telecableayutla.api.entity.Pago;

/**
 *
 * @author rcacacho
 */
@Local
public interface PagosBeanLocal {

    List<Pago> listPagosByFechaInicioAndFin(Date fechainicio, Date fechafin);

    List<Pago> listCobros(Date fechainicio, Date fechafin);

    Pago savePago(Pago pago);

    Pago saveCobro(Pago pago);

    Pago findPagoByIdClienteAndAnioAndMes(Integer idCliente, Integer anio, String mes);

    List<Pago> listPagoByIdCliente(Integer idcliente);

    List<Pago> listPagoByAnio(Integer anio);

    List<Pago> listPagoByMes(String mes);

    List<Pago> listCobroByIdCliente(Integer idcliente);

    List<Pago> listCobroByAnio(Integer anio);

    List<Pago> listCobroByMes(String mes);

    Pago findPagoByIdPago(Integer idPago);

    List<Pago> listPagos();

    Detallepago saveDetallepago(Detallepago detalle);

    List<Detallepago> listDetallePago(Integer idPago);

    Pago updatePago(Pago pago);

    List<Pago> listPagosByIdMunicipio(Integer idmunicicpio);

    List<Pago> listPagosByInIdMunicipios();

    Pago findUltimoPago(Integer idcliente);

    Pago eliminarPago(Integer idpago);

    List<Pago> listPagoByIdClienteAndAnio(Integer idcliente, Integer anio);

}
