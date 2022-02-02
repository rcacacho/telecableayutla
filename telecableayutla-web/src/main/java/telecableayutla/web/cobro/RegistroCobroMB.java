package telecableayutla.web.cobro;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import telecableayutla.api.ejb.CatalogoBeanLocal;
import telecableayutla.api.ejb.ClienteBeanLocal;
import telecableayutla.api.ejb.PagosBeanLocal;
import telecableayutla.api.entity.Cliente;
import telecableayutla.api.entity.Detallepago;
import telecableayutla.api.entity.Pago;
import telecableayutla.api.entity.Sector;
import telecableayutla.api.enums.TipoPagoEnum;
import telecableayutla.web.utils.JsfUtil;
import telecableayutla.web.utils.SesionUsuarioMB;

/**
 *
 * @author rcacacho
 */
@ManagedBean(name = "registroCobroMB")
@ViewScoped
public class RegistroCobroMB implements Serializable {

    private static final Logger log = Logger.getLogger(RegistroCobroMB.class);

    @EJB
    private PagosBeanLocal pagosBean;
    @EJB
    private ClienteBeanLocal clienteBean;
    @EJB
    private CatalogoBeanLocal catalogoBean;

    private Pago pago;
    private Sector sectorSelected;
    private List<Sector> listSector;
    private Cliente cliente;
    private List<Cliente> listClientes;

    public RegistroCobroMB() {
        pago = new Pago();
    }

    @PostConstruct
    void cargarDatos() {
        listSector = catalogoBean.listSector();
    }

    public void regresar() {
        JsfUtil.redirectTo("/cobros/lista.xhtml");
    }

    public void cargarClientesSector() {
        if (sectorSelected != null) {
            listClientes = clienteBean.ListClientesByIdSector(sectorSelected.getIdsector());
        } else {
            listClientes = null;
            cliente = null;
        }
    }

    public void cargarCliente() {
        pago.setIdcliente(cliente);
    }

    public void saveCobro() throws IOException {
        if (cliente.getIdconfiguracionpago().getValor() == null) {
            JsfUtil.addErrorMessage("El cliente debe tener un cobro configurado");
            return;
        }

        pago.setUsuariocreacion(SesionUsuarioMB.getUserName());
        pago.setIdcliente(cliente);
        pago.setIdtipopago(catalogoBean.findTipoPago(TipoPagoEnum.COBRO.getId()));

        if (pago.getTotal() != null) {
            pago.setTotal(cliente.getIdconfiguracionpago().getValor() - pago.getTotal());
        } else {
            pago.setTotal(cliente.getIdconfiguracionpago().getValor());
        }

        Pago response = pagosBean.saveCobro(pago);
        if (response != null) {
            Detallepago detalle = new Detallepago();
            detalle.setIdpago(pago);
            if (pago.getTotal() != null) {
                detalle.setMontocobrado(pago.getTotal());
            } else {
                detalle.setMontocobrado(cliente.getIdconfiguracionpago().getValor());
            }

            if (detalle.getMontopagado() != null) {
                detalle.setTotal(detalle.getMontocobrado() - detalle.getMontopagado());
            } else {
                detalle.setTotal(detalle.getMontocobrado());
            }

            detalle.setUsuariocreacion(SesionUsuarioMB.getUserName());
            Detallepago responseDetalle = pagosBean.saveDetallepago(detalle);

            JsfUtil.addSuccessMessage("El cobro se registro exitosamente");
            pago = null;
            cliente = null;
            return;
        }
    }

    /*Metodos getters y setters*/
    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getListClientes() {
        return listClientes;
    }

    public void setListClientes(List<Cliente> listClientes) {
        this.listClientes = listClientes;
    }

    public Sector getSectorSelected() {
        return sectorSelected;
    }

    public void setSectorSelected(Sector sectorSelected) {
        this.sectorSelected = sectorSelected;
    }

    public List<Sector> getListSector() {
        return listSector;
    }

    public void setListSector(List<Sector> listSector) {
        this.listSector = listSector;
    }

}
