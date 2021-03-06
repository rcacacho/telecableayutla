package telecableayutla.web.cobro;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
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
import telecableayutla.api.entity.Municipio;
import telecableayutla.api.entity.Pago;
import telecableayutla.api.entity.Sector;
import telecableayutla.api.enums.TipoPagoEnum;
import telecableayutla.web.utils.JsfUtil;
import telecableayutla.web.utils.SesionUsuarioMB;

/**
 *
 * @author rcacacho
 */
@ManagedBean(name = "registroCobroMasivoMB")
@ViewScoped
public class RegistroCobroMasivoMB implements Serializable {

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
    private Date fechaInicio;
    private Date fechaFin;

    public RegistroCobroMasivoMB() {
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
        LocalDate startDate = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        ZoneId defaultZoneId = ZoneId.systemDefault();

        Integer count = 0;
        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            count++;
            if (count.equals(30)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarCobro(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
            } else if (count.equals(60)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarCobro(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
            } else if (count.equals(90)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarCobro(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                System.out.println("entro al pago 3");
            } else if (count.equals(120)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarCobro(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                System.out.println("entro al pago 4");
            } else if (count.equals(150)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarCobro(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarCobro();
                System.out.println("entro al pago 5");
            } else if (count.equals(180)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarCobro(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarCobro();
                System.out.println("entro al pago 6");
            } else if (count.equals(210)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarCobro(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarCobro();
                System.out.println("entro al pago 7");
            } else if (count.equals(240)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarCobro(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarCobro();
                System.out.println("entro al pago 8");
            } else if (count.equals(270)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarCobro(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarCobro();
            } else if (count.equals(300)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarCobro(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarCobro();
            } else if (count.equals(330)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarCobro(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarCobro();
            } else if (count.equals(360)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarCobro(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarCobro();
            } else if (count.equals(390)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarCobro(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarCobro();
            } else if (count.equals(420)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarCobro(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarCobro();
            } else if (count.equals(450)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarCobro(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarCobro();
            } else if (count.equals(480)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarCobro(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarCobro();
            } else if (count.equals(510)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarCobro(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarCobro();
            } else if (count.equals(540)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarCobro(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarCobro();
            } else if (count.equals(570)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarCobro(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarCobro();
            } else if (count.equals(600)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarCobro(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarCobro();
            } else if (count.equals(630)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarCobro(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarCobro();
            } else if (count.equals(660)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarCobro(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarCobro();
            } else if (count.equals(690)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarCobro(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
            }
        }

        pago = null;
        cliente = null;
    }

    public void registrarCobro(Integer anio, String mes, Date fechaPago) throws IOException {
        if (cliente.getIdconfiguracionpago().getValor() == null) {
            JsfUtil.addErrorMessage("El cliente debe tener un cobro configurado");
            return;
        }

        pago.setUsuariocreacion(SesionUsuarioMB.getUserName());
        pago.setIdcliente(cliente);
        pago.setIdtipopago(catalogoBean.findTipoPago(TipoPagoEnum.COBRO.getId()));
        pago.setAnio(anio);
        pago.setMes(mes);
        pago.setFechapago(fechaPago);

        if (pago.getTotal() != null) {
            pago.setTotal(cliente.getIdconfiguracionpago().getValor() - pago.getTotal());
        } else {
            pago.setTotal(cliente.getIdconfiguracionpago().getValor());
        }

        Pago response = pagosBean.saveCobro(pago);
        if (response != null) {
            Detallepago detalle = new Detallepago();
            detalle.setIdpago(pago);
            detalle.setFechapago(fechaPago);
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

    public String obtenerMes(Integer mes) {
        if (mes.equals(1)) {
            return "enero";
        } else if (mes.equals(2)) {
            return "febrero";
        } else if (mes.equals(3)) {
            return "marzo";
        } else if (mes.equals(4)) {
            return "abril";
        } else if (mes.equals(5)) {
            return "mayo";
        } else if (mes.equals(6)) {
            return "junio";
        } else if (mes.equals(7)) {
            return "julio";
        } else if (mes.equals(8)) {
            return "agosto";
        } else if (mes.equals(9)) {
            return "septiembre";
        } else if (mes.equals(10)) {
            return "octubre";
        } else if (mes.equals(11)) {
            return "noviembre";
        } else if (mes.equals(12)) {
            return "diciembre";
        }
        return null;
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
