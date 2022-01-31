package telecableayutla.api.ejb;

import java.util.List;
import javax.ejb.Local;
import telecableayutla.api.entity.Configuracionpago;
import telecableayutla.api.entity.Departamento;
import telecableayutla.api.entity.Formapago;
import telecableayutla.api.entity.Municipio;
import telecableayutla.api.entity.Proveedor;
import telecableayutla.api.entity.Tipocompra;
import telecableayutla.api.entity.Tipodocumentocompra;
import telecableayutla.api.entity.Tipopago;
import telecableayutla.api.entity.Usuario;

/**
 *
 * @author rcacacho
 */
@Local
public interface CatalogoBeanLocal {

    List<Departamento> listDepartamentos();

    List<Municipio> listMunicipioByIdDepartamento(Integer iddepartamento);

    Tipopago findTipoPago(Integer idtipopago);

    Configuracionpago findConfiguracionPago(Integer idconfiguracionpago);

    List<Configuracionpago> ListConfiguracionPago();

    List<Proveedor> listProveedor();

    List<Tipodocumentocompra> listTipoDocumento();

    List<Tipocompra> listTipoCompra();

    List<Formapago> listFormaPago();

    List<Usuario> listaUsuarios();

}
