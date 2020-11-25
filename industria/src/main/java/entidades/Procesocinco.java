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
@Table(name = "procesocinco")
@NamedQueries({
    @NamedQuery(name = "Procesocinco.findAll", query = "SELECT p FROM Procesocinco p"),
    @NamedQuery(name = "Procesocinco.findById", query = "SELECT p FROM Procesocinco p WHERE p.id = :id"),
    @NamedQuery(name = "Procesocinco.findByHora", query = "SELECT p FROM Procesocinco p WHERE p.hora = :hora"),
    @NamedQuery(name = "Procesocinco.findByFecha", query = "SELECT p FROM Procesocinco p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Procesocinco.findByStatusproceso", query = "SELECT p FROM Procesocinco p WHERE p.statusproceso = :statusproceso")})
public class Procesocinco implements Serializable {

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
    @OneToMany(mappedBy = "pcinco")
    private Collection<Proceso> procesoCollection;
    @JoinColumn(name = "id_carro", referencedColumnName = "id")
    @ManyToOne
    private Carro idCarro;
    @JoinColumn(name = "id_inventario", referencedColumnName = "id")
    @ManyToOne
    private Inventario idInventario;
    @JoinColumn(name = "id_solicitud", referencedColumnName = "id")
    @ManyToOne
    private Slicitud idSolicitud;

    public Procesocinco() {
    }

    public Procesocinco(Short id) {
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

    public Collection<Proceso> getProcesoCollection() {
        return procesoCollection;
    }

    public void setProcesoCollection(Collection<Proceso> procesoCollection) {
        this.procesoCollection = procesoCollection;
    }

    public Carro getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(Carro idCarro) {
        this.idCarro = idCarro;
    }

    public Inventario getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Inventario idInventario) {
        this.idInventario = idInventario;
    }

    public Slicitud getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Slicitud idSolicitud) {
        this.idSolicitud = idSolicitud;
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
        if (!(object instanceof Procesocinco)) {
            return false;
        }
        Procesocinco other = (Procesocinco) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Procesocinco[ id=" + id + " ]";
    }
    
}
