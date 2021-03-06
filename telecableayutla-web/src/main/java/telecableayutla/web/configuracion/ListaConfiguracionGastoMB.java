package telecableayutla.web.configuracion;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import telecableayutla.api.ejb.ConfiguracionBeanLocal;
import telecableayutla.api.entity.Tipocompra;
import telecableayutla.web.utils.JsfUtil;
import telecableayutla.web.utils.SesionUsuarioMB;

/**
 *
 * @author rcacacho
 */
@ManagedBean(name = "listaConfiguracionGastoMB")
@ViewScoped
public class ListaConfiguracionGastoMB implements Serializable {

    private static final Logger log = Logger.getLogger(ListaConfiguracionGastoMB.class);

    @EJB
    private ConfiguracionBeanLocal configuracionBean;

    private List<Tipocompra> listTipoGasto;

    @PostConstruct
    public void cargarDatos() {
        listTipoGasto = configuracionBean.listConfiguracionGasto();
    }

    public void linkRegistro() {
        JsfUtil.redirectTo("/configuracion/compra/registro.xhtml");
    }

    public void detalle(Integer id) {
        JsfUtil.redirectTo("/configuracion/compra/detalle.xhtml?idtipocompra=" + id);
    }

    public void eliminar(Integer id) throws IOException {
        String usuario = SesionUsuarioMB.getUserName();
        Tipocompra response = configuracionBean.deleteConfiguracionGasto(id, usuario);
        if (response != null) {
            JsfUtil.addSuccessMessage("Se elimino la configuración exitosamente");
            cargarDatos();
            return;
        }

        JsfUtil.addErrorMessage("Sucedio un error al elimnar");
    }

    /*Metodos getters y setters*/
    public List<Tipocompra> getListTipoGasto() {
        return listTipoGasto;
    }

    public void setListTipoGasto(List<Tipocompra> listTipoGasto) {
        this.listTipoGasto = listTipoGasto;
    }

}

