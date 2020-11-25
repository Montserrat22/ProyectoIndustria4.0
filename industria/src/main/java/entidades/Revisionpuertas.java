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
@Table(name = "revisionpuertas")
@NamedQueries({
    @NamedQuery(name = "Revisionpuertas.findAll", query = "SELECT r FROM Revisionpuertas r"),
    @NamedQuery(name = "Revisionpuertas.findById", query = "SELECT r FROM Revisionpuertas r WHERE r.id = :id"),
    @NamedQuery(name = "Revisionpuertas.findByNumpuertas", query = "SELECT r FROM Revisionpuertas r WHERE r.numpuertas = :numpuertas"),
    @NamedQuery(name = "Revisionpuertas.findByAlineadas", query = "SELECT r FROM Revisionpuertas r WHERE r.alineadas = :alineadas"),
    @NamedQuery(name = "Revisionpuertas.findByStatusrevision", query = "SELECT r FROM Revisionpuertas r WHERE r.statusrevision = :statusrevision")})
public class Revisionpuertas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Size(max = 99)
    @Column(name = "numpuertas")
    private String numpuertas;
    @Size(max = 99)
    @Column(name = "alineadas")
    private String alineadas;
    @Size(max = 99)
    @Column(name = "statusrevision")
    private String statusrevision;
    @OneToMany(mappedBy = "rpuertas")
    private Collection<Procesoocho> procesoochoCollection;

    public Revisionpuertas() {
    }

    public Revisionpuertas(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getNumpuertas() {
        return numpuertas;
    }

    public void setNumpuertas(String numpuertas) {
        this.numpuertas = numpuertas;
    }

    public String getAlineadas() {
        return alineadas;
    }

    public void setAlineadas(String alineadas) {
        this.alineadas = alineadas;
    }

    public String getStatusrevision() {
        return statusrevision;
    }

    public void setStatusrevision(String statusrevision) {
        this.statusrevision = statusrevision;
    }

    public Collection<Procesoocho> getProcesoochoCollection() {
        return procesoochoCollection;
    }

    public void setProcesoochoCollection(Collection<Procesoocho> procesoochoCollection) {
        this.procesoochoCollection = procesoochoCollection;
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
        if (!(object instanceof Revisionpuertas)) {
            return false;
        }
        Revisionpuertas other = (Revisionpuertas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Revisionpuertas[ id=" + id + " ]";
    }
    
}
