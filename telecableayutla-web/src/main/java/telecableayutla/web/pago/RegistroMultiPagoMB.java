package telecableayutla.web.pago;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
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
import telecableayutla.api.entity.Municipio;
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
@ManagedBean(name = "registroMultiPagoMB")
@ViewScoped
public class RegistroMultiPagoMB implements Serializable {

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
    private Date fechaInicio;
    private Date fechaFin;

    public RegistroMultiPagoMB() {
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
        LocalDate startDate = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        ZoneId defaultZoneId = ZoneId.systemDefault();

        Integer count = 0;
        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            count++;
            if (count.equals(30)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarPago(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
            } else if (count.equals(60)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarPago(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
            } else if (count.equals(90)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarPago(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                System.out.println("entro al pago 3");
            } else if (count.equals(120)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarPago(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                System.out.println("entro al pago 4");
            } else if (count.equals(150)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarPago(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarPago();
                System.out.println("entro al pago 5");
            } else if (count.equals(180)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarPago(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarPago();
                System.out.println("entro al pago 6");
            } else if (count.equals(210)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarPago(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarPago();
                System.out.println("entro al pago 7");
            } else if (count.equals(240)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarPago(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarPago();
                System.out.println("entro al pago 8");
            } else if (count.equals(270)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarPago(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarPago();
            } else if (count.equals(300)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarPago(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarPago();
            } else if (count.equals(330)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarPago(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarPago();
            } else if (count.equals(360)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarPago(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarPago();
            } else if (count.equals(390)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarPago(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarPago();
            } else if (count.equals(420)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarPago(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarPago();
            } else if (count.equals(450)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarPago(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarPago();
            } else if (count.equals(480)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarPago(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarPago();
            } else if (count.equals(510)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarPago(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarPago();
            } else if (count.equals(540)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarPago(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarPago();
            } else if (count.equals(570)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarPago(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarPago();
            } else if (count.equals(600)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarPago(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarPago();
            } else if (count.equals(630)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarPago(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarPago();
            } else if (count.equals(660)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarPago(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
                //registrarPago();
            } else if (count.equals(690)) {
                LocalDate fPago = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
                Date fechaPago = Date.from(fPago.atStartOfDay(defaultZoneId).toInstant());
                registrarPago(date.getYear(), obtenerMes(date.getMonthValue()), fechaPago);
            }
        }

        pago = null;
        cliente = null;
        detalle = null;
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

    public void registrarPago(Integer anio, String mes, Date fechaPago) throws IOException {
        Pago responseVerificacion = pagosBean.findPagoByIdClienteAndAnioAndMes(cliente.getIdcliente(), anio, mes);

        if (responseVerificacion != null) {
            Pago actualizacionPago = new Pago();
            actualizacionPago = responseVerificacion;
            actualizacionPago.setAnio(anio);
            actualizacionPago.setMes(mes);
            actualizacionPago.setIdcliente(cliente);
            actualizacionPago.setFechapago(fechaPago);
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
            return;
        } else {
            pago.setUsuariocreacion(SesionUsuarioMB.getUserName());
            tipoPago = catalogoBean.findTipoPago(TipoPagoEnum.PAGO.getId());
            pago.setIdcliente(cliente);
            pago.setIdtipopago(tipoPago);
            pago.setAnio(anio);
            pago.setMes(mes);
            pago.setFechapago(fechaPago);
            Pago responsePago = pagosBean.savePago(pago);
            if (responsePago != null) {
                detalle.setUsuariocreacion(SesionUsuarioMB.getUserName());
                detalle.setMontopagado(pago.getTotal());
                detalle.setIdpago(pago);
                Detallepago responseDet = pagosBean.saveDetallepago(detalle);

                JsfUtil.addSuccessMessage("El pago se registro exitosamente");
                return;
            }
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
