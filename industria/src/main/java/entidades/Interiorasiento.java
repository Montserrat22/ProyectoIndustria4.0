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
@Table(name = "interiorasiento")
@NamedQueries({
    @NamedQuery(name = "Interiorasiento.findAll", query = "SELECT i FROM Interiorasiento i"),
    @NamedQuery(name = "Interiorasiento.findById", query = "SELECT i FROM Interiorasiento i WHERE i.id = :id"),
    @NamedQuery(name = "Interiorasiento.findByStatusinterior", query = "SELECT i FROM Interiorasiento i WHERE i.statusinterior = :statusinterior")})
public class Interiorasiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Column(name = "statusinterior")
    private Short statusinterior;
    @OneToMany(mappedBy = "iasiento")
    private Collection<Procesoseis> procesoseisCollection;
    @JoinColumn(name = "id_inventario", referencedColumnName = "id")
    @ManyToOne
    private Inventario idInventario;

    public Interiorasiento() {
    }

    public Interiorasiento(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Short getStatusinterior() {
        return statusinterior;
    }

    public void setStatusinterior(Short statusinterior) {
        this.statusinterior = statusinterior;
    }

    public Collection<Procesoseis> getProcesoseisCollection() {
        return procesoseisCollection;
    }

    public void setProcesoseisCollection(Collection<Procesoseis> procesoseisCollection) {
        this.procesoseisCollection = procesoseisCollection;
    }

    public Inventario getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Inventario idInventario) {
        this.idInventario = idInventario;
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
        if (!(object instanceof Interiorasiento)) {
            return false;
        }
        Interiorasiento other = (Interiorasiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Interiorasiento[ id=" + id + " ]";
    }
    
}
