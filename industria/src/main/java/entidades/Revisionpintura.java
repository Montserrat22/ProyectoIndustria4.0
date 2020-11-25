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
@Table(name = "revisionpintura")
@NamedQueries({
    @NamedQuery(name = "Revisionpintura.findAll", query = "SELECT r FROM Revisionpintura r"),
    @NamedQuery(name = "Revisionpintura.findById", query = "SELECT r FROM Revisionpintura r WHERE r.id = :id"),
    @NamedQuery(name = "Revisionpintura.findByUniformidad", query = "SELECT r FROM Revisionpintura r WHERE r.uniformidad = :uniformidad"),
    @NamedQuery(name = "Revisionpintura.findByEscurrimiento", query = "SELECT r FROM Revisionpintura r WHERE r.escurrimiento = :escurrimiento"),
    @NamedQuery(name = "Revisionpintura.findByStatusrevision", query = "SELECT r FROM Revisionpintura r WHERE r.statusrevision = :statusrevision")})
public class Revisionpintura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Size(max = 99)
    @Column(name = "uniformidad")
    private String uniformidad;
    @Size(max = 99)
    @Column(name = "escurrimiento")
    private String escurrimiento;
    @Size(max = 99)
    @Column(name = "statusrevision")
    private String statusrevision;
    @OneToMany(mappedBy = "rpintura")
    private Collection<Procesoocho> procesoochoCollection;

    public Revisionpintura() {
    }

    public Revisionpintura(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getUniformidad() {
        return uniformidad;
    }

    public void setUniformidad(String uniformidad) {
        this.uniformidad = uniformidad;
    }

    public String getEscurrimiento() {
        return escurrimiento;
    }

    public void setEscurrimiento(String escurrimiento) {
        this.escurrimiento = escurrimiento;
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
        if (!(object instanceof Revisionpintura)) {
            return false;
        }
        Revisionpintura other = (Revisionpintura) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Revisionpintura[ id=" + id + " ]";
    }
    
}
