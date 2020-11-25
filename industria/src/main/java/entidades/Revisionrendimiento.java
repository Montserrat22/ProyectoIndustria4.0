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
@Table(name = "revisionrendimiento")
@NamedQueries({
    @NamedQuery(name = "Revisionrendimiento.findAll", query = "SELECT r FROM Revisionrendimiento r"),
    @NamedQuery(name = "Revisionrendimiento.findById", query = "SELECT r FROM Revisionrendimiento r WHERE r.id = :id"),
    @NamedQuery(name = "Revisionrendimiento.findByRpm", query = "SELECT r FROM Revisionrendimiento r WHERE r.rpm = :rpm"),
    @NamedQuery(name = "Revisionrendimiento.findByStatusrevision", query = "SELECT r FROM Revisionrendimiento r WHERE r.statusrevision = :statusrevision")})
public class Revisionrendimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Size(max = 99)
    @Column(name = "rpm")
    private String rpm;
    @Size(max = 99)
    @Column(name = "statusrevision")
    private String statusrevision;
    @OneToMany(mappedBy = "rrendimiento")
    private Collection<Procesoocho> procesoochoCollection;

    public Revisionrendimiento() {
    }

    public Revisionrendimiento(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getRpm() {
        return rpm;
    }

    public void setRpm(String rpm) {
        this.rpm = rpm;
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
        if (!(object instanceof Revisionrendimiento)) {
            return false;
        }
        Revisionrendimiento other = (Revisionrendimiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Revisionrendimiento[ id=" + id + " ]";
    }
    
}
