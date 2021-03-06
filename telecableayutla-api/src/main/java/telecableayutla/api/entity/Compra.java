package telecableayutla.api.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rcacacho
 */
@Entity
@Table(name = "compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c"),
    @NamedQuery(name = "Compra.findByIdcompra", query = "SELECT c FROM Compra c WHERE c.idcompra = :idcompra"),
    @NamedQuery(name = "Compra.findByBienoservicio", query = "SELECT c FROM Compra c WHERE c.bienoservicio = :bienoservicio"),
    @NamedQuery(name = "Compra.findByNocomprobanteegreso", query = "SELECT c FROM Compra c WHERE c.nocomprobanteegreso = :nocomprobanteegreso"),
    @NamedQuery(name = "Compra.findByFechacompra", query = "SELECT c FROM Compra c WHERE c.fechacompra = :fechacompra"),
    @NamedQuery(name = "Compra.findBySerie", query = "SELECT c FROM Compra c WHERE c.serie = :serie"),
    @NamedQuery(name = "Compra.findByNodocumento", query = "SELECT c FROM Compra c WHERE c.nodocumento = :nodocumento"),
    @NamedQuery(name = "Compra.findByMontocompra", query = "SELECT c FROM Compra c WHERE c.montocompra = :montocompra"),
    @NamedQuery(name = "Compra.findByDescripcion", query = "SELECT c FROM Compra c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Compra.findByFechacreacion", query = "SELECT c FROM Compra c WHERE c.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Compra.findByUsuariocreacion", query = "SELECT c FROM Compra c WHERE c.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Compra.findByFechamodificacion", query = "SELECT c FROM Compra c WHERE c.fechamodificacion = :fechamodificacion"),
    @NamedQuery(name = "Compra.findByUsuariomodificacion", query = "SELECT c FROM Compra c WHERE c.usuariomodificacion = :usuariomodificacion"),
    @NamedQuery(name = "Compra.findByActivo", query = "SELECT c FROM Compra c WHERE c.activo = :activo")})
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcompra")
    private Integer idcompra;
    
    @Size(max = 100)
    @Column(name = "bienoservicio")
    private String bienoservicio;
    
    @Size(max = 100)
    @Column(name = "nocomprobanteegreso")
    private String nocomprobanteegreso;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechacompra")
    @Temporal(TemporalType.DATE)
    private Date fechacompra;
    
    @Size(max = 100)
    @Column(name = "serie")
    private String serie;
    
    @Size(max = 200)
    @Column(name = "nodocumento")
    private String nodocumento;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "montocompra")
    private double montocompra;
    
    @Size(max = 2000)
    @Column(name = "descripcion")
    private String descripcion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechacreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "usuariocreacion")
    private String usuariocreacion;
    
    @Column(name = "fechamodificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechamodificacion;
    
    @Size(max = 50)
    @Column(name = "usuariomodificacion")
    private String usuariomodificacion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    
    @JoinColumn(name = "idproveedor", referencedColumnName = "idproveedor")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Proveedor idproveedor;
    
    @JoinColumn(name = "idtipocompra", referencedColumnName = "idtipocompra")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tipocompra idtipocompra;
    
    @JoinColumn(name = "idtipodocumentocompra", referencedColumnName = "idtipodocumentocompra")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tipodocumentocompra idtipodocumentocompra;

    public Compra() {
    }

    public Compra(Integer idcompra) {
        this.idcompra = idcompra;
    }

    public Compra(Integer idcompra, Date fechacompra, double montocompra, Date fechacreacion, String usuariocreacion, boolean activo) {
        this.idcompra = idcompra;
        this.fechacompra = fechacompra;
        this.montocompra = montocompra;
        this.fechacreacion = fechacreacion;
        this.usuariocreacion = usuariocreacion;
        this.activo = activo;
    }

    public Integer getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(Integer idcompra) {
        this.idcompra = idcompra;
    }

    public String getBienoservicio() {
        return bienoservicio;
    }

    public void setBienoservicio(String bienoservicio) {
        this.bienoservicio = bienoservicio;
    }

    public String getNocomprobanteegreso() {
        return nocomprobanteegreso;
    }

    public void setNocomprobanteegreso(String nocomprobanteegreso) {
        this.nocomprobanteegreso = nocomprobanteegreso;
    }

    public Date getFechacompra() {
        return fechacompra;
    }

    public void setFechacompra(Date fechacompra) {
        this.fechacompra = fechacompra;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNodocumento() {
        return nodocumento;
    }

    public void setNodocumento(String nodocumento) {
        this.nodocumento = nodocumento;
    }

    public double getMontocompra() {
        return montocompra;
    }

    public void setMontocompra(double montocompra) {
        this.montocompra = montocompra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getUsuariocreacion() {
        return usuariocreacion;
    }

    public void setUsuariocreacion(String usuariocreacion) {
        this.usuariocreacion = usuariocreacion;
    }

    public Date getFechamodificacion() {
        return fechamodificacion;
    }

    public void setFechamodificacion(Date fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
    }

    public String getUsuariomodificacion() {
        return usuariomodificacion;
    }

    public void setUsuariomodificacion(String usuariomodificacion) {
        this.usuariomodificacion = usuariomodificacion;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Proveedor getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(Proveedor idproveedor) {
        this.idproveedor = idproveedor;
    }

    public Tipocompra getIdtipocompra() {
        return idtipocompra;
    }

    public void setIdtipocompra(Tipocompra idtipocompra) {
        this.idtipocompra = idtipocompra;
    }

    public Tipodocumentocompra getIdtipodocumentocompra() {
        return idtipodocumentocompra;
    }

    public void setIdtipodocumentocompra(Tipodocumentocompra idtipodocumentocompra) {
        this.idtipodocumentocompra = idtipodocumentocompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcompra != null ? idcompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.idcompra == null && other.idcompra != null) || (this.idcompra != null && !this.idcompra.equals(other.idcompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "telecableayutla.api.entity.Compra[ idcompra=" + idcompra + " ]";
    }
    
}
