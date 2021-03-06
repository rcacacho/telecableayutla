package telecableayutla.web.pago;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import telecableayutla.api.entity.Formapago;
import telecableayutla.api.entity.Pago;
import telecableayutla.api.entity.Sector;
import telecableayutla.api.entity.Tipopago;
import telecableayutla.api.enums.TipoPagoEnum;
import telecableayutla.web.utils.JsfUtil;
import telecableayutla.web.utils.SesionUsuarioMB;

/**
 *
 * @author elfo_
 */
@ManagedBean(name = "registroPagoMB")
@ViewScoped
public class RegistroPagoMB implements Serializable {

    private static final Logger log = Logger.getLogger(ListaPagosMB.class);

    @EJB
    private PagosBeanLocal pagosBean;
    @EJB
    private ClienteBeanLocal clienteBean;
    @EJB
    private CatalogoBeanLocal catalogoBean;

    private Pago pago;
    private Detallepago detalle;
    private Cliente cliente;
    private Tipopago tipoPago;
    private List<Formapago> listFormaPago;
    private List<Cliente> listClientes;
    private Sector sectorSelected;
    private List<Sector> listSector;

    public RegistroPagoMB() {
        pago = new Pago();
        detalle = new Detallepago();
    }

    @PostConstruct
    void cargarDatos() {
        listFormaPago = catalogoBean.listFormaPago();
        listSector = catalogoBean.listSector();
    }

    public List<Cliente> completeCliente(String query) {
        List<Cliente> clientes = clienteBean.ListClientesByNombre(query);
        return clientes;
    }

    public void savePago() throws IOException {
        Pago responseVerificacion = pagosBean.findPagoByIdClienteAndAnioAndMes(cliente.getIdcliente(), pago.getAnio(), pago.getMes());
        if (responseVerificacion != null) {
            Pago actualizacionPago = new Pago();
            actualizacionPago = responseVerificacion;
            actualizacionPago.setAnio(pago.getAnio());
            actualizacionPago.setMes(pago.getMes());
            actualizacionPago.setIdcliente(cliente);
            actualizacionPago.setFechapago(new Date());

            Integer total = cliente.getIdconfiguracionpago().getValor();

            if (pago.getTotal() != null) {
                pago.setTotal(total - pago.getTotal());
            }

            Pago updatePago = pagosBean.updatePago(actualizacionPago);

            detalle.setUsuariocreacion(SesionUsuarioMB.getUserName());
            detalle.setMontopagado(pago.getTotal());
            detalle.setIdpago(actualizacionPago);
            Detallepago responseDet = pagosBean.saveDetallepago(detalle);

            JsfUtil.addSuccessMessage("El pago se registro exitosamente");
            pago = null;
            cliente = null;
            detalle = null;
            sectorSelected = null;
            return;
        } else {
            pago.setUsuariocreacion(SesionUsuarioMB.getUserName());
            tipoPago = catalogoBean.findTipoPago(TipoPagoEnum.PAGO.getId());
            pago.setIdcliente(cliente);
            pago.setIdtipopago(tipoPago);
            pago.setFechapago(new Date());
            Pago responsePago = pagosBean.savePago(pago);
            if (responsePago != null) {
                detalle.setUsuariocreacion(SesionUsuarioMB.getUserName());
                detalle.setMontopagado(pago.getTotal());
                detalle.setIdpago(pago);
                Detallepago responseDet = pagosBean.saveDetallepago(detalle);

                JsfUtil.addSuccessMessage("El pago se registro exitosamente");
                pago = null;
                cliente = null;
                detalle = null;
                sectorSelected = null;
                return;
            }
        }
    }

    public void regresar() {
        JsfUtil.redirectTo("/pagos/lista.xhtml");
    }

    public List<Cliente> findPersona(String search) {
        List<Cliente> listado = new ArrayList();
        try {
            if (!search.isEmpty()) {
                List<Cliente> response = clienteBean.ListClientesByNombre(search);
                if (response != null) {
                    listado = response;
                }
            }

            return listado;
        } catch (Exception ex) {
            JsfUtil.addErrorMessage("No encontrado");
            return null;
        }
    }

    public void cargarCliente() {
        pago.setIdcliente(cliente);
    }

    public void cargarClientesSector() {
        if (sectorSelected != null) {
            listClientes = clienteBean.ListClientesByIdSector(sectorSelected.getIdsector());
        } else {
            listClientes = null;
            cliente = null;
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

    public List<Formapago> getListFormaPago() {
        return listFormaPago;
    }

    public void setListFormaPago(List<Formapago> listFormaPago) {
        this.listFormaPago = listFormaPago;
    }

    public List<Cliente> getListClientes() {
        return listClientes;
    }

    public void setListClientes(List<Cliente> listClientes) {
        this.listClientes = listClientes;
    }

    public Tipopago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(Tipopago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Detallepago getDetalle() {
        return detalle;
    }

    public void setDetalle(Detallepago detalle) {
        this.detalle = detalle;
    }

    public List<Sector> getListSector() {
        return listSector;
    }

    public void setListSector(List<Sector> listSector) {
        this.listSector = listSector;
    }

    public Sector getSectorSelected() {
        return sectorSelected;
    }

    public void setSectorSelected(Sector sectorSelected) {
        this.sectorSelected = sectorSelected;
    }

}
