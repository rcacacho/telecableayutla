package telecableayutla.api.ejb;

import javax.ejb.Local;
import telecableayutla.api.entity.Usuario;

/**
 *
 * @author rcacacho
 */
@Local
public interface LoginBeanLocal {

    Usuario verificarUsuario(String usuario, String password);

    String findUsuario(String usuario);

}
