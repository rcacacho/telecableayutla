package telecableayutla.web.pago;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import telecableayutla.api.ejb.ClienteBeanLocal;
import telecableayutla.api.ejb.PagosBeanLocal;
import telecableayutla.api.entity.Cliente;
import telecableayutla.api.entity.Pago;
import telecableayutla.web.utils.JsfUtil;
import telecableayutla.web.utils.SesionUsuarioMB;


/**
 *
 * @author rcacacho
 */
@ManagedBean(name = "listaPagosMB")
@ViewScoped
public class ListaPagosMB implements Serializable {

    private static final Logger log = Logger.getLogger(ListaPagosMB.class);

    @EJB
    private PagosBeanLocal pagosBean;
    @EJB
    private ClienteBeanLocal clienteBean;

    private List<Pago> listPago;
    private Integer idcliente;
    private Integer anio;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaInicioBus;
    private Date fechaFinBus;
    private String mes;
    private List<Cliente> listClientes;

    public ListaPagosMB() {
    }

    @PostConstruct
    void cargarDatos() {
        Date fInicio = new Date();
        Date fFin = new Date();

        if (SesionUsuarioMB.getRootUsuario()) {
            listClientes = clienteBean.ListClientes();
            listPago = pagosBean.listPagos();
        } else if (SesionUsuarioMB.getIdMunicipio().equals(6)) {
            listClientes = clienteBean.listClientesByInMunucipio();
        } else {
            listClientes = clienteBean.ListClientesByIdMunucipio(SesionUsuarioMB.getIdMunicipio());
            listPago = pagosBean.listPagosByInIdMunicipios();
        }
    }

    public void buscarPago() {
        if (idcliente != 0 && anio != null) {
            List<Pago> response = pagosBean.listPagoByIdClienteAndAnio(idcliente, anio);
            if (response != null) {
                listPago = response;
            } else {
                listPago = new ArrayList<>();
                JsfUtil.addErrorMessage("No se encontraron datos");
            }
        } else if (idcliente != 0) {
            List<Pago> response = pagosBean.listPagoByIdCliente(idcliente);
            if (response != null) {
                listPago = response;
            } else {
                listPago = new ArrayList<>();
                JsfUtil.addErrorMessage("No se encontraron datos");
            }
        } else if (anio != null) {
            List<Pago> response = pagosBean.listPagoByAnio(anio);
            if (response != null) {
                listPago = response;
            } else {
                listPago = new ArrayList<>();
                JsfUtil.addErrorMessage("No se encontraron datos");
            }
        } else if (mes != "") {
            List<Pago> response = pagosBean.listPagoByMes(mes);
            if (response != null) {
                listPago = response;
            } else {
                listPago = new ArrayList<>();
                JsfUtil.addErrorMessage("No se encontraron datos");
            }
        } else if (fechaInicioBus != null && fechaFinBus != null) {
            List<Pago> response = pagosBean.listPagosByFechaInicioAndFin(fechaInicioBus, fechaFinBus);
            if (response != null) {
                listPago = response;
            } else {
                listPago = new ArrayList<>();
                JsfUtil.addErrorMessage("No se encontraron datos");
            }
        } else {
            listPago = new ArrayList<>();
            JsfUtil.addErrorMessage("No se encontraron datos");
        }
    }

    public void limpiarCampos() {
        anio = null;
        mes = "";
        idcliente = 0;
        fechaInicioBus = null;
        fechaFinBus = null;
        listPago = null;
    }

    public void detalle(Integer id) {
        JsfUtil.redirectTo("/pagos/detalle.xhtml?idpago=" + id);
    }

    public void eliminarPago(Integer id) {
        Pago response = pagosBean.eliminarPago(id);
        if (response != null) {
            buzon();
            JsfUtil.addSuccessMessage("Se elimino el pago exitosamente");
            return;
        }

        JsfUtil.addErrorMessage("Sucedio un error al elimnar");
    }

    public boolean obtenerRoot() {
        if (SesionUsuarioMB.getRootUsuario()) {
            return true;
        } else {
            return false;
        }
    }

    public void buzon() {
        if (SesionUsuarioMB.getRootUsuario()) {
            listClientes = clienteBean.ListClientes();
            listPago = pagosBean.listPagos();
        } else if (SesionUsuarioMB.getIdMunicipio().equals(6)) {
            listClientes = clienteBean.listClientesByInMunucipio();
        } else {
            listClientes = clienteBean.ListClientesByIdMunucipio(SesionUsuarioMB.getIdMunicipio());
            listPago = pagosBean.listPagosByInIdMunicipios();
        }
    }

    /*Metodos getters y setters*/
    public List<Pago> getListPago() {
        return listPago;
    }

    public void setListPago(List<Pago> listPago) {
        this.listPago = listPago;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public List<Cliente> getListClientes() {
        return listClientes;
    }

    public void setListClientes(List<Cliente> listClientes) {
        this.listClientes = listClientes;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Date getFechaInicioBus() {
        return fechaInicioBus;
    }

    public void setFechaInicioBus(Date fechaInicioBus) {
        this.fechaInicioBus = fechaInicioBus;
    }

    public Date getFechaFinBus() {
        return fechaFinBus;
    }

    public void setFechaFinBus(Date fechaFinBus) {
        this.fechaFinBus = fechaFinBus;
    }

}
