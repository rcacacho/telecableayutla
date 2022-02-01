package telecableayutla.web.cobro;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import telecableayutla.api.ejb.PagosBeanLocal;
import telecableayutla.api.entity.Pago;
import telecableayutla.web.utils.JsfUtil;

/**
 *
 * @author elfo_
 */
@ManagedBean(name = "detalleCobroMB")
@ViewScoped
public class DetalleCobroMB implements Serializable {

    private static final Logger log = Logger.getLogger(DetalleCobroMB.class);

    @EJB
    private PagosBeanLocal pagosBean;

    private Integer idCobro;
    private Pago pago;

    public void cargarDatos() {
        pago = pagosBean.findPagoByIdPago(idCobro);
    }

    public void regresar() {
        JsfUtil.redirectTo("/cobros/lista.xhtml");
    }

    /*Metodos getters y setters*/
    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public Integer getIdCobro() {
        return idCobro;
    }

    public void setIdCobro(Integer idCobro) {
        this.idCobro = idCobro;
    }

}

