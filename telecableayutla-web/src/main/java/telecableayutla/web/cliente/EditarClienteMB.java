package telecableayutla.web.cliente;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import telecableayutla.api.ejb.CatalogoBeanLocal;
import telecableayutla.api.ejb.ClienteBeanLocal;
import telecableayutla.api.entity.Cliente;
import telecableayutla.api.entity.Configuracionpago;
import telecableayutla.api.entity.Municipio;
import telecableayutla.api.entity.Sector;
import telecableayutla.web.utils.JsfUtil;
import telecableayutla.web.utils.SesionUsuarioMB;

/**
 *
 * @author rcacacho
 */
@ManagedBean(name = "editarClienteMB")
@ViewScoped
public class EditarClienteMB implements Serializable {

    private static final Logger log = Logger.getLogger(EditarClienteMB.class);

    @EJB
    private ClienteBeanLocal clienteBean;
    @EJB
    private CatalogoBeanLocal catalogoBean;

    private Integer idcliente;
    private Cliente cliente;
    private List<Municipio> listMunicipios;
    private List<Configuracionpago> listConfiguracionPago;
    private List<Sector> listSector;

    public void cargarDatos() {
        cliente = clienteBean.findClienteById(idcliente);
        listMunicipios = catalogoBean.listMunicipioByIdDepartamento(1);
        listConfiguracionPago = catalogoBean.ListConfiguracionPago();
        listSector = catalogoBean.listSector();
    }

    public void actualizarCliente() throws IOException {
        cliente.setUsuariomodificacion(SesionUsuarioMB.getUserName());
        cliente.setFechamodificacion(new Date());
        Cliente responseVerificacion = clienteBean.updateCliente(cliente);
        if (responseVerificacion != null) {
            JsfUtil.addSuccessMessage("Cliente actualizado exitosamente");
            JsfUtil.redirectTo("/clientes/detalle.xhtml?idCliente=" + idcliente);
        } else {
            JsfUtil.addErrorMessage("Ocurrio un error verificar datos");
        }
    }

    public void regresar() {
        JsfUtil.redirectTo("/clientes/detalle.xhtml?idCliente=" + idcliente);
    }

    public void cargarDialog() {
        RequestContext.getCurrentInstance().execute("PF('dlgProveedor').show()");
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

    public List<Municipio> getListMunicipios() {
        return listMunicipios;
    }

    public void setListMunicipios(List<Municipio> listMunicipios) {
        this.listMunicipios = listMunicipios;
    }

    public List<Configuracionpago> getListConfiguracionPago() {
        return listConfiguracionPago;
    }

    public void setListConfiguracionPago(List<Configuracionpago> listConfiguracionPago) {
        this.listConfiguracionPago = listConfiguracionPago;
    }

    public List<Sector> getListSector() {
        return listSector;
    }

    public void setListSector(List<Sector> listSector) {
        this.listSector = listSector;
    }

}
