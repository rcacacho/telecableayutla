package telecableayutla.bussines.ejb.imp;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import telecableayutla.api.ejb.LoginBeanLocal;
import telecableayutla.api.entity.Usuario;

/**
 *
 * @author rcacacho
 */
@Singleton
public class LoginBean implements LoginBeanLocal {
    
    private static final Logger log = Logger.getLogger(LoginBean.class);

    @PersistenceContext(unitName = "TeleCablePU")
    EntityManager em;

    @Resource
    private EJBContext context;

    @Override
    public Usuario verificarUsuario(String usuario, String password) {
        List<Usuario> lst = em.createQuery("SELECT usuario FROM Usuario usuario WHERE usuario.usuario =:usuario and usuario.password =:password ", Usuario.class)
                .setParameter("usuario", usuario)
                .setParameter("password", password)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public String findUsuario(String usuario) {
       List<Usuario> lst = em.createQuery("SELECT usuario FROM Usuario usuario WHERE usuario.usuario =:usuario ", Usuario.class)
                .setParameter("usuario", usuario)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0).getUsuario();
    }

}
