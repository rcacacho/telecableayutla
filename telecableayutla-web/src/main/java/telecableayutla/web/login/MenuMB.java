package telecableayutla.web.login;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import telecableayutla.web.utils.SesionUsuarioMB;

/**
 *
 * @author rcacacho
 */
public class MenuMB implements Serializable {

    private boolean root;

    @PostConstruct
    void cargarDatos() {
        root = SesionUsuarioMB.getRootUsuario();
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

}
