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
@Table(name = "revisionmotor")
@NamedQueries({
    @NamedQuery(name = "Revisionmotor.findAll", query = "SELECT r FROM Revisionmotor r"),
    @NamedQuery(name = "Revisionmotor.findById", query = "SELECT r FROM Revisionmotor r WHERE r.id = :id"),
    @NamedQuery(name = "Revisionmotor.findByArranque", query = "SELECT r FROM Revisionmotor r WHERE r.arranque = :arranque"),
    @NamedQuery(name = "Revisionmotor.findByEnsamble", query = "SELECT r FROM Revisionmotor r WHERE r.ensamble = :ensamble"),
    @NamedQuery(name = "Revisionmotor.findByStatusrevision", query = "SELECT r FROM Revisionmotor r WHERE r.statusrevision = :statusrevision")})
public class Revisionmotor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Size(max = 99)
    @Column(name = "arranque")
    private String arranque;
    @Size(max = 99)
    @Column(name = "ensamble")
    private String ensamble;
    @Size(max = 99)
    @Column(name = "statusrevision")
    private String statusrevision;
    @OneToMany(mappedBy = "rmotor")
    private Collection<Procesoocho> procesoochoCollection;

    public Revisionmotor() {
    }

    public Revisionmotor(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getArranque() {
        return arranque;
    }

    public void setArranque(String arranque) {
        this.arranque = arranque;
    }

    public String getEnsamble() {
        return ensamble;
    }

    public void setEnsamble(String ensamble) {
        this.ensamble = ensamble;
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
        if (!(object instanceof Revisionmotor)) {
            return false;
        }
        Revisionmotor other = (Revisionmotor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Revisionmotor[ id=" + id + " ]";
    }
    
}
