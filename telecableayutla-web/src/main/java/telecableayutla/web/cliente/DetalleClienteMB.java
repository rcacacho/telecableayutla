package telecableayutla.web.cliente;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import telecableayutla.api.ejb.ClienteBeanLocal;
import telecableayutla.api.entity.Cliente;
import telecableayutla.web.utils.JsfUtil;

/**
 *
 * @author rcacacho
 */
@ManagedBean(name = "detalleClienteMB")
@ViewScoped
public class DetalleClienteMB implements Serializable {

    private static final Logger log = Logger.getLogger(DetalleClienteMB.class);

    @EJB
    private ClienteBeanLocal clienteBean;

    private Integer idcliente;
    private Cliente cliente;

    public void cargarDatos() {
        cliente = clienteBean.findClienteById(idcliente);
    }

    public void regresar() {
        JsfUtil.redirectTo("/clientes/lista.xhtml");
    }
    
    public void editar() {
        JsfUtil.redirectTo("/clientes/editar.xhtml?idCliente=" + idcliente);
    }

    /*Metodos getters y setters*/
    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
}