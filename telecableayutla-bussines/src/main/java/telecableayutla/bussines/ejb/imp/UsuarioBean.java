package telecableayutla.bussines.ejb.imp;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.apache.log4j.Logger;
import telecableayutla.api.ejb.UsuarioBeanLocal;
import telecableayutla.api.entity.Usuario;

/**
 *
 * @author rcacacho
 */
@Singleton
public class UsuarioBean implements UsuarioBeanLocal {

    private static final Logger log = Logger.getLogger(UsuarioBean.class);

    @PersistenceContext(unitName = "TeleCablePU")
    EntityManager em;

    @Resource
    private EJBContext context;

    private void processException(Exception ex) {
        log.error(ex.getMessage(), ex);
    }

    private String getConstraintViolationExceptionAsString(ConstraintViolationException ex) {
        StringBuilder sb = new StringBuilder();
        sb.append("Error de validación:\n");
        for (ConstraintViolation c : ex.getConstraintViolations()) {
            sb.append(String.format("[bean: %s; field: %s; message: %s; value: %s]",
                    c.getRootBeanClass().getName(),
                    c.getPropertyPath().toString(),
                    c.getMessage(), c.getInvalidValue()));
        }
        return sb.toString();
    }

    @Override
    public List<Usuario> listaUsuarios() {
        List<Usuario> lst = em.createQuery("SELECT qj FROM Usuario qj ", Usuario.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        try {
            usuario.setFechacreacion(new Date());
            usuario.setActivo(true);
            em.persist(usuario);
            em.flush();
            return (usuario);
        } catch (ConstraintViolationException ex) {
            String validationError = getConstraintViolationExceptionAsString(ex);
            log.error(validationError);
            context.setRollbackOnly();
            return null;
        } catch (Exception ex) {
            processException(ex);
            context.setRollbackOnly();
            return null;
        }
    }

    @Override
    public Usuario findUsuario(Integer idusuario) {
        List<Usuario> lst = em.createQuery("SELECT usuario FROM Usuario usuario WHERE usuario.idusuario =:idusuario ", Usuario.class)
                .setParameter("idusuario", idusuario)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public Usuario findUsuario(String usuario) {
        List<Usuario> lst = em.createQuery("SELECT usuario FROM Usuario usuario WHERE usuario.usuario =:usuario ", Usuario.class)
                .setParameter("usuario", usuario)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public Usuario reinicioPassword(Usuario usuario) {
        if (usuario == null) {
            context.setRollbackOnly();
            return null;
        }

        try {
            Usuario toUpdate = em.find(Usuario.class, usuario.getIdusuario());

            toUpdate.setPassword(usuario.getPassword());
            toUpdate.setUsuariomodificacion(usuario.getUsuariomodificacion());
            toUpdate.setFechamodificacion(new Date());
            em.merge(toUpdate);

            return toUpdate;
        } catch (ConstraintViolationException ex) {
            String validationError = getConstraintViolationExceptionAsString(ex);
            log.error(validationError);
            context.setRollbackOnly();
            return null;
        } catch (Exception ex) {
            processException(ex);
            return null;
        }
    }

}

