package telecableayutla.api.ejb;

import java.util.List;
import javax.ejb.Local;
import telecableayutla.api.entity.Configuracionpago;
import telecableayutla.api.entity.Tipocompra;

/**
 *
 * @author rcacacho
 */
@Local
public interface ConfiguracionBeanLocal {
    
     List<Configuracionpago> listConfiguracionPago();

    Configuracionpago saveConfiguracionPago(Configuracionpago configuracion);

    Configuracionpago deleteConfiguracionPago(Integer idconfiguracionpago, String usuario);

    Configuracionpago findConfiguracionPago(Integer idconfiguracionpago);

    Configuracionpago actualizarConfiguracion(Configuracionpago configuracion);

    List<Tipocompra> listConfiguracionGasto();

    Tipocompra saveConfiguracionGasto(Tipocompra configuracionGasto);

    Tipocompra deleteConfiguracionGasto(Integer idtipocompra, String usuario);

    Tipocompra findConfiguracionGasto(Integer idtipocompra);

    Tipocompra actualizarConfiguracionGasto(Tipocompra configuracionGasto);

    
}
