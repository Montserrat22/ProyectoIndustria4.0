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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jaker
 */
@Entity
@Table(name = "inventario")
@NamedQueries({
    @NamedQuery(name = "Inventario.findAll", query = "SELECT i FROM Inventario i"),
    @NamedQuery(name = "Inventario.findById", query = "SELECT i FROM Inventario i WHERE i.id = :id"),
    @NamedQuery(name = "Inventario.findByCantidad", query = "SELECT i FROM Inventario i WHERE i.cantidad = :cantidad"),
    @NamedQuery(name = "Inventario.findByStatus", query = "SELECT i FROM Inventario i WHERE i.status = :status")})
public class Inventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Column(name = "cantidad")
    private Short cantidad;
    @Column(name = "status")
    private Short status;
    @OneToMany(mappedBy = "idInventario")
    private Collection<Procesouno> procesounoCollection;
    @OneToMany(mappedBy = "idInventario")
    private Collection<Procesosiete> procesosieteCollection;
    @OneToMany(mappedBy = "idInventario")
    private Collection<Procesocuatro> procesocuatroCollection;
    @OneToMany(mappedBy = "idInventario")
    private Collection<Interiorasiento> interiorasientoCollection;
    @OneToMany(mappedBy = "idInventario")
    private Collection<Interiorvolante> interiorvolanteCollection;
    @OneToMany(mappedBy = "idInventario")
    private Collection<Procesodos> procesodosCollection;
    @OneToMany(mappedBy = "idInventario")
    private Collection<Procesocinco> procesocincoCollection;
    @OneToMany(mappedBy = "idInventario")
    private Collection<Interiorfreno> interiorfrenoCollection;
    @JoinColumn(name = "id_pieza", referencedColumnName = "id")
    @ManyToOne
    private Pieza idPieza;
    @OneToMany(mappedBy = "idInventario")
    private Collection<Procesotres> procesotresCollection;

    public Inventario() {
    }

    public Inventario(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Short getCantidad() {
        return cantidad;
    }

    public void setCantidad(Short cantidad) {
        this.cantidad = cantidad;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
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

    public Collection<Interiorasiento> getInteriorasientoCollection() {
        return interiorasientoCollection;
    }

    public void setInteriorasientoCollection(Collection<Interiorasiento> interiorasientoCollection) {
        this.interiorasientoCollection = interiorasientoCollection;
    }

    public Collection<Interiorvolante> getInteriorvolanteCollection() {
        return interiorvolanteCollection;
    }

    public void setInteriorvolanteCollection(Collection<Interiorvolante> interiorvolanteCollection) {
        this.interiorvolanteCollection = interiorvolanteCollection;
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

    public Collection<Interiorfreno> getInteriorfrenoCollection() {
        return interiorfrenoCollection;
    }

    public void setInteriorfrenoCollection(Collection<Interiorfreno> interiorfrenoCollection) {
        this.interiorfrenoCollection = interiorfrenoCollection;
    }

    public Pieza getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(Pieza idPieza) {
        this.idPieza = idPieza;
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
        if (!(object instanceof Inventario)) {
            return false;
        }
        Inventario other = (Inventario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Inventario[ id=" + id + " ]";
    }
    
}
