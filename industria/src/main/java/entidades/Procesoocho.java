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

/**
 *
 * @author jaker
 */
@Entity
@Table(name = "procesoocho")
@NamedQueries({
    @NamedQuery(name = "Procesoocho.findAll", query = "SELECT p FROM Procesoocho p"),
    @NamedQuery(name = "Procesoocho.findById", query = "SELECT p FROM Procesoocho p WHERE p.id = :id"),
    @NamedQuery(name = "Procesoocho.findByFechainspeccion", query = "SELECT p FROM Procesoocho p WHERE p.fechainspeccion = :fechainspeccion"),
    @NamedQuery(name = "Procesoocho.findByStatusproceso", query = "SELECT p FROM Procesoocho p WHERE p.statusproceso = :statusproceso")})
public class Procesoocho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Column(name = "fechainspeccion")
    @Temporal(TemporalType.DATE)
    private Date fechainspeccion;
    @Column(name = "statusproceso")
    private Short statusproceso;
    @JoinColumn(name = "id_carro", referencedColumnName = "id")
    @ManyToOne
    private Carro idCarro;
    @JoinColumn(name = "rcarroceria", referencedColumnName = "statusrevision")
    @ManyToOne
    private Revisioncarroceria rcarroceria;
    @JoinColumn(name = "rinteriores", referencedColumnName = "statusrevision")
    @ManyToOne
    private Revisioninteriores rinteriores;
    @JoinColumn(name = "rllantas", referencedColumnName = "statusrevision")
    @ManyToOne
    private Revisionllantas rllantas;
    @JoinColumn(name = "rmotor", referencedColumnName = "statusrevision")
    @ManyToOne
    private Revisionmotor rmotor;
    @JoinColumn(name = "rpintura", referencedColumnName = "statusrevision")
    @ManyToOne
    private Revisionpintura rpintura;
    @JoinColumn(name = "rpuertas", referencedColumnName = "statusrevision")
    @ManyToOne
    private Revisionpuertas rpuertas;
    @JoinColumn(name = "rrendimiento", referencedColumnName = "statusrevision")
    @ManyToOne
    private Revisionrendimiento rrendimiento;
    @JoinColumn(name = "id_solicitud", referencedColumnName = "id")
    @ManyToOne
    private Slicitud idSolicitud;
    @OneToMany(mappedBy = "pocho")
    private Collection<Proceso> procesoCollection;

    public Procesoocho() {
    }

    public Procesoocho(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Date getFechainspeccion() {
        return fechainspeccion;
    }

    public void setFechainspeccion(Date fechainspeccion) {
        this.fechainspeccion = fechainspeccion;
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

    public Revisioncarroceria getRcarroceria() {
        return rcarroceria;
    }

    public void setRcarroceria(Revisioncarroceria rcarroceria) {
        this.rcarroceria = rcarroceria;
    }

    public Revisioninteriores getRinteriores() {
        return rinteriores;
    }

    public void setRinteriores(Revisioninteriores rinteriores) {
        this.rinteriores = rinteriores;
    }

    public Revisionllantas getRllantas() {
        return rllantas;
    }

    public void setRllantas(Revisionllantas rllantas) {
        this.rllantas = rllantas;
    }

    public Revisionmotor getRmotor() {
        return rmotor;
    }

    public void setRmotor(Revisionmotor rmotor) {
        this.rmotor = rmotor;
    }

    public Revisionpintura getRpintura() {
        return rpintura;
    }

    public void setRpintura(Revisionpintura rpintura) {
        this.rpintura = rpintura;
    }

    public Revisionpuertas getRpuertas() {
        return rpuertas;
    }

    public void setRpuertas(Revisionpuertas rpuertas) {
        this.rpuertas = rpuertas;
    }

    public Revisionrendimiento getRrendimiento() {
        return rrendimiento;
    }

    public void setRrendimiento(Revisionrendimiento rrendimiento) {
        this.rrendimiento = rrendimiento;
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
        if (!(object instanceof Procesoocho)) {
            return false;
        }
        Procesoocho other = (Procesoocho) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Procesoocho[ id=" + id + " ]";
    }
    
}
