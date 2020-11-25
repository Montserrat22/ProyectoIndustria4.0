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
@Table(name = "carro")
@NamedQueries({
    @NamedQuery(name = "Carro.findAll", query = "SELECT c FROM Carro c"),
    @NamedQuery(name = "Carro.findById", query = "SELECT c FROM Carro c WHERE c.id = :id"),
    @NamedQuery(name = "Carro.findByNombre", query = "SELECT c FROM Carro c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Carro.findByAnio", query = "SELECT c FROM Carro c WHERE c.anio = :anio"),
    @NamedQuery(name = "Carro.findByStatus", query = "SELECT c FROM Carro c WHERE c.status = :status")})
public class Carro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "anio")
    private Short anio;
    @Column(name = "status")
    private Short status;
    @OneToMany(mappedBy = "idCarro")
    private Collection<Procesoseis> procesoseisCollection;
    @OneToMany(mappedBy = "idCarro")
    private Collection<Procesoocho> procesoochoCollection;
    @OneToMany(mappedBy = "idCarro")
    private Collection<Procesouno> procesounoCollection;
    @OneToMany(mappedBy = "idCarro")
    private Collection<Procesosiete> procesosieteCollection;
    @OneToMany(mappedBy = "idCarro")
    private Collection<Proceso> procesoCollection;
    @OneToMany(mappedBy = "idCarro")
    private Collection<Procesocuatro> procesocuatroCollection;
    @OneToMany(mappedBy = "idCarro")
    private Collection<Procesodos> procesodosCollection;
    @OneToMany(mappedBy = "idCarro")
    private Collection<Procesocinco> procesocincoCollection;
    @OneToMany(mappedBy = "idCarro")
    private Collection<Procesotres> procesotresCollection;

    public Carro() {
    }

    public Carro(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Short getAnio() {
        return anio;
    }

    public void setAnio(Short anio) {
        this.anio = anio;
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

    public Collection<Proceso> getProcesoCollection() {
        return procesoCollection;
    }

    public void setProcesoCollection(Collection<Proceso> procesoCollection) {
        this.procesoCollection = procesoCollection;
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
        if (!(object instanceof Carro)) {
            return false;
        }
        Carro other = (Carro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Carro[ id=" + id + " ]";
    }
    
}
