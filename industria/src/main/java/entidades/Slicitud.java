/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jaker
 */
@Entity
@Table(name = "slicitud")
@NamedQueries({
    @NamedQuery(name = "Slicitud.findAll", query = "SELECT s FROM Slicitud s"),
    @NamedQuery(name = "Slicitud.findById", query = "SELECT s FROM Slicitud s WHERE s.id = :id"),
    @NamedQuery(name = "Slicitud.findByFechai", query = "SELECT s FROM Slicitud s WHERE s.fechai = :fechai"),
    @NamedQuery(name = "Slicitud.findByFechaf", query = "SELECT s FROM Slicitud s WHERE s.fechaf = :fechaf"),
    @NamedQuery(name = "Slicitud.findByStatus", query = "SELECT s FROM Slicitud s WHERE s.status = :status")})
public class Slicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Size(max = 99)
    @Column(name = "fechai")
    private String fechai;
    @Size(max = 99)
    @Column(name = "fechaf")
    private String fechaf;
    @Column(name = "status")
    private Short status;
    @OneToMany(mappedBy = "idSolicitud")
    private Collection<Procesoseis> procesoseisCollection;
    @OneToMany(mappedBy = "idSolicitud")
    private Collection<Procesoocho> procesoochoCollection;
    @OneToMany(mappedBy = "idSolicitud")
    private Collection<Procesouno> procesounoCollection;
    @OneToMany(mappedBy = "idSolicitud")
    private Collection<Procesosiete> procesosieteCollection;
    @OneToMany(mappedBy = "idSolicitud")
    private Collection<Procesocuatro> procesocuatroCollection;
    @OneToMany(mappedBy = "idSolicitud")
    private Collection<Procesodos> procesodosCollection;
    @OneToMany(mappedBy = "idSolicitud")
    private Collection<Procesocinco> procesocincoCollection;
    @OneToMany(mappedBy = "idSolicitud")
    private Collection<Procesotres> procesotresCollection;

    public Slicitud() {
    }

    public Slicitud(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getFechai() {
        return fechai;
    }

    public void setFechai(String fechai) {
        this.fechai = fechai;
    }

    public String getFechaf() {
        return fechaf;
    }

    public void setFechaf(String fechaf) {
        this.fechaf = fechaf;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Collection<Procesoseis> getProcesoseisCollection() {
        return procesoseisCollection;
    }

    public void setProcesoseisCollection(Collection<Procesoseis> procesoseisCollection) {
        this.procesoseisCollection = procesoseisCollection;
    }

    public Collection<Procesoocho> getProcesoochoCollection() {
        return procesoochoCollection;
    }

    public void setProcesoochoCollection(Collection<Procesoocho> procesoochoCollection) {
        this.procesoochoCollection = procesoochoCollection;
    }

    public Collection<Procesouno> getProcesounoCollection() {
        return procesounoCollection;
    }

    public void setProcesounoCollection(Collection<Procesouno> procesounoCollection) {
        this.procesounoCollection = procesounoCollection;
    }

    public Collection<Procesosiete> getProcesosieteCollection() {
        return procesosieteCollection;
    }

    public void setProcesosieteCollection(Collection<Procesosiete> procesosieteCollection) {
        this.procesosieteCollection = procesosieteCollection;
    }

    public Collection<Procesocuatro> getProcesocuatroCollection() {
        return procesocuatroCollection;
    }

    public void setProcesocuatroCollection(Collection<Procesocuatro> procesocuatroCollection) {
        this.procesocuatroCollection = procesocuatroCollection;
    }

    public Collection<Procesodos> getProcesodosCollection() {
        return procesodosCollection;
    }

    public void setProcesodosCollection(Collection<Procesodos> procesodosCollection) {
        this.procesodosCollection = procesodosCollection;
    }

    public Collection<Procesocinco> getProcesocincoCollection() {
        return procesocincoCollection;
    }

    public void setProcesocincoCollection(Collection<Procesocinco> procesocincoCollection) {
        this.procesocincoCollection = procesocincoCollection;
    }

    public Collection<Procesotres> getProcesotresCollection() {
        return procesotresCollection;
    }

    public void setProcesotresCollection(Collection<Procesotres> procesotresCollection) {
        this.procesotresCollection = procesotresCollection;
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
        if (!(object instanceof Slicitud)) {
            return false;
        }
        Slicitud other = (Slicitud) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Slicitud[ id=" + id + " ]";
    }
    
}
