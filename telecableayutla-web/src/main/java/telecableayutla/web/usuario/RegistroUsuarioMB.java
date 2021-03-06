package telecableayutla.web.usuario;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import telecableayutla.api.ejb.CatalogoBeanLocal;
import telecableayutla.api.ejb.UsuarioBeanLocal;
import telecableayutla.api.entity.Municipio;
import telecableayutla.api.entity.Usuario;
import telecableayutla.web.utils.JsfUtil;
import telecableayutla.web.utils.SesionUsuarioMB;


/**
 *
 * @author elfo_
 */
@ManagedBean(name = "registroUsuarioMB")
@ViewScoped
public class RegistroUsuarioMB implements Serializable {

    private static final Logger log = Logger.getLogger(RegistroUsuarioMB.class);

    @EJB
    private UsuarioBeanLocal usuarioBean;
    @EJB
    private CatalogoBeanLocal catalogoBean;

    private Usuario usuario;
    private List<Municipio> listMunicipios;

    public RegistroUsuarioMB() {
        usuario = new Usuario();
    }

    @PostConstruct
    void cargarDatos() {
        listMunicipios = catalogoBean.listMunicipioByIdDepartamento(1);
    }

    public void saveUsuario() throws IOException {
        usuario.setUsuariocreacion(SesionUsuarioMB.getUserName());
        Usuario response = usuarioBean.findUsuario(usuario.getUsuario());
        if (response != null) {
            JsfUtil.addErrorMessage("El usuario ya existe");
            return;
        }

        String contra = md5(usuario.getPassword());
        usuario.setPassword(contra);
        usuario.setUsuariocreacion(SesionUsuarioMB.getUserName());
        Usuario responseVerificacion = usuarioBean.saveUsuario(usuario);
        if (responseVerificacion != null) {
            JsfUtil.addSuccessMessage("Usuario registrado exitosamente");
            usuario = null;
            return;
        }
    }

    public void regresar() {
        JsfUtil.redirectTo("/usuario/lista.xhtml");
    }

    public String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /*Metodos getters y setters*/
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Municipio> getListMunicipios() {
        return listMunicipios;
    }

    public void setListMunicipios(List<Municipio> listMunicipios) {
        this.listMunicipios = listMunicipios;
    }

}
