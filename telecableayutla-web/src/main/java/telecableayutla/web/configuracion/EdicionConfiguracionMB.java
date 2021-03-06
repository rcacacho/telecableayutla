package telecableayutla.web.configuracion;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import telecableayutla.api.ejb.ConfiguracionBeanLocal;
import telecableayutla.api.entity.Configuracionpago;
import telecableayutla.web.utils.JsfUtil;

/**
 *
 * @author rcacacho
 */
@ManagedBean(name = "edicionConfiguracionMB")
@ViewScoped
public class EdicionConfiguracionMB implements Serializable {

    private static final Logger log = Logger.getLogger(EdicionConfiguracionMB.class);

    @EJB
    private ConfiguracionBeanLocal configuracionBen;

    private Integer idconfiguracionpago;
    private Configuracionpago configuracion;

    public void cargarDatos() {
        configuracion = configuracionBen.findConfiguracionPago(idconfiguracionpago);
    }

    public void actualizarConfiguracion() {
        Configuracionpago responseVerificacion = configuracionBen.actualizarConfiguracion(configuracion);
        if (responseVerificacion != null) {
            JsfUtil.addSuccessMessage("Configuración actualizada exitosamente");
        } else {
            JsfUtil.addErrorMessage("Ocurrio un error verificar datos");
        }
    }

    public void regresar() {
        JsfUtil.redirectTo("/configuracion/detalle.xhtml?idconfiguracionpago=" + idconfiguracionpago);
    }

    /*Metodos getters y setters*/
    public Integer getIdconfiguracionpago() {
        return idconfiguracionpago;
    }

    public void setIdconfiguracionpago(Integer idconfiguracionpago) {
        this.idconfiguracionpago = idconfiguracionpago;
    }

    public Configuracionpago getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(Configuracionpago configuracion) {
        this.configuracion = configuracion;
    }

}
