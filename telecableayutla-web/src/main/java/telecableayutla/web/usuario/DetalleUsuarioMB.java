package telecableayutla.web.usuario;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import telecableayutla.api.ejb.UsuarioBeanLocal;
import telecableayutla.api.entity.Usuario;
import telecableayutla.web.utils.JsfUtil;

/**
 *
 * @author elfo_
 */
@ManagedBean(name = "detalleUsuarioMB")
@ViewScoped
public class DetalleUsuarioMB implements Serializable {
    
    private static final Logger log = Logger.getLogger(DetalleUsuarioMB.class);

    @EJB
    private UsuarioBeanLocal usuarioBean;

    private Integer idusuario;
    private Usuario usuario;

    public void cargarDatos() {
        usuario = usuarioBean.findUsuario(idusuario);
    }

    public void regresar() {
        JsfUtil.redirectTo("/usuario/lista.xhtml");
    }
    
    /*Metodos getters y setteres*/
    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    } 
    
}
