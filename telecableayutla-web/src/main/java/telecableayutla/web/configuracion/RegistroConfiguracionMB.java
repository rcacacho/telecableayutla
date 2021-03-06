package telecableayutla.web.configuracion;

import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import telecableayutla.api.ejb.ConfiguracionBeanLocal;
import telecableayutla.api.entity.Configuracionpago;
import telecableayutla.web.utils.JsfUtil;
import telecableayutla.web.utils.SesionUsuarioMB;

/**
 *
 * @author rcacacho
 */
@ManagedBean(name = "registroConfiguracionMB")
@ViewScoped
public class RegistroConfiguracionMB implements Serializable {

    private static final Logger log = Logger.getLogger(RegistroConfiguracionMB.class);

    @EJB
    private ConfiguracionBeanLocal configuracionBean;

    private Configuracionpago configuracion;

    public RegistroConfiguracionMB() {
        configuracion = new Configuracionpago();
    }

    public void regresar() {
        JsfUtil.redirectTo("/configuracion/lista.xhtml");
    }

    public void saveConfiguracion() throws IOException {
        configuracion.setUsuariocreacion(SesionUsuarioMB.getUserName());
        Configuracionpago response = configuracionBean.saveConfiguracionPago(configuracion);
        if (response != null) {
            JsfUtil.addSuccessMessage("Configuración registrada exitosamente");
            configuracion = null;
            return;
        }
    }

    /*Metodos getters y setters*/
    public Configuracionpago getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(Configuracionpago configuracion) {
        this.configuracion = configuracion;
    }

}
