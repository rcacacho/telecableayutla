package telecableayutla.bussines.ejb.imp;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import telecableayutla.api.ejb.CatalogoBeanLocal;
import telecableayutla.api.entity.Configuracionpago;
import telecableayutla.api.entity.Departamento;
import telecableayutla.api.entity.Formapago;
import telecableayutla.api.entity.Municipio;
import telecableayutla.api.entity.Proveedor;
import telecableayutla.api.entity.Sector;
import telecableayutla.api.entity.Tipocompra;
import telecableayutla.api.entity.Tipodocumentocompra;
import telecableayutla.api.entity.Tipopago;
import telecableayutla.api.entity.Usuario;

/**
 *
 * @author rcacacho
 */
@Singleton
public class CatalagoBean implements CatalogoBeanLocal {

    private static final Logger log = Logger.getLogger(CatalagoBean.class);

    @PersistenceContext(unitName = "TeleCablePU")
    EntityManager em;

    @Resource
    private EJBContext context;

    @Override
    public List<Municipio> listMunicipioByIdDepartamento(Integer iddepartamento) {
        if (iddepartamento == null) {
            return null;
        }

        List<Municipio> lst = em.createQuery("SELECT muni FROM Municipio muni WHERE muni.iddepartamento.iddepartamento =:iddepartamento", Municipio.class)
                .setParameter("iddepartamento", iddepartamento)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<Departamento> listDepartamentos() {
        List<Departamento> lst = em.createQuery("SELECT dep FROM Departamento dep WHERE dep.activo  = true", Departamento.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public Tipopago findTipoPago(Integer idtipopago) {
        List<Tipopago> lst = em.createQuery("SELECT dep FROM Tipopago dep WHERE dep.activo  = true and dep.idtipopago =:idtipopago", Tipopago.class)
                .setParameter("idtipopago", idtipopago)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public Configuracionpago findConfiguracionPago(Integer idconfiguracionpago) {
        List<Configuracionpago> lst = em.createQuery("SELECT dep FROM Configuracionpago dep WHERE dep.activo  = true and dep.idconfiguracionpago =:idconfiguracionpago ", Configuracionpago.class)
                .setParameter("idconfiguracionpago", idconfiguracionpago)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public List<Configuracionpago> ListConfiguracionPago() {
        List<Configuracionpago> lst = em.createQuery("SELECT dep FROM Configuracionpago dep WHERE dep.activo  = true ", Configuracionpago.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<Proveedor> listProveedor() {
        List<Proveedor> lst = em.createQuery("SELECT qj FROM Proveedor qj where qj.activo = true ", Proveedor.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<Tipodocumentocompra> listTipoDocumento() {
        List<Tipodocumentocompra> lst = em.createQuery("SELECT qj FROM Tipodocumentocompra qj where qj.activo = true ", Tipodocumentocompra.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<Tipocompra> listTipoCompra() {
        List<Tipocompra> lst = em.createQuery("SELECT qj FROM Tipocompra qj where qj.activo = true ", Tipocompra.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<Formapago> listFormaPago() {
        List<Formapago> lst = em.createQuery("SELECT qj FROM Formapago qj where qj.activo = true ", Formapago.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<Usuario> listaUsuarios() {
        List<Usuario> lst = em.createQuery("SELECT qj FROM Usuario qj where qj.activo = true", Usuario.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<Sector> listSector() {
        List<Sector> lst = em.createQuery("SELECT qj FROM Sector qj where qj.activo = true", Sector.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }
}
