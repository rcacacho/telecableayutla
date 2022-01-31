package telecableayutla.api.ejb;

import java.util.List;
import javax.ejb.Local;
import telecableayutla.api.entity.Usuario;

/**
 *
 * @author rcacacho
 */
@Local
public interface UsuarioBeanLocal {
    
    List<Usuario> listaUsuarios();

    Usuario saveUsuario(Usuario usuario);

    Usuario findUsuario(Integer idusuario);

    Usuario findUsuario(String usuario);

    Usuario reinicioPassword(Usuario usuario);
    
}
