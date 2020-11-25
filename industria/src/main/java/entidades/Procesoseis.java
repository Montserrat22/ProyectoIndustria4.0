/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jaker
 */
@Entity
@Table(name = "procesoseis")
@NamedQueries({
    @NamedQuery(name = "Procesoseis.findAll", query = "SELECT p FROM Procesoseis p"),
    @NamedQuery(name = "Procesoseis.findById", query = "SELECT p FROM Procesoseis p WHERE p.id = :id"),
    @NamedQuery(name = "Procesoseis.findByHora", query = "SELECT p FROM Procesoseis p WHERE p.hora = :hora"),
    @NamedQuery(name = "Procesoseis.findByFecha", query = "SELECT p FROM Procesoseis p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Procesoseis.findByStatusproceso", query = "SELECT p FROM Procesoseis p WHERE p.statusproceso = :statusproceso")})
public class Procesoseis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Size(max = 7)
    @Column(name = "hora")
    private String hora;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "statusproceso")
    private Short statusproceso;
    @JoinColumn(name = "id_carro", referencedColumnName = "id")
    @ManyToOne
    private Carro idCarro;
    @JoinColumn(name = "iasiento", referencedColumnName = "statusinterior")
    @ManyToOne
    private Interiorasiento iasiento;
    @JoinColumn(name = "ifreno", referencedColumnName = "statusinterior")
    @ManyToOne
    private Interiorfreno ifreno;
    @JoinColumn(name = "ivolante", referencedColumnName = "statusinterior")
    @ManyToOne
    private Interiorvolante ivolante;
    @JoinColumn(name = "id_solicitud", referencedColumnName = "id")
    @ManyToOne
    private Slicitud idSolicitud;
    @OneToMany(mappedBy = "pseis")
    private Collection<Proceso> procesoCollection;

    public Procesoseis() {
    }

    public Procesoseis(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Short getStatusproceso() {
        return statusproceso;
    }

    public void setStatusproceso(Short statusproceso) {
        this.statusproceso = statusproceso;
    }

    public Carro getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(Carro idCarro) {
        this.idCarro = idCarro;
    }

    public Interiorasiento getIasiento() {
        return iasiento;
    }

    public void setIasiento(Interiorasiento iasiento) {
        this.iasiento = iasiento;
    }

    public Interiorfreno getIfreno() {
        return ifreno;
    }

    public void setIfreno(Interiorfreno ifreno) {
        this.ifreno = ifreno;
    }

    public Interiorvolante getIvolante() {
        return ivolante;
    }

    public void setIvolante(Interiorvolante ivolante) {
        this.ivolante = ivolante;
    }

    public Slicitud getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Slicitud idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Collection<Proceso> getProcesoCollection() {
        return procesoCollection;
    }

    public void setProcesoCollection(Collection<Proceso> procesoCollection) {
        this.procesoCollection = procesoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Procesoseis)) {
            return false;
        }
        Procesoseis other = (Procesoseis) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Procesoseis[ id=" + id + " ]";
    }
    
}
