package telecableayutla.bussines.ejb.imp;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import org.apache.log4j.Logger;
import telecableayutla.api.ejb.CatalogoBeanLocal;
import telecableayutla.api.ejb.ClienteBeanLocal;
import telecableayutla.api.ejb.PagosBeanLocal;
import telecableayutla.api.entity.Cliente;
import telecableayutla.api.entity.Detallepago;
import telecableayutla.api.entity.Pago;
import telecableayutla.api.entity.Tipopago;
import telecableayutla.api.enums.TipoPagoEnum;

/**
 *
 * @author rcacacho
 */
@Singleton
public class GeneracionCobro {

    private static final Logger log = Logger.getLogger(GeneracionCobro.class);

    @EJB
    private ClienteBeanLocal clienteBean;
    @EJB
    private PagosBeanLocal pagoBean;
    @EJB
    private CatalogoBeanLocal catalogoBean;

    @Schedule(month = "*", dayOfMonth = "1", hour = "1", persistent = false)
    //@Schedule(second = "0", minute = "40", hour = "*", persistent = false)
    public void registroCobro() {
        List<Cliente> response = clienteBean.ListClientes();
        if (response.size() > 0) {
            for (Cliente cc : response) {
                Date fechaActual = new Date();
                LocalDate localDate = fechaActual.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                Integer month = localDate.getMonthValue();
                Integer an = localDate.getYear();
                String mes = "";

                if (month.equals(1)) {
                    mes = "enero";
                } else if (month.equals(2)) {
                    mes = "febrero";
                } else if (month.equals(3)) {
                    mes = "marzo";
                } else if (month.equals(4)) {
                    mes = "abril";
                } else if (month.equals(5)) {
                    mes = "mayo";
                } else if (month.equals(6)) {
                    mes = "junio";
                } else if (month.equals(7)) {
                    mes = "julio";
                } else if (month.equals(8)) {
                    mes = "agosto";
                } else if (month.equals(9)) {
                    mes = "septiembre";
                } else if (month.equals(10)) {
                    mes = "octubre";
                } else if (month.equals(11)) {
                    mes = "noviembre";
                } else if (month.equals(12)) {
                    mes = "diciembre";
                }

                Pago responsePago = pagoBean.findPagoByIdClienteAndAnioAndMes(cc.getIdcliente(), an, mes);
                if (responsePago == null) {
                    Tipopago tipo = catalogoBean.findTipoPago(TipoPagoEnum.COBRO.getId());

                    Pago cobro = new Pago();
                    cobro.setAnio(an);
                    cobro.setMes(mes);
                    cobro.setTotal(cc.getIdconfiguracionpago().getValor());
                    cobro.setIdtipopago(tipo);
                    cobro.setUsuariocreacion("Cobro automatico");
                    cobro.setIdcliente(cc);
                    Pago responseCobro = pagoBean.saveCobro(cobro);

                    Detallepago detalle = new Detallepago();
                    detalle.setIdpago(cobro);
                    detalle.setMontocobrado(cc.getIdconfiguracionpago().getValor());
                    detalle.setUsuariocreacion("Cobro automatico");
                    detalle.setTotal(cc.getIdconfiguracionpago().getValor());
                    Detallepago responseDetalle = pagoBean.saveDetallepago(detalle);

                }
            }
        }
    }

    public void registroCobroMesAtrasado() {
        Date fechaActual = new Date();
        LocalDate localDate = fechaActual.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    
        List<Pago> listPago = pagoBean.listPagos();
        if (listPago != null){
            
        }
    }
}
