/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jaker
 */
@Entity
@Table(name = "proceso")
@NamedQueries({
    @NamedQuery(name = "Proceso.findAll", query = "SELECT p FROM Proceso p"),
    @NamedQuery(name = "Proceso.findById", query = "SELECT p FROM Proceso p WHERE p.id = :id"),
    @NamedQuery(name = "Proceso.findByStatus", query = "SELECT p FROM Proceso p WHERE p.status = :status")})
public class Proceso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Column(name = "status")
    private Short status;
    @JoinColumn(name = "id_carro", referencedColumnName = "id")
    @ManyToOne
    private Carro idCarro;
    @JoinColumn(name = "pcinco", referencedColumnName = "statusproceso")
    @ManyToOne
    private Procesocinco pcinco;
    @JoinColumn(name = "pcuatro", referencedColumnName = "statusproceso")
    @ManyToOne
    private Procesocuatro pcuatro;
    @JoinColumn(name = "pdos", referencedColumnName = "statusproceso")
    @ManyToOne
    private Procesodos pdos;
    @JoinColumn(name = "pocho", referencedColumnName = "statusproceso")
    @ManyToOne
    private Procesoocho pocho;
    @JoinColumn(name = "pseis", referencedColumnName = "statusproceso")
    @ManyToOne
    private Procesoseis pseis;
    @JoinColumn(name = "psiete", referencedColumnName = "statusproceso")
    @ManyToOne
    private Procesosiete psiete;
    @JoinColumn(name = "ptres", referencedColumnName = "statusproceso")
    @ManyToOne
    private Procesotres ptres;
    @JoinColumn(name = "puno", referencedColumnName = "statusproceso")
    @ManyToOne
    private Procesouno puno;

    public Proceso() {
    }

    public Proceso(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Carro getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(Carro idCarro) {
        this.idCarro = idCarro;
    }

    public Procesocinco getPcinco() {
        return pcinco;
    }

    public void setPcinco(Procesocinco pcinco) {
        this.pcinco = pcinco;
    }

    public Procesocuatro getPcuatro() {
        return pcuatro;
    }

    public void setPcuatro(Procesocuatro pcuatro) {
        this.pcuatro = pcuatro;
    }

    public Procesodos getPdos() {
        return pdos;
    }

    public void setPdos(Procesodos pdos) {
        this.pdos = pdos;
    }

    public Procesoocho getPocho() {
        return pocho;
    }

    public void setPocho(Procesoocho pocho) {
        this.pocho = pocho;
    }

    public Procesoseis getPseis() {
        return pseis;
    }

    public void setPseis(Procesoseis pseis) {
        this.pseis = pseis;
    }

    public Procesosiete getPsiete() {
        return psiete;
    }

    public void setPsiete(Procesosiete psiete) {
        this.psiete = psiete;
    }

    public Procesotres getPtres() {
        return ptres;
    }

    public void setPtres(Procesotres ptres) {
        this.ptres = ptres;
    }

    public Procesouno getPuno() {
        return puno;
    }

    public void setPuno(Procesouno puno) {
        this.puno = puno;
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
        if (!(object instanceof Proceso)) {
            return false;
        }
        Proceso other = (Proceso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Proceso[ id=" + id + " ]";
    }
    
}
